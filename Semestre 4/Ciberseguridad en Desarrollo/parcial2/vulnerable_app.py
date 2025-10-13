from flask import Flask, request, render_template_string, session, redirect, url_for, flash
from authlib.integrations.flask_client import OAuth
import sqlite3
import os
import hashlib

app = Flask(__name__)
app.secret_key = os.urandom(24)

oauth = OAuth(app)
auth0 = oauth.register(
	'auth0',
	client_id='V3AD82bsJAV9FvH3GsMloEPRqDhDiAVf',
	client_secret='QFvoiTroR3lRJ9Pgq9hX6EcIWVHqFExLWRvsicwibS5TltOLmXMtD2_3uObcVqym',
	api_base_url='https://dev-g36zcabhihbnkc6s.us.auth0.com',
	access_token_url='https://dev-g36zcabhihbnkc6s.us.auth0.com/oauth/token',
	authorize_url='https://dev-g36zcabhihbnkc6s.us.auth0.com/authorize',
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


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        conn = get_db_connection()

        # Inyección de SQL solo si se detecta un payload de inyección de SQL
        if "' OR '" in password:
            query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
            user = conn.execute(query).fetchone()
        else:
            query = "SELECT * FROM users WHERE username = ? AND password = ?"
            hashed_password = hash_password(password)
            user = conn.execute(query, (username, hashed_password)).fetchone()

        print("Consulta SQL generada:", query)

        if user:
            session['user_id'] = user['id']
            session['role'] = user['role']
            return redirect(url_for('dashboard'))
        else:
            return 'Invalid credentials!'
    return '''
        <form method="post">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
    '''


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

    task = request.form['task']
    user_id = session['user_id']

    conn = get_db_connection()
    conn.execute(
        "INSERT INTO tasks (user_id, task) VALUES (?, ?)", (user_id, task))
    conn.commit()
    conn.close()

    return redirect(url_for('dashboard'))


@app.route('/delete_task/<int:task_id>')
def delete_task(task_id):
    if 'user_id' not in session:
        return redirect(url_for('login'))

    conn = get_db_connection()
    conn.execute("DELETE FROM tasks WHERE id = ?", (task_id,))
    conn.commit()
    conn.close()

    return redirect(url_for('dashboard'))


@app.route('/admin')
def admin():
    if 'user_id' not in session or session.get('role') != 'admin':
        return redirect(url_for('login'))

    return 'Welcome to the admin panel!'


if __name__ == '__main__':
    app.run(debug=True)
