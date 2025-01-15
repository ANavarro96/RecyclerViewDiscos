package com.example.recyclerviewejemplo.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewejemplo.R

class crearDiscos : AppCompatActivity() {

    lateinit var boton : Button
    lateinit var cuadroTexto : EditText

    fun inicializarVistas(){
        boton = findViewById(R.id.button)
        cuadroTexto = findViewById(R.id.editTextText)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_discos)
        inicializarVistas()


        boton.setOnClickListener {
            if(cuadroTexto.text.isNullOrEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_LONG).show()
            }
            else{
                val intentRespuesta = Intent()
                intentRespuesta.putExtra("nuevoNombre", cuadroTexto.text.toString())
                setResult(RESULT_OK, intentRespuesta)
                finish()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}