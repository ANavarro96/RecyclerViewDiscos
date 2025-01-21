package com.example.recyclerviewejemplo.dao

import DiscoDatabaseHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.recyclerviewejemplo.R
import com.example.recyclerviewejemplo.modelo.Disco

class DiscoDAO(var context: Context) {
    lateinit var db : DiscoDatabaseHelper

    companion object {
        var listaDiscos = ArrayList<Disco>()
    }


     fun conectarseBD(){
         db = DiscoDatabaseHelper(context)
     }


    fun addDisco(disco: Disco){
        listaDiscos.add(disco)
        db.insertDisco(disco)
    }

    fun deleteByPosition(pos: Int){
        listaDiscos[pos].id?.let { db.deleteDisco(it) }
        listaDiscos.removeAt(pos)
    }

    fun updateByPosition(pos: Int){
        var discoModificar = listaDiscos[pos]
        discoModificar.nombre = "MODIFICADO"
        discoModificar.portada = R.drawable.marvin_llorar
        db.updateDisco(discoModificar)
        listaDiscos.set(pos, discoModificar)
    }

    fun obtenerDiscos(){
        listaDiscos = db.getAllDiscos()
    }


}