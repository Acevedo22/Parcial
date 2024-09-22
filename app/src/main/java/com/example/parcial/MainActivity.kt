package com.example.parcial

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.adapters.ListViajesAdapters
import com.example.parcial.bd.DbViajes
import com.example.parcial.entidades.Viajes
import java.util.ArrayList

/*
class MainActivity : ComponentActivity() {

    private lateinit var btnCrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCrear = findViewById(R.id.btnCrear)

        btnCrear.setOnClickListener {
            val bdHelper = DbHelper(this)
            val bd: SQLiteDatabase = bdHelper.writableDatabase

            if (bd != null) {
                Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_nuevo -> {
                nuevoRegistro()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun nuevoRegistro() {
        val intent = Intent(this, NuevoActivity::class.java)
        startActivity(intent)
    }
}
*/
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var txtBuscar: SearchView
    private lateinit var listviajes:  RecyclerView
    private lateinit var listArrayVerbs: ArrayList<Viajes>
    private lateinit var adapter: ListViajesAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Video 3
        txtBuscar = findViewById(R.id.txtSearch)
        listviajes = findViewById(R.id.listVerbs)
        listviajes.layoutManager = LinearLayoutManager(this)
        val dbViajes= DbViajes(this)

        listArrayVerbs = ArrayList()

        adapter = ListViajesAdapters(dbViajes.showViajes())
        listviajes.adapter = adapter

        txtBuscar.setOnQueryTextListener(this)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNuevo -> {
                goToInsert()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun goToInsert() {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        adapter.filtrado(s)
        return false
    }
    }