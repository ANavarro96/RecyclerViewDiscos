package com.example.recyclerviewejemplo.dao

import com.example.recyclerviewejemplo.R
import com.example.recyclerviewejemplo.modelo.Disco

class DiscoDAO {
    companion object {
        var listaDiscos = ArrayList<Disco>()
    }

    fun addDisco(disco: Disco){
        listaDiscos.add(disco)
    }

    fun deleteByPosition(pos: Int){
        listaDiscos.removeAt(pos)
    }

    fun updateByPosition(pos: Int){
        listaDiscos.set(pos, Disco("MODIFICADO", R.drawable.marvin_llorar))
    }


}