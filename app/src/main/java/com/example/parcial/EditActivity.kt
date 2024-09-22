package com.example.parcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes

class EditActivity : AppCompatActivity() {
    // Elementos de la interfaz
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

        // Inicializar vistas
        txtDestino = findViewById(R.id.txtDestino)
        txtFechaInicio = findViewById(R.id.txtFechaInicio)
        txtFechaFin = findViewById(R.id.txtFechaFin)
        txtLugares = findViewById(R.id.txtLugares)
        txtActividades = findViewById(R.id.txtActividades)
        btnSave = findViewById(R.id.btnSave)

        // Obtener ID del viaje desde los extras
        if (savedInstanceState == null) {
            val extras = intent.extras
            id = extras?.getInt("ID") ?: 0
        } else {
            id = savedInstanceState.getSerializable("ID") as Int
        }

        // Crear instancia de la base de datos y cargar los datos del viaje
        val dbViajes = DbViajes(this)
        viaje = dbViajes.showViaje(id)!!

        // Cargar los datos del viaje en los campos de texto
        txtDestino.setText(viaje.Destino)
        txtFechaInicio.setText(viaje.Fecha_Inicio)
        txtFechaFin.setText(viaje.Fecha_Fin)
        txtLugares.setText(viaje.Lugares)
        txtActividades.setText(viaje.Actividades)

        // Configurar el botón para guardar los cambios
        btnSave.setOnClickListener {
            val destinoText = txtDestino.text.toString()
            val fechainicioText = txtFechaInicio.text.toString()
            val fechaFinText = txtFechaFin.text.toString()
            val lugaresText = txtLugares.text.toString()
            val actividadesText = txtActividades.text.toString()

            // Validar campos de texto
            if (isValidText(destinoText) && isValidText(fechainicioText) &&
                isValidText(fechaFinText) && isValidText(lugaresText) && isValidText(actividadesText)) {

                // Intentar editar el viaje
                val correcto = dbViajes.editViaje(id, destinoText, fechainicioText, fechaFinText, lugaresText, actividadesText)

                if (correcto) {
                    Toast.makeText(this@EditActivity, "Registro Modificado", Toast.LENGTH_LONG).show()
                    goToShow() // Navegar a la actividad de visualización
                } else {
                    Toast.makeText(this@EditActivity, "Error al modificar registro", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@EditActivity, "Ingrese datos válidos en todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Navegar a la actividad de visualización
    private fun goToShow() {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    // Validar texto utilizando expresiones regulares
    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }
}
