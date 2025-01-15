package com.example.recyclerviewejemplo.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewejemplo.dao.DiscoDAO
import com.example.recyclerviewejemplo.R
import com.example.recyclerviewejemplo.modelo.Eventos

class AdaptadorDiscos(private val interfaz : Eventos): RecyclerView.Adapter<AdaptadorDiscos.ViewHolderDisco>() {


    inner class ViewHolderDisco(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nombre : TextView = itemView.findViewById(R.id.textView)
        var portada : ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener{
                interfaz.click(adapterPosition)
            }
            itemView.setOnLongClickListener { interfaz.clickLargo(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDisco {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.layout_disco,parent,false)
        return ViewHolderDisco(item)
    }

    override fun getItemCount(): Int {
        return DiscoDAO.listaDiscos.size
    }

    override fun onBindViewHolder(holder: ViewHolderDisco, position: Int) {
        val disco = DiscoDAO.listaDiscos[position]
        holder.nombre.text = disco.nombre
        holder.portada.setImageResource(disco.portada)
        println("Aqui en la posicion " + position)

    }
}