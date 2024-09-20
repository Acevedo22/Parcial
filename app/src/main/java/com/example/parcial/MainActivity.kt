package com.example.parcial

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.ui.theme.ParcialTheme
import com.example.parcial.ui.theme.bd.bdHelper

class MainActivity : ComponentActivity() {

    private lateinit var btnCrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCrear = findViewById(R.id.btnCrear)

        btnCrear.setOnClickListener {
            val bdHelper = bdHelper(this@MainActivity)
            val bd: SQLiteDatabase = bdHelper.writableDatabase

            if (bd != null) {
                Toast.makeText(this@MainActivity, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show()
            }

            }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ParcialTheme {
        Greeting("Android")
    }
}