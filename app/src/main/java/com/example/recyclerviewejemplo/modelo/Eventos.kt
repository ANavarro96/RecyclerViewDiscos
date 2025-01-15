package com.example.recyclerviewejemplo.modelo

interface Eventos {
    fun click(pos : Int)
    fun clickLargo(pos : Int) : Boolean
}