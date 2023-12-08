package com.example.calculadorlayouts

import android.view.View
import android.widget.TextView

class Figura {

    var alto : Int
    var ancho : Int=0
    var resultado : Int

    constructor(alto: Int, ancho: Int) {
        this.alto = alto
        this.ancho = ancho
        this.resultado = 0
    }

    constructor(alto: Int) {
        this.alto = alto
        this.resultado = 0
    }
    fun onCalcular(view: View){

    }

    fun onBorrar(){

    }



}