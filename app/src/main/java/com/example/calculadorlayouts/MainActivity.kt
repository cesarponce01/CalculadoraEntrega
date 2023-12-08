package com.example.calculadorlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var txAlto: EditText
    lateinit var txAncho: EditText
    lateinit var txResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //Se configura los recursos de las fuguras geométricas
        val btnCuadrado: ImageButton = findViewById(R.id.btnCuadrado)
        val btnRectangulo: ImageButton = findViewById(R.id.btnRectangulo)
        val btnTriangulo: ImageButton = findViewById(R.id.btnTriangulo)
        val btnCirculo: ImageButton = findViewById(R.id.btnCirculo)
        val btnPentagono: ImageButton = findViewById(R.id.btnPentagono)
        val btnHexagono: ImageButton = findViewById(R.id.btnHexagono)



        // Asigna las imágenes a los ImageButton
        btnCuadrado.setImageResource(R.mipmap.cuadrado)
        btnRectangulo.setImageResource(R.mipmap.rectangulo)
        btnTriangulo.setImageResource(R.mipmap.triangulo)
        btnCirculo.setImageResource(R.mipmap.circulo)
        btnPentagono.setImageResource(R.mipmap.pentagono)
        btnHexagono.setImageResource(R.mipmap.hexagono)



        //Se configura los recursos del teclado númérico
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        txAlto=findViewById(R.id.txAlto)
        txAncho=findViewById(R.id.txAncho)
        txResultado=findViewById(R.id.txResultado)




    }



}