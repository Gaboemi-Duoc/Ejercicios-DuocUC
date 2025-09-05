// Clases
open class Vehicle(
    val tipoMotor : String,
    val marca : String,
    val hP : Int,
    var color : String
) {
    open fun verStats() : String = "Vehiculo\nMarca: $marca \nTipo Motor: $tipoMotor " +
            "\nCaballos de Fuerza: $hP \nColor: $color"

}

class Camioneta(
    tipoMotor : String, marca : String, hP : Int, color : String,
    val fourByFour : Boolean, val carga : Double, val todoTerreno : Boolean
) : Vehicle(tipoMotor, marca, hP, color) {
    override fun verStats() : String = "Camioneta\n  Marca: $marca\n  Tipo Motor: $tipoMotor" +
            "\n  Caballos de Fuerza: $hP\n  Color: $color\n  Carga: $carga\n  Es 4x4: $fourByFour\n  Es Todoterreno: $todoTerreno"

}

class Moto(
    tipoMotor : String, marca : String, hP : Int, color : String,
    val tipo : String, val cc : Double
) : Vehicle(tipoMotor, marca, hP, color) {
    override fun verStats() : String = "Moto\n  Marca: $marca\n  Tipo Motor: $tipoMotor" +
            "\n  Caballos de Fuerza: $hP\n  Color: $color\n  CC: $cc\n  Tipo: $tipo"

}

// Variables Vehiculos
var vehicle1 : Vehicle = Vehicle(
    tipoMotor = "Electrico",
    marca = "Toyota",
    hP = 1200,
    color = "Naranja"
)
var vehicle2 : Camioneta = Camioneta(
    tipoMotor = "Diesel",
    marca = "Ford",
    hP = 3000,
    color = "Negro",
    fourByFour = true,
    carga = 75f,
    todoTerreno = true
)
var vehicle3 : Moto = Moto(
    tipoMotor = "Gas",
    marca = "Ducati",
    hP = 1000,
    color = "Rojo",
    tipo = "Standard",
    cc = 200f
)

var consecionaria = listOf<Vehicle>(vehicle1,vehicle2,vehicle3)

// Funcion "Main"
fun main() {
    consecionaria.forEach { it.verStats() }
}

