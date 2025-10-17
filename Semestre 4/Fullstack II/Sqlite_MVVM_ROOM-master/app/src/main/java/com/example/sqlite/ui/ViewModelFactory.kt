package com.example.sqlite.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqlite.data.AppDatabase
import com.example.sqlite.data.ProductRepository


class ProductViewModelFactory(app: Application) : ViewModelProvider.Factory {
    private val repo by lazy {
        val dao = AppDatabase.get(app).productDao()
        ProductRepository(dao)
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ProductViewModel(repo) as T
}
