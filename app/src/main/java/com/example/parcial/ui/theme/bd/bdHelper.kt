package com.example.parcial.ui.theme.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class bdHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_VIAJES (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "destino TEXT NOT NULL," +
                    "fecha_inicio DATETIME NOT NULL," +
                    "fecha_fin DATETIME)"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_VIAJES")
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NOMBRE = "viajes.db"
        const val TABLE_VIAJES = "t_viajes"
    }
}
