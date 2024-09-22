package com.example.parcial.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.annotation.Nullable
import com.example.parcial.DbHelper
import com.example.parcial.entidades.Viajes
import java.util.ArrayList

class DbViajes(@Nullable context: Context?) : DbHelper(context) {
    private val context: Context

    init {
        this.context = context!!
    }

    fun insertarViaje(destino: String, fechaInicio: String, fechaFin: String, lugares: String, actividades :String): Long {
        var id: Long = 0
        try {
            val dbHelper = DbHelper(context)
            val db = dbHelper.getWritableDatabase()

            val values = ContentValues()
            values.put("destino", destino)
            values.put("fecha de inicio", fechaInicio)
            values.put("fecha de fin", fechaFin)
            values.put("lugares", lugares)
            values.put("Actividades", actividades)

            id = db.insert(TABLE_VIAJES, null, values)
        } catch (ex: Exception) {
            ex.toString()
        }
        return id
    }

    fun showViajes(): ArrayList<Viajes> {
        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        val listaViajes = java.util.ArrayList<Viajes>()
        var viaje: Viajes?
        var cursorViajes: Cursor? = null

        cursorViajes = db.rawQuery("SELECT * FROM $TABLE_VIAJES", null)

        if (cursorViajes.moveToFirst()) {
            do{
            viaje = Viajes()
            viaje.id = cursorViajes.getInt(0)
            viaje.Destino = cursorViajes.getString(1)
            viaje.Fecha_Inicio = cursorViajes.getString(2)
            viaje.Fecha_Fin = cursorViajes.getString(3)
            viaje.Lugares = cursorViajes.getString(4)
            viaje.Actividades = cursorViajes.getString(5)
            listaViajes.add(viaje)
        } while (cursorViajes.moveToNext())
        }
        cursorViajes.close()

        return listaViajes
    }
        fun showViaje(id: Int): Viajes? {
            val dbHelper = DbHelper(context)
            val db = dbHelper.writableDatabase

            var viaje: Viajes? = null
            var cursorViajes: Cursor? = null
            cursorViajes = db.rawQuery("SELECT * FROM $TABLE_VIAJES WHERE id = $id LIMIT 1", null)

            if (cursorViajes.moveToFirst()) {
                viaje = Viajes()
                viaje.id = cursorViajes.getInt(0)
                viaje.Destino = cursorViajes.getString(1)
                viaje.Fecha_Inicio = cursorViajes.getString(2)
                viaje.Fecha_Fin = cursorViajes.getString(3)
                viaje.Lugares = cursorViajes.getString(4)
                viaje.Actividades = cursorViajes.getString(5)

            }
            cursorViajes.close()

            return viaje
        }
    fun editViaje(id: Int, Destino: String, Fecha_Inicio: String, Fecha_Fin: String, Lugares: String, Actividades: String): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        try {
            val values = ContentValues()
            values.put("Destino", Destino)
            values.put("Fecha_Inicio", Fecha_Inicio)
            values.put("Fecha_Fin", Fecha_Fin)
            values.put("Lugares", Lugares)
            values.put("Actividades", Actividades)

            db.update(TABLE_VIAJES, values, "id = ?", arrayOf(id.toString()))
            correcto = true
        } catch (ex: Exception) {
            ex.toString()
            correcto = false
        } finally {
            db.close()
        }

        return correcto
    }
    fun deleteViaje(id: Int): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_VIAJES WHERE id = '$id'")
            correcto = true
        } catch (ex: Exception) {
            ex.toString()
            correcto = false
        } finally {
            db.close()
        }

        return correcto
    }


}