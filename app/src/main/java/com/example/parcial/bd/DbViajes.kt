package com.example.parcial.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Build
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.example.parcial.DbHelper
import com.example.parcial.entidades.Viajes
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class DbViajes(@Nullable context: Context?) : DbHelper(context) {

    // Contexto para acceder a los recursos de la aplicación
    private val context: Context = context!!

    // Insertar un nuevo viaje en la base de datos
    @RequiresApi(Build.VERSION_CODES.O)
    fun insertarViaje(destino: String, fecha_inicio: String, fecha_fin: String, lugares: String, actividades: String): Long {
        var id: Long = 0
        try {
            val dbHelper = DbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put("destino", destino)
                put("fecha_inicio", fecha_inicio)
                put("fecha_fin", fecha_fin)
                put("lugares", lugares)
                put("actividades", actividades)
            }

            id = db.insert(TABLE_VIAJES, null, values) // Insertar el viaje y devolver el ID generado
        } catch (ex: Exception) {
            ex.printStackTrace() // Registrar el error
        }
        return id
    }

    // Mostrar todos los viajes almacenados en la base de datos
    fun showViajes(): ArrayList<Viajes> {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        val listaViajes = ArrayList<Viajes>()
        var cursorViajes: Cursor? = null

        try {
            cursorViajes = db.rawQuery("SELECT * FROM $TABLE_VIAJES", null)

            if (cursorViajes.moveToFirst()) {
                do {
                    val viaje = Viajes().apply {
                        id = cursorViajes.getInt(0)
                        Destino = cursorViajes.getString(1)
                        Fecha_Inicio = cursorViajes.getString(2)
                        Fecha_Fin = cursorViajes.getString(3)
                        Lugares = cursorViajes.getString(4)
                        Actividades = cursorViajes.getString(5)
                    }
                    listaViajes.add(viaje) // Agregar cada viaje a la lista
                } while (cursorViajes.moveToNext())
            }
        } finally {
            cursorViajes?.close() // Cerrar el cursor después de usarlo
        }

        return listaViajes
    }

    // Mostrar un viaje específico basado en su ID
    fun showViaje(id: Int): Viajes? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var viaje: Viajes? = null
        var cursorViajes: Cursor? = null

        try {
            cursorViajes = db.rawQuery("SELECT * FROM $TABLE_VIAJES WHERE id = $id LIMIT 1", null)

            if (cursorViajes.moveToFirst()) {
                viaje = Viajes().apply {
                    this.id = cursorViajes.getInt(0)
                    Destino = cursorViajes.getString(1)
                    Fecha_Inicio = cursorViajes.getString(2)
                    Fecha_Fin = cursorViajes.getString(3)
                    Lugares = cursorViajes.getString(4)
                    Actividades = cursorViajes.getString(5)
                }
            }
        } finally {
            cursorViajes?.close()
        }

        return viaje
    }

    // Editar un viaje específico en la base de datos
    fun editViaje(id: Int, destino: String, fecha_inicio: String, fecha_fin: String, lugares: String, actividades: String): Boolean {
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            val values = ContentValues().apply {
                put("Destino", destino)
                put("Fecha_Inicio", fecha_inicio)
                put("Fecha_Fin", fecha_fin)
                put("Lugares", lugares)
                put("Actividades", actividades)
            }

            db.update(TABLE_VIAJES, values, "id = ?", arrayOf(id.toString())) // Actualizar el viaje por su ID
            correcto = true
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            db.close() // Asegurarse de cerrar la base de datos
        }

        return correcto
    }

    // Eliminar un viaje de la base de datos
    fun deleteViaje(id: Int): Boolean {
        var correcto = false
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_VIAJES WHERE id = '$id'") // Ejecutar la eliminación del viaje
            correcto = true
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            db.close()
        }

        return correcto
    }
}
