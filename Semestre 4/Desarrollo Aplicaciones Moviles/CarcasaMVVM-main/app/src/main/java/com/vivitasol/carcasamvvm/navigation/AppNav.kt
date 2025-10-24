package com.vivitasol.carcasamvvm.navigation

sealed class Route(val route: String) {
    data object Welcome : Route("welcome")
    data object MenuShell : Route("menu_shell") // contenedor con drawer
    data object Option1 : Route("option1")
    data object Option2 : Route("option2")
    data object Option3 : Route("option3")

    //detalle con argumento
    data object Option2Detail : Route("option2/detail/{id}") {
        fun build(id: String) = "option2/detail/$id"
    }

    data object Option4 : Route("option4")

    data object Option5 : Route("option5")
}