package com.example.parcial

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes

class ShowActivity : AppCompatActivity() {

    // Elementos de la interfaz
    private lateinit var txtDestino: TextView
    private lateinit var txtFechaInicio: TextView
    private lateinit var txtFechaFin: TextView
    private lateinit var TxtLugares: TextView
    private lateinit var TxtActividades: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    private lateinit var viaje: Viajes
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        // Inicializar vistas
        txtDestino = findViewById(R.id.txtDestino)
        txtFechaInicio = findViewById(R.id.txtFechaInicio)
        btnEdit = findViewById(R.id.btngoToEdit)
        btnDelete = findViewById(R.id.btngoToDelete)
        txtFechaFin = findViewById(R.id.txtFechaFin)
        TxtLugares = findViewById(R.id.txtLugares)
        TxtActividades = findViewById(R.id.txtActividades)

        // Obtener el ID del viaje desde los extras
        if (savedInstanceState == null) {
            val extras = intent.extras
            id = extras?.getInt("ID") ?: 0
        } else {
            id = savedInstanceState.getSerializable("ID") as Int
        }

        // Cargar datos del viaje
        val DbViajes = DbViajes(this)
        viaje = DbViajes.showViaje(id)!!

        // Mostrar los datos del viaje en las vistas
        viaje?.let {
            txtDestino.text = it.Destino
            txtFechaInicio.text = it.Fecha_Inicio
            txtFechaFin.text = it.Fecha_Fin
            TxtLugares.text = it.Lugares
            TxtActividades.text = it.Actividades
        }

        // Configurar el listener para el botón de editar
        btnEdit.setOnClickListener {
            goToEdit()
        }

        // Configurar el listener para el botón de eliminar
        btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this@ShowActivity)
            builder.setMessage("¿Desea eliminar este viaje?")
                .setPositiveButton("SI") { dialog, which ->
                    if (DbViajes.deleteViaje(id)) {
                        goToHome()
                    }
                }
                .setNegativeButton("No") { dialog, which -> }
                .show()
        }
    }

    // Navegar a la actividad de edición
    private fun goToEdit() {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    // Navegar a la actividad principal
    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
