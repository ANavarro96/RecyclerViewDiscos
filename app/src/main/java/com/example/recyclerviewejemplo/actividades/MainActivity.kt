package com.example.recyclerviewejemplo.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewejemplo.adaptadores.AdaptadorDiscos
import com.example.recyclerviewejemplo.modelo.Disco
import com.example.recyclerviewejemplo.dao.DiscoDAO
import com.example.recyclerviewejemplo.modelo.Eventos
import com.example.recyclerviewejemplo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView :  RecyclerView
    lateinit var fab :  FloatingActionButton
    lateinit var botonBorrar : Button
    lateinit var botonModificar :  Button
    var discoDAO : DiscoDAO = DiscoDAO()
    lateinit var adaptador: AdaptadorDiscos

   val funcionResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
       resultado ->
           if(resultado.resultCode == RESULT_OK){
               val datos = resultado.data
               val nuevoNombre = datos?.getStringExtra("nuevoNombre")!!
               discoDAO.addDisco(Disco(nuevoNombre, R.drawable.marvin))
               adaptador.notifyItemInserted(DiscoDAO.listaDiscos.size - 1)
           }
   }

    fun sacarTostadita ( disco: Disco) {
        Toast.makeText(this, disco.nombre,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val e = object : Eventos {
            override fun click(pos: Int) {
                sacarTostadita(DiscoDAO.listaDiscos[pos])
            }

            override fun clickLargo(pos: Int) : Boolean {
                Toast.makeText(applicationContext,"VIVA IRON MAIDEN",Toast.LENGTH_LONG).show()
                return true
            }

        }

        recyclerView = findViewById(R.id.rv)
        fab = findViewById(R.id.floatingActionButton)
        botonBorrar = findViewById(R.id.botonBorrar)

        botonBorrar.setOnClickListener {
             discoDAO.deleteByPosition(0)
            adaptador.notifyItemRemoved(0)
        }

        botonModificar = findViewById(R.id.botonModificar)

        botonModificar.setOnClickListener {
            discoDAO.updateByPosition(0)
            adaptador.notifyItemChanged(0)
        }

        adaptador = AdaptadorDiscos(e)

        for( i in 0..10) DiscoDAO().addDisco(Disco("Disco " + i, R.drawable.marvin))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL))
        recyclerView.adapter = adaptador

        println(adaptador.itemCount)


        fab.setOnClickListener {
            val intent = Intent(this, crearDiscos::class.java)
            funcionResultado.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



}