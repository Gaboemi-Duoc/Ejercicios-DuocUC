const app = document.getElementById('app'); // Get Div called 'app'
const app2 = document.getElementById('app2'); // Get Div called 'app2'
const root = ReactDOM.createRoot(app);
const root2 = ReactDOM.createRoot(app2);

function Header(props) {
  return <div>
			<ul>
				<li>arg1: {props.arg1}</li>
				<li>arg2: {props.arg2}</li>
			</ul>
  		</div>;
}

function Producto(props) {
	num_prods = props.likes
	for (let i = 0; i < num_prods; i++) {
		complist.push(<><Content proid={i} /></>)
	}
}
 
function HomePage() { // Ideally the format would be full of calls to functions...
	const productos = [""]
	const complist = [<><Content proid={1} /></>, <><Content proid={2} /></>]

	const [likes, setLikes] = React.useState(0);
	function handleClickUpvote() {
		setLikes(likes + 1)
		complist.push(<><Content proid={likes + 1} /></>)
	}
	function handleClickDownvote() {
		setLikes(likes - 1)
	}

	return (
		<div>
			{/* Nesting the Header component */}
			<Header arg1="Hola" arg2="Mundo"/>
			<p>Or so they say...</p>
			<p>There was a time where humanity was but a nomadic people, with stories transmitted mouth to mouth, ear to ear.</p>
		
			<a href="https://nextjs.org/learn/react-foundations/getting-started-with-react">Link Tutorial React NextJS</a>

			<div>
				<p>Reddit Points: {likes}</p>
				<button onClick={handleClickUpvote}>Upvote</button>
				<button onClick={handleClickDownvote}>Downvote</button>
			</div>
		</div>
	);
}
root.render(<HomePage />);
//root2.render(<Header />);

