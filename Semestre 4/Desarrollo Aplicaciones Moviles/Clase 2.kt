/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

val producto1 = mapOf(
    "name" to "Laptop Gamer",
    "price" to 1250.50,
    "category" to "Tecnologia"
)
val producto2 = mapOf(
    "name" to "Libro de Programacion en Kotlin",
    "price" to 45.99,
    "category" to "Libros"
)
val inventario = listOf(producto1, producto2)

val persona1 = Persona(
    nombre = "Gabriel",
    run = 65007165,
    fechaNacimiento = "9 de Mayo",
    edad = 23
)
val persona2 = Persona(
    nombre = "Fernando",
    run = 52353881,
    fechaNacimiento = "24 de Enero",
    edad = 46
)
val empleado1 = Empleado(
    nombre = "Juana",
    run = 65431102,
    fechaNacimiento = "7 de Marzo",
    edad = 26, cargo = "Manager",
    sueldo = 1855000
)
var informable = listOf(persona1,persona2,empleado1)

// Main Function
fun main() {
    println(busqueda("Laptop Gamer", inventario))
    println(calculo(inventario))
    println(filtro("Libros", inventario))
    println(onlyNames(inventario))
    println(onlyNames(filtro("Tecnologia", inventario)))
    informable.forEach { it.mostrarInfo() }
    println("Sueldo de ${empleado1.nombre}: $${empleado1.calcularBonus()}")
}

fun busqueda(nameProduct: String, inventario: List<Map<String, Any>>): Map<String, Any>? {
    var returnProduct: Map<String, Any>? = null
    for (product in inventario) {
        if(product["name"] == nameProduct) {
            returnProduct = product
        }
    }
    return returnProduct
}

fun calculo(inventario: List<Map<String, Any>>): Double {
    var totalPrice = 0.0
    val numProducts = inventario.count()
    inventario.forEach {
        totalPrice += (it["price"] as? Double) ?: 0.0 // To make it safer, as it could return null or other
    }
    return totalPrice / numProducts
}

fun filtro(category: String, inventario: List<Map<String, Any>>): List<Map<String, Any>> = inventario.filter { it["category"] == category }

fun onlyNames(inventario: List<Map<String, Any>>): List<Map<String, Any>> = inventario.map {
    product -> product.filterKeys {
        mapKey -> mapKey == "name"
    }
} // without the mapKey -> , the reference defaults to 'it', same with mapKey ->

open class Persona(
    var nombre : String,
    val run : Int,
    val fechaNacimiento : String,
    var edad : Int) {
    open fun mostrarInfo() {
        println("Datos de $nombre \n - Nombre: $nombre\n - Run: $run\n - Fecha de Nacimiento: " +
                "$fechaNacimiento\n - Edad: $edad")
    }
}

class Empleado(
    nombre : String, run : Int,
    fechaNacimiento : String, edad : Int,
    var cargo : String, private var sueldo : Int) : Persona(nombre,run,fechaNacimiento,edad) {
    override fun mostrarInfo() {
        println("Datos de $nombre \n - Nombre: $nombre\n - Run: $run\n - Fecha de Nacimiento: " +
                "$fechaNacimiento\n - Edad: $edad\n - Cargo: $cargo\n - Sueldo: $sueldo")
    }
    fun calcularBonus() = (sueldo * 1.15).toInt()
}
