function NavBar() {
  return <h1>Hola mundo. Soy el componente NavBar</h1>;
}
// function Content(props) {
//   return <div>
//     <h1 key={props.prodid}>Hola mundo. Soy el componente producto {props.prodid} </h1>
//   </div>
// }
function Footer() {
  return <h1>Hola mundo. Soy el componente Footer</h1>;
}
 
 
function Product(props){
  const prodid = props.prodid
  return <>
    <h1>Hola mundo. Soy el componente producto {prodid} </h1>
  </>
}
 
 
function Products(props){
  const num_prods = props.likes
 
 
  // Para cada producto, crear un componente Product que reciba el id del producto
  // y retorne un tag html
  const complist = []
  for (let i = 0; i < num_prods; i++) {
    complist.push(<Product key={i} prodid={i} />)
  }
  
  return <div>
    {complist}
  </div>
}
 
function HomePage() {
  const [count, setCount] = React.useState(0);
  // const [saludo, setSaludo] = React.useState("Hola");
 
 
  function addClick() {
    setCount(count + 1);
  }
  function subtractClick() {
    if (count > 0) {
      setCount(count - 1);
    }
    else {
      setCount(0);
    }
  }
 
 
  return (
    <div>
      <NavBar />
      <button onClick={subtractClick}> - </button>
      <b> Mostrando {count} producto(s) </b>
      <button onClick={addClick}> + </button>
      {/* {complist} */}
      <Products likes={count} />
      <Footer />
    </div>
  );
}
 
 
const root = ReactDOM.createRoot(app);
root.render(<HomePage />);
 