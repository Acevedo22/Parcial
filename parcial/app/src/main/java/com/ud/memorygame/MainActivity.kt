package com.ud.memorygame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var btnBuscar: Button
    private lateinit var btnAgregar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        addLogicToBtns()
    }

    fun addLogicToBtns(){
        btnBuscar = findViewById(R.id.btnBuscar)
        btnAgregar = findViewById(R.id.btnAgregar)

        btnBuscar.setOnClickListener {
            Toast.makeText(this, "Click in btn Buscar  ", Toast.LENGTH_LONG).show()
            goToScreen("Buscar")
        }

        btnAgregar.setOnClickListener{
            Toast.makeText(this, "Click in btn Agregar", Toast.LENGTH_LONG).show()
            goToScreen("Agregar")
        }


    }

    fun goToScreen(Boton: String){
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("boton", Boton)
        startActivity(intent)
    }
}