package com.example.calculadorlayouts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.math.PI
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    lateinit var txAlto: EditText
    lateinit var txAncho: EditText
    lateinit var txResultado: TextView
    lateinit var figuraSeleccionada : TipoFigura


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



        //Habilitamos los TexTview según lo que elija
        btnCuadrado.setOnClickListener { habilitarEditText(true, false) }
        btnRectangulo.setOnClickListener { habilitarEditText(true, true) }
        btnTriangulo.setOnClickListener { habilitarEditText(true, false) }
        btnCirculo.setOnClickListener { habilitarEditText(true, false) }
        btnPentagono.setOnClickListener { habilitarEditText(true, false) }
        btnHexagono.setOnClickListener { habilitarEditText(true, false) }

        //Se crea un array con los botones creados
         val botonesNumeros = arrayOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        // Configuramos el listener para que cuando pinche click se llame a la función agregue el numero
        for (boton in botonesNumeros) {
            boton.setOnClickListener { agregarNumero(boton.text.toString()) }
        }


        // Configura el listener para desactivar el teclado cuando los EditText están en foco
        val cambioEstadoEditText = View.OnFocusChangeListener { _, focus ->
            if (focus) {
                ocultarTeclado()
            }
        }
        // Cuando el usuario selecciona una figura cambiamos el hint en función de la figura y así
        //orientamos al usuario que exactamente se le pide.


        btnCuadrado.setOnClickListener { configurarFigura(TipoFigura.CUADRADO, "Ingrese el lado", "") }
        btnRectangulo.setOnClickListener { configurarFigura(TipoFigura.RECTANGULO, "Ingrese el largo", "Ingrese el ancho") }
        btnTriangulo.setOnClickListener { configurarFigura(TipoFigura.TRIANGULO,"Ingrese la base","Ingrese la altura") }
        btnCirculo.setOnClickListener { configurarFigura(TipoFigura.CIRCULO,"Ingrese el radio","") }
        btnPentagono.setOnClickListener { configurarFigura(TipoFigura.PENTAGONO,"Ingrese el lado","") }
        btnHexagono.setOnClickListener { configurarFigura(TipoFigura.HEXAGONO,"Ingrese el lado","") }




        txAlto=findViewById(R.id.txAlto)
        txAlto.onFocusChangeListener=cambioEstadoEditText

        txAncho=findViewById(R.id.txAncho)
        txAncho.onFocusChangeListener=cambioEstadoEditText

        txResultado=findViewById(R.id.txResultado)


    }

    private fun habilitarEditText(alto: Boolean, ancho: Boolean) {
        txAlto.isEnabled = alto
        txAncho.isEnabled = ancho
    }

    private fun agregarNumero(numero: String) {

        // Verifica qué EditText está activo y agrega el número correspondiente
        when {
            txAlto.isFocused -> {

                val textoActual = txAlto.text.toString()
                txAlto.setText(textoActual + numero)
            }
            txAncho.isFocused -> {

                val textoActual = txAncho.text.toString()
                txAncho.setText(textoActual + numero)
            }
        }
    }
    private fun configurarFigura(tipoFigura: TipoFigura, hintAlto: String,hintAncho: String) {
       // habilitarEditText(true, true)
        figuraSeleccionada = tipoFigura
        txAlto.hint = hintAlto
        txAncho.hint = hintAncho
        txAlto.requestFocus()

        ocultarTeclado()
    }
    //Función para ocultar el teclado
    private fun ocultarTeclado() {
        var ocultar = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ocultar.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
    fun onCalcular(view: View) {

        var lado = txAlto.text.toString().toIntOrNull() ?: 0
        var ancho = txAncho.text.toString().toIntOrNull() ?: 0
        var resultado : Double

        when (figuraSeleccionada) {

            TipoFigura.CUADRADO-> {
                resultado = (lado*lado).toDouble()
                  txResultado.text = "Área cuadrado es: $resultado"
            }
            TipoFigura.RECTANGULO-> {
                resultado = (lado*ancho).toDouble()
                txResultado.text = "Área rectángulo es: $resultado"
            }
            TipoFigura.TRIANGULO->{
                //lado = base
                //ancho=altura

                resultado = (1/2 * lado*ancho).toDouble()
                txResultado.text = "Área triángulo es: $resultado"
            }

            TipoFigura.CIRCULO ->{
                //lado= radio
                resultado = PI*lado*lado
                txResultado.text = "Área círculo es: $resultado"
            }
            TipoFigura.PENTAGONO ->{
               resultado = 1/4 * sqrt(5.0*(5.0+2.0* sqrt(5.0))*lado*lado)
                txResultado.text = "Área pentágono es: $resultado"
            }
            TipoFigura.HEXAGONO ->{
                resultado= (3* sqrt(3.0)/2)*lado*lado
                txResultado.text = "Área hexágono es: $resultado"
            }
        }
    }

    private fun onBorrar(view: View){
        txAlto.setText("")
        txAncho.setText("")
        txResultado.text = ""
    }

    //Se crean los tipos de figuras como enumerados
    enum class TipoFigura {
        CUADRADO,
        RECTANGULO,
        TRIANGULO,
        CIRCULO,
        PENTAGONO,
        HEXAGONO

    }



}