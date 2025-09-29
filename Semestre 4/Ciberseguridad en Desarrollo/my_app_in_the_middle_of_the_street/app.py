from flask import Flask, redirect, url_for, session, request, jsonify
from flask_login import LoginManager, UserMixin, login_user, logout_user, login_required
from authlib.integrations.flask_client import OAuth
from functools import wraps
import os

app = Flask(__name__)
app.secret_key = os.getenv('SECRET_KEY', 'supersecretkey')
app.config['SESSION_COOKIE_NAME'] = 'auth_session'

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

login_manager = LoginManager()
login_manager.init_app(app)

class User(UserMixin):
	def __init__(self, id, name, email):
		self.id = id
		self.name = name
		self.email = email

users = {}

@login_manager.user_loader
def load_user(user_id):
	return users.get(user_id)

def requires_auth(f):
	@wraps(f)
	def decorated(*args, **kwargs):
		if 'profile' not in session:
			return redirect('/login')
		return f(*args, **kwargs)
	return decorated

@app.route('/')
def index():
	return 'Bienvenido! <a href="/login">Login</a>'

@app.route('/login')
def login():
	return auth0.authorize_redirect(redirect_uri=url_for('callback', _external=True))

@app.route('/callback')
def callback():
	auth0.authorize_access_token()
	resp = auth0.get('userinfo')
	user_info = resp.json()
	user = User(id=user_info['sub'], name=user_info['name'], email=user_info['email'])
	users[user.id] = user
	login_user(user)
	session['profile'] = user_info
	return redirect('/dashboard')

@app.route('/logout')
@login_required
def logout():
	logout_user()
	session.clear()
	return redirect('/')

@app.route('/dashboard')
@requires_auth
def dashboard():
	return f"Logged in as: {session['profile']['name']}"

if __name__ == '__main__':
	app.run(debug=True)
