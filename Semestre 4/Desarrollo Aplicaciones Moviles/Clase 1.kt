/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main() {
    val x: Int = 5
    val y: Int = 8
    val result: Int = suma(x, y)
    val result2 = multiplicacion(x, y)
    
    println(result)    
    println(result2)
    
    var name = "Name"
    var age = 64
    
    println("Hello " + name + " and you are " + age + " years old...")
    println("Your age in five years will be " + (age + 5))
    
    menu("hola")
    println(par(23))
    
    println(diaSemana(1))
}

fun diaSemana(dia: Int) = when(dia) {
    1 -> "Lunes"
    2 -> "Martes"
    3 -> "Miercoles"
    4 -> "Jueves"
    5 -> "Viernes"
    6 -> "Sabado"
    7 -> "Domingo"
    else -> "Dia no es Valido..."
}

fun menu(a: String) = when(a) {
    "hola"	-> println("a es 1")
    "b"		-> println("a es 2")
    "c"		-> println("a es 3")
    "d"		-> println("a es 4")
    else	-> println("No es un Valor valido")
}

fun par(n: Int): Boolean = (n % 2) == 0
// fun alternativepar(n: Int): String if ((n % 2) == 0) "Es Par" else "Es Impar" 

fun condicion() {
    print("Ingrese su Edad: ")
    val age = readln()
    if (age.toInt() >= 18) {
        println("Es mayor de edad.")
    } else {
        println("Es menor de edad.")
    }
}

fun suma(n1: Int, n2: Int) = n1 + n2

fun resta(n1: Int, n2: Int) = n1 - n2

fun multiplicacion(n1: Int, n2: Int) = n1 * n2

fun division(n1: Int, n2: Int) = n1 / n2