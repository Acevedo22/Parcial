package com.example.parcial

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes

class ShowActivity  : AppCompatActivity (){
    class ShowActivity : AppCompatActivity() {

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

            txtDestino = findViewById(R.id.txtDestino)
            txtFechaInicio = findViewById(R.id.txtFechaInicio)
            btnEdit = findViewById(R.id.btngoToEdit)
            btnDelete = findViewById(R.id.btngoToDelete)
            txtFechaFin = findViewById(R.id.txtFechaFin)
            TxtLugares = findViewById(R.id.txtLugares)
            TxtActividades = findViewById(R.id.txtActividades)

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
                TxtLugares.setText(viaje.Lugares)
                TxtActividades.setText(viaje.Actividades)

                //btnSave.visibility = View.INVISIBLE
                /* et_verb.inputType = InputType.TYPE_NULL
                 Ind_Yo.inputType = InputType.TYPE_NULL
                 Ind_Tu.inputType = InputType.TYPE_NULL
                 Ind_El_Ella_Usted.inputType = InputType.TYPE_NULL
                 Ind_Nosotros.inputType = InputType.TYPE_NULL
                 Ind_Vosotros.inputType = InputType.TYPE_NULL
                 Ind_Ellos.inputType = InputType.TYPE_NULL
                 Imp_Tu.inputType = InputType.TYPE_NULL
                 Imp_Nosotros.inputType = InputType.TYPE_NULL
                 Imp_Ellos_Ellas_Ustedes.inputType = InputType.TYPE_NULL*/
            }
            btnEdit.setOnClickListener {
                goToEdit()
            }
            btnDelete.setOnClickListener {
                val builder = AlertDialog.Builder(this@ShowActivity)
                builder.setMessage("¿Desea eliminar este verbo?")
                    .setPositiveButton("SI") { dialog, which ->
                        if (DbViajes.deleteViaje(id)) {
                            goToHome();
                        }
                    }
                    .setNegativeButton("No") { dialog, which ->

                    }
                    .show()
            }

        }

        private fun goToEdit() {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }

        private fun goToHome() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }}