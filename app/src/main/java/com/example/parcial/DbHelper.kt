package com.example.parcial

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Clase auxiliar para gestionar la base de datos
public open class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    // Definición de constantes
    companion object {
        private const val DATABASE_VERSION = 2 // Versión de la base de datos
        private const val DATABASE_NOMBRE = "viajes2.db" // Nombre de la base de datos
        const val TABLE_VIAJES = "t_viajes" // Nombre de la tabla de viajes
    }

    // Método llamado cuando se crea la base de datos por primera vez
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        // Crear tabla t_viajes
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_VIAJES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + // Columna ID autoincremental
                    "destino TEXT NOT NULL," + // Columna destino
                    "fecha_inicio DATE NOT NULL," + // Columna fecha de inicio
                    "fecha_fin DATE NOT NULL," + // Columna fecha de fin
                    "lugares TEXT NOT NULL," + // Columna lugares
                    "Actividades TEXT NOT NULL)" // Columna actividades
        )
    }

    // Método llamado cuando la base de datos necesita ser actualizada
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Eliminar tabla si existe y recrearla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_VIAJES")
        onCreate(sqLiteDatabase)
    }
}
