package com.example.sqlite.data
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductoDao) {

    val productos: Flow<List<Producto>> = dao.getAll()

    suspend fun agregar(nombre: String, precio: Double, stock: Int, tipo: String) {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(tipo.isNotBlank()) {"El tipo no puede estar vacio"}
        require(precio >= 0) { "El precio no puede ser negativo" }
        require(stock >= 0) { "El stock no puede ser negativo" }
        dao.insert(Producto(nombre = nombre.trim(), precio = precio, stock = stock, tipo = tipo))
    }

    suspend fun actualizar(id: Long, nombre: String, precio: Double, stock: Int, tipo: String) {
        require(id > 0) { "Id inválido" }
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(tipo.isNotBlank()) { "El tipo no puede estar vacío" }
        require(precio >= 0) { "El precio no puede ser negativo" }
        require(stock >= 0) { "El stock no puede ser negativo" }
        dao.update(Producto(id = id, nombre = nombre.trim(), precio = precio, stock = stock, tipo = tipo))
    }

    suspend fun eliminar(product: Producto) = dao.delete(product)
    suspend fun obtener(id: Long) = dao.findById(id)
}
