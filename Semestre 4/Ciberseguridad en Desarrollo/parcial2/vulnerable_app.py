# Gabriel Jeria Mayerovich - 21009174-2

from flask import Flask, request, render_template_string, session, redirect, url_for, flash
import sqlite3
import os
import hashlib
import html
import re
import secrets
import json
from urllib.parse import urlencode
from authlib.integrations.flask_client import OAuth
from jose import jwt, JWTError
import requests

from flask_wtf.csrf import CSRFProtect

app = Flask(__name__)
app.secret_key = os.urandom(24)

# Cookies Seguras
app.config['SESSION_COOKIE_SECURE'] = True
app.config['SESSION_COOKIE_HTTPONLY'] = True

# Proteccion CSRF
csrf = CSRFProtect()
csrf.init_app(app)

# Auth0
oauth = OAuth(app)
auth0 = oauth.register(
    'auth0',
    client_id='V3AD82bsJAV9FvH3GsMloEPRqDhDiAVf',
    client_secret='QFvoiTroR3lRJ9Pgq9hX6EcIWVHqFExLWRvsicwibS5TltOLmXMtD2_3uObcVqym',
    api_base_url='https://dev-g36zcabhihbnkc6s.us.auth0.com',
    access_token_url=f'https://dev-g36zcabhihbnkc6s.us.auth0.com/oauth/token',
    authorize_url=f'https://dev-g36zcabhihbnkc6s.us.auth0.com/authorize',
    client_kwargs={
        'scope': 'openid profile email',
    },
	server_metadata_url=f'https://dev-g36zcabhihbnkc6s.us.auth0.com/.well-known/openid-configuration'
)

def get_db_connection():
    conn = sqlite3.connect('database.db')
    conn.row_factory = sqlite3.Row
    return conn


def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()


@app.route('/')
def index():
    return 'Welcome to the Task Manager Application!'

# @app.route('/login', methods=['GET', 'POST'])
# def login():
#     if request.method == 'POST':
#         username = request.form['username']
#         password = request.form['password']

#         conn = get_db_connection()

#         # Usar la consulta parametrizada
#         query = "SELECT * FROM users WHERE username = ? AND password = ?"
#         hashed_password = hash_password(password)
#         user = conn.execute(query, (username, hashed_password)).fetchone()

#         print("Consulta SQL generada:", query)

#         if user:
#             session['user_id'] = user['id']
#             session['role'] = user['role']
#             return redirect(url_for('dashboard'))
#         else:
#             return 'Invalid credentials!'
    
#     return '''
#         <form method="post">
#             Username: <input type="text" name="username"><br>
#             Password: <input type="password" name="password"><br>
#             <input type="submit" value="Login">
#         </form>
#     '''


@app.route('/login')
def login():
    # Redirect to Auth0 for authentication
    return auth0.authorize_redirect(redirect_uri='/callback', audience='https://dev-g36zcabhihbnkc6s.us.auth0.com/userinfo')

@app.route('/callback')
def callback():
    # Callback de Auth0
    try:
        token = auth0.authorize_access_token()
    
        resp = auth0.get('userinfo')
        userinfo = resp.json()
        
        # Informacion del usuario en la sesion
        session['user_id'] = userinfo['sub']
        session['user_email'] = userinfo['email']
        session['user_name'] = userinfo.get('name', '')
        session['access_token'] = token['access_token']
        
        # Checkear si el usuario existe en la base de datos
        conn = get_db_connection()
        user = conn.execute(
            "SELECT * FROM users WHERE username = ?", (session['user_name'],)
        ).fetchone()
        
        if not user:
            # Crear usuario con rol 'user' por defecto 
            conn.execute(
                "INSERT INTO users (username, password, role) VALUES (?, ?, ?)",
                (session['user_name'], hash_password(os.urandom(24).hex()), 'user')
            )
            conn.commit()
        
        conn.close()
        
        return redirect(url_for('dashboard'))
    
    except Exception as e:
        flash(f'Authentication failed: {str(e)}')
        return redirect(url_for('index'))
    
@app.route('/logout')
def logout():
    session.clear()
    params = {'returnTo': url_for('index', _external=True), 'client_id': 'V3AD82bsJAV9FvH3GsMloEPRqDhDiAVf'}
    return redirect(f'https://dev-g36zcabhihbnkc6s.us.auth0.com/v2/logout?{urlencode(params)}')

@app.route('/dashboard')
def dashboard():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user_id = session['user_id']
    conn = get_db_connection()
    tasks = conn.execute(
        "SELECT * FROM tasks WHERE user_id = ?", (user_id,)).fetchall()
    conn.close()

    return render_template_string('''
        <h1>Welcome, user {{ user_id }}!</h1>
        <form action="/add_task" method="post">
            <input type="text" name="task" placeholder="New task"><br>
            <input type="submit" value="Add Task">
        </form>
        <h2>Your Tasks</h2>
        <ul>
        {% for task in tasks %}
            <li>{{ task['task'] }} <a href="/delete_task/{{ task['id'] }}">Delete</a></li>
        {% endfor %}
        </ul>
    ''', user_id=user_id, tasks=tasks)


@app.route('/add_task', methods=['POST'])
def add_task():
    if 'user_id' not in session:
        return redirect(url_for('login'))
    
    # Verificar token CSRF
    if not verify_csrf_token():
        flash('Invalid security token', 'error')
        return redirect(url_for('dashboard')), 403

    task = request.form['task']
    user_id = session['user_id']
    # Validar y sanitizar la tarea
    if not task or len(task.strip()) == 0:
        flash('Task cannot be empty')
        return redirect(url_for('dashboard'))
    
    if len(task) > 500:
        flash('Task is too long')
        return redirect(url_for('dashboard'))

    # Escapar contenido HTML para proteger contra XSS
    task_escaped = html.escape(task)
    
    # Validación adicional contra patrones maliciosos
    if contains_malicious_patterns(task_escaped):
        flash('Invalid task content', 'error')
        return redirect(url_for('dashboard')), 400

    try:
        conn = get_db_connection()
        conn.execute(
            "INSERT INTO tasks (user_id, task) VALUES (?, ?)", (user_id, task_escaped))
        conn.commit()
        conn.close()
        flash('Task added successfully', 'success')
        
    except Exception as e:
        # Log del error sin exponer detalles al usuario
        print(f"Database error: {e}")
        flash('Error adding task', 'error')
        return redirect(url_for('dashboard')), 500

    return redirect(url_for('dashboard'))


@app.route('/delete_task/<int:task_id>')
def delete_task(task_id):
    if 'user_id' not in session:
        return redirect(url_for('login'))
    
    # Verificar token CSRF
    if not verify_csrf_token():
        flash('Invalid security token', 'error')
        return redirect(url_for('dashboard')), 403

    user_id = session['user_id'] #Obtener el user_id de la sesion
    
    # Validar que task_id sea positivo
    if task_id <= 0:
        flash('ID de tarea inválido', 'error')
        return redirect(url_for('dashboard')), 400
    
    conn = get_db_connection()

    task = conn.execute( "SELECT * FROM tasks WHERE id = ? AND user_id = ?", (task_id, user_id) ).fetchone()
    
    # Escapar contenido HTML para proteger contra XSS
    task_escaped = html.escape(task)
    

    if task_escaped:
        conn.execute("DELETE FROM tasks WHERE id = ? AND user_id = ?", (task_escaped, user_id))
        conn.commit()
        flash('Tarea borrada exitosamente!')
        
    else:
        flash('Tarea no encontrada o con permisos insuficientes para borrar la tarea')

    conn.close()
    return redirect(url_for('dashboard'))


@app.route('/admin')
def admin():
    if 'user_id' not in session or session.get('role') != 'admin':
        return redirect(url_for('login'))

    return 'Welcome to the admin panel! <a href="/dashboard">Back to Dashboard</a>'

# Hace disponible el token CSRF en todas las plantillas
@app.context_processor
def inject_csrf_token():
    return dict(csrf_token=generate_csrf_token())

# Regenerar token CSRF después de cada uso para mayor seguridad
@app.after_request
def regenerate_csrf_token(response):
    if 'csrf_token' in session:
        session.pop('csrf_token', None)
        generate_csrf_token()
    return response

# Genera un token CSRF seguro y lo almacena en sesión
def generate_csrf_token():
    if 'csrf_token' not in session:
        session['csrf_token'] = secrets.token_urlsafe(64)
    return session['csrf_token']

#Verifica el token CSRF de forma segura
def verify_csrf_token():
    if request.method == 'POST':
        token_received = request.form.get('csrf_token', '')
        token_session = session.get('csrf_token', '')
        
        # Validar que ambos tokens existen
        if not token_received or not token_session:
            return False
        
        # Comparación segura contra timing attacks
        return secrets.compare_digest(token_received, token_session)
    
    return False

def contains_malicious_patterns(text):
    # Detecta patrones potencialmente maliciosos
    malicious_patterns = [
        r'<script.*?>.*?</script>',
        r'javascript:',
        r'on\w+\s*=',
        r'vbscript:',
        r'expression\s*\(',
    ]
    
    for pattern in malicious_patterns:
        if re.search(pattern, text, re.IGNORECASE):
            return True
    return False

from flask_talisman import Talisman
Talisman(app, force_https=True, content_security_policy={'default-src': "'self'"})

if __name__ == '__main__':
    app.run(debug=True)
