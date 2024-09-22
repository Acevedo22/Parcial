package com.example.parcial

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public open class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NOMBRE = "viajes2.db"
        const val TABLE_VIAJES = "t_viajes"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_VIAJES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "destino TEXT NOT NULL," +
                    "fecha_inicio TEXT NOT NULL," +
                    "fecha_fin TEXT NOT NULL," +
                    "lugares TEXT NOT NULL," +
                    "Actividades TEXT NOT NULL)"

        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_VIAJES")
        onCreate(sqLiteDatabase)
    }


}
