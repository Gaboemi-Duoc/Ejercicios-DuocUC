const app = document.getElementById('app'); // Get Div called 'app'
const app2 = document.getElementById('app2'); // Get Div called 'app2'
const root = ReactDOM.createRoot(app);
const root2 = ReactDOM.createRoot(app2);

function Header() {
  return <h1>Hello World!</h1>;
}
 
function HomePage() { // Ideally the format would be full of calls to functions...
  return (
    <div>
    	{/* Nesting the Header component */}
		<Header />
		<p>Or so they say...</p>
		<p>There was a time where humanity was but a nomadic people, with stories transmitted mouth to mouth, ear to ear.</p>
		<a href="https://nextjs.org/learn/react-foundations/getting-started-with-react">Link Tutorial React NextJS</a>
	</div>
  );
}

root.render(<HomePage />);
//root2.render(<Header />);

