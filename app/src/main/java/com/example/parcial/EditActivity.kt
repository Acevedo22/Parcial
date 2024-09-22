package com.example.parcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.parcial.R.id
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes

class EditActivity : AppCompatActivity() {
    private lateinit var txtDestino: EditText
    private lateinit var txtFechaInicio: EditText
    private lateinit var txtFechaFin: EditText
    private lateinit var txtLugares: EditText
    private lateinit var txtActividades: EditText
    private lateinit var btnSave: Button

    private lateinit var viaje: Viajes
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        txtDestino = findViewById(R.id.txtDestino)
        txtFechaInicio = findViewById(R.id.txtFechaInicio)
        txtFechaFin = findViewById(R.id.txtFechaFin)
        txtLugares = findViewById(R.id.txtLugares)
        txtActividades = findViewById(R.id.txtActividades)
        btnSave = findViewById(R.id.btnSave)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                id = 0
            } else {
                id = extras.getInt("ID")
            }
        } else {
            id = savedInstanceState.getSerializable("ID") as Int
        }

        val DbViajes = DbViajes(this)
        viaje = DbViajes.showViaje(id)!!

        if (viaje != null) {
            txtDestino.setText(viaje.Destino)
            txtFechaInicio.setText(viaje.Fecha_Inicio)
            txtFechaFin.setText(viaje.Fecha_Fin)
            txtLugares.setText(viaje.Lugares)
            txtActividades.setText(viaje.Actividades)

        }

        btnSave.setOnClickListener {
            val destinoText = txtDestino.text.toString()
            val fechainicioText = txtFechaInicio.text.toString()
            val fechaFinText = txtFechaFin.text.toString()
            val lugaresText = txtLugares.text.toString()
            val actividadesText = txtActividades.text.toString()


            if (isValidText(destinoText) && isValidText(fechainicioText) && isValidText(fechaFinText) && isValidText(
                    lugaresText
                ) && isValidText(actividadesText)
            ) {
                val correcto = DbViajes.editViaje(
                    id, destinoText, fechainicioText, fechaFinText, lugaresText, actividadesText
                )

                if (correcto) {
                    Toast.makeText(this@EditActivity, "Registro Modificado", Toast.LENGTH_LONG)
                        .show()
                    goToShow()
                } else {
                    Toast.makeText(
                        this@EditActivity,
                        "Error al modificar registro",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@EditActivity,
                    "Ingrese datos válidos en todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }
    private fun goToShow() {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }
}