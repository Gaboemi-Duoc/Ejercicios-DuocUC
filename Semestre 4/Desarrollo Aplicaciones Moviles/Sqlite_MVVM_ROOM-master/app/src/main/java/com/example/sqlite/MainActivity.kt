package com.example.sqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.sqlite.ui.ProductViewModel
import com.example.sqlite.ui.ProductViewModelFactory
import com.example.sqlite.ui.screen.ProductListScreen


class MainActivity : ComponentActivity() {
    private val vm: ProductViewModel by viewModels { ProductViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ProductListScreen(vm) }
    }
}
