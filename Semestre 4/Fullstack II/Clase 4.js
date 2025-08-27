
console.log("Hello World!")

varScoping()
letScoping()

let n1 = 3;
let n2 = 5;
console.log("La suma entre " + n1 + " y " + n2 + " es " + suma(n1,n2));

function saludar(nombre) { return "Hola " + nombre + "!"; }
console.log(saludar("Juan"));

function varScoping() {
  console.log("var Scoping:");
  var x = 1;
  if (true) {
    var x = 2;
    console.log(x);
  }
  console.log(x);
}
function letScoping() {
  console.log("let Scoping:");
  let x = 1;
  if (true) {
    let x = 2;
    console.log(x);
  }
  console.log(x);
}

function suma(n1,n2) {
  return n1 + n2;
}

function act3(age) {
  //let age = 0;
  while(age <= 0) {
    // age = prompt("Ingrese la Edad de la persona: ");
    
    if(age <= 0) {
      console.log("Error: Ingrese una edad valida...");
    }
  }
  
  if(age < 18) {
    console.log("Persona es Menor de Edad");
  } else if (age < 65) {
    console.log("Persona es Adulto");
  } else if (age < 85) {
    console.log("Persona es Adulto Mayor");
  } else {
    console.log("Persona es de Años Dorados");
  }
}

act3(23);