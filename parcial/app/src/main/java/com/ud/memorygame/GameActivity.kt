package com.ud.memorygame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ud.memorygame.logic.Game

class GameActivity : AppCompatActivity() {
    var level: String = "Agregar"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        if (bundle != null) {
            level = bundle.getString("Agregar").toString()
        }
    }
}