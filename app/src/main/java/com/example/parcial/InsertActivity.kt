package com.example.parcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.parcial.bd.DbViajes

class InsertActivity : AppCompatActivity() {

    private lateinit var txtDestino: EditText
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText

    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        txtDestino = findViewById(R.id.txtDestino)
        text1= findViewById(R.id.txtFechaInicio)
        text2= findViewById(R.id.txtFechaFin)
        text3= findViewById(R.id.txtLugares)
        text4= findViewById(R.id.txtActividades)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val destino = txtDestino.text.toString()
            val fechain = text1.text.toString()
            val fechafin = text2.text.toString()
            val lugares = text3.text.toString()
            val actividades = text4.text.toString()


            if (isValidText(destino) && isValidText(fechain) && isValidText(fechafin) && isValidText(
                    lugares
                ) && isValidText(actividades)
            ) {
                val DbViajes = DbViajes(this@InsertActivity)
                val id = DbViajes.insertarViaje(
                    destino,
                    fechain,
                    fechafin,
                    lugares,
                    actividades
                )

                if (id > 0) {
                    Toast.makeText(this@InsertActivity, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                    limpiar()
                } else {
                    Toast.makeText(this@InsertActivity, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@InsertActivity, "Ingrese datos válidos en todos los campos", Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun limpiar() {
        txtDestino.text.clear()
        text1.text.clear()
        text2.text.clear()
        text3.text.clear()
        text4.text.clear()

    }
    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }
}