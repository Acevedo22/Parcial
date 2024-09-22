package com.example.parcial

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.adapters.ListViajesAdapters
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    // Elementos de la interfaz
    private lateinit var txtBuscar: SearchView
    private lateinit var listviajes: RecyclerView
    private lateinit var listArrayVerbs: ArrayList<Viajes>
    private lateinit var adapter: ListViajesAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        txtBuscar = findViewById(R.id.txtSearch)
        listviajes = findViewById(R.id.listViajes)
        listviajes.layoutManager = LinearLayoutManager(this)

        // Crear instancia de la base de datos y cargar los viajes
        val dbViajes = DbViajes(this)
        listArrayVerbs = ArrayList()
        adapter = ListViajesAdapters(dbViajes.showViajes())
        listviajes.adapter = adapter

        // Configurar el listener para la búsqueda
        txtBuscar.setOnQueryTextListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú de opciones
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Manejar la selección de ítems del menú
        when (item.itemId) {
            R.id.menuNuevo -> {
                goToInsert() // Ir a la actividad de inserción
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Método para navegar a la actividad de inserción
    private fun goToInsert() {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
    }

    // Métodos de búsqueda
    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        adapter.filtrado(s) // Filtrar la lista según el texto ingresado
        return false
    }
}
