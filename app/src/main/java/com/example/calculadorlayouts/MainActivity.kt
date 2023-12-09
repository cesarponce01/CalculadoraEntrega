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
    lateinit var figuraSeleccionada: TipoFigura
    //var figura =Figura()

    //Se declaran las figuras
    lateinit var btnCuadrado: ImageButton
    lateinit var btnRectangulo: ImageButton
    lateinit var btnTriangulo: ImageButton
    lateinit var btnCirculo: ImageButton
    lateinit var btnPentagono: ImageButton
    lateinit var btnHexagono: ImageButton

    //Se declaran los botones Numéricos
    lateinit var btn0: Button
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se configura los recursos de las fuguras geométricas
        btnCuadrado = findViewById(R.id.btnCuadrado)
        btnRectangulo = findViewById(R.id.btnRectangulo)
        btnTriangulo = findViewById(R.id.btnTriangulo)
        btnCirculo = findViewById(R.id.btnCirculo)
        btnPentagono = findViewById(R.id.btnPentagono)
        btnHexagono = findViewById(R.id.btnHexagono)

        // Asigna las imágenes a los ImageButton
        btnCuadrado.setImageResource(R.mipmap.cuadrado)
        btnRectangulo.setImageResource(R.mipmap.rectangulo)
        btnTriangulo.setImageResource(R.mipmap.triangulo)
        btnCirculo.setImageResource(R.mipmap.circulo)
        btnPentagono.setImageResource(R.mipmap.pentagono)
        btnHexagono.setImageResource(R.mipmap.hexagono)

        //Se configura los recursos del teclado númérico
        btn0 = findViewById(R.id.btn0)
        btn1= findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        val btnBorrar: Button = findViewById(R.id.btnBorrar)
        btnBorrar.setOnClickListener { onBorrar() }

        //Se crea un array con los botones creados
        val botonesNumeros = arrayOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        // Cuando el usuario selecciona una figura cambiamos el hint en función de la figura y así
        //orientamos al usuario que exactamente se le pide.

        btnCuadrado.setOnClickListener {
            configurarFigura(
                TipoFigura.CUADRADO,
                "Ingrese el lado",
                "",
                true,
                false
            )
        }
        btnRectangulo.setOnClickListener {
            configurarFigura(
                TipoFigura.RECTANGULO,
                "Ingrese el largo",
                "Ingrese el ancho",
                true,
                true
            )
        }
        btnTriangulo.setOnClickListener {
            configurarFigura(
                TipoFigura.TRIANGULO,
                "Ingrese la base",
                "Ingrese la altura",
                true,
                true
            )
        }
        btnCirculo.setOnClickListener {
            configurarFigura(
                TipoFigura.CIRCULO,
                "Ingrese el radio",
                "",
                true,
                false
            )
        }
        btnPentagono.setOnClickListener {
            configurarFigura(
                TipoFigura.PENTAGONO,
                "Ingrese el lado",
                "",
                true,
                false
            )
        }
        btnHexagono.setOnClickListener {
            configurarFigura(
                TipoFigura.HEXAGONO,
                "Ingrese el lado",
                "",
                true,
                false
            )
        }

        // Configuramos el listener para que cuando pinche click se llame a la función agregue cada número
        for (boton in botonesNumeros) {
            boton.setOnClickListener { agregarNumero(boton.text.toString()) }
        }

        txAlto = findViewById(R.id.txAlto)
        txAlto.setOnClickListener { ocultarTeclado() }

        txAncho = findViewById(R.id.txAncho)
        txAncho.setOnClickListener { ocultarTeclado() }

        txResultado = findViewById(R.id.txResultado)
    }


    private fun habilitarEditText(alto: Boolean, ancho: Boolean) {
        txAlto.isEnabled = alto
        txAncho.isEnabled = ancho

        //Si ambos son true los 2 editText son visibles
        if (alto && ancho) {
            txAlto.visibility = View.VISIBLE
            txAncho.visibility = View.VISIBLE
        } else {
            //Caso contrario se esconde la visibilidad del txAncho
            txAlto.visibility = View.VISIBLE
            txAncho.visibility = View.INVISIBLE
        }
    }

    private fun agregarNumero(numero: String) {

        // Verifica qué EditText está activo y agrega el número correspondiente
        when {
            txAlto.isFocused -> {
                ocultarTeclado()
                val textoActual = txAlto.text.toString()
                txAlto.setText(textoActual + numero)

            }

            txAncho.isFocused -> {
                ocultarTeclado()
                val textoActual = txAncho.text.toString()
                txAncho.setText(textoActual + numero)
            }
        }
    }

    private fun configurarFigura(
        tipoFigura: TipoFigura,
        hintAlto: String,
        hintAncho: String,
        alto: Boolean,
        ancho: Boolean
    ) {
        //Borramos los valores que estén anteriormente
        onBorrar()

        //habilitarEditText(false, false)
        figuraSeleccionada = tipoFigura
        txAlto.hint = hintAlto
        txAncho.hint = hintAncho
        txAlto.requestFocus()

        //Habilitamos los editText
        habilitarEditText(alto, ancho)
    }

    //Función para ocultar el teclado
    private fun ocultarTeclado() {

        var ocultar = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ocultar.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    //Función para realizar el cálculo de las oepraciones de cada Figura Geométrica
    fun onCalcular(view: View) {

        var lado = txAlto.text.toString().toIntOrNull() ?: 0
        var ancho = txAncho.text.toString().toIntOrNull() ?: 0

        when (figuraSeleccionada) {

            TipoFigura.CUADRADO -> {
                var resultado = lado * lado
                txResultado.text = "Área cuadrado: $resultado"
            }

            TipoFigura.RECTANGULO -> {
                var resultado = lado * ancho
                txResultado.text = "Área rectángulo: $resultado"
            }

            TipoFigura.TRIANGULO -> {
                //lado = base
                //ancho=altura

                var resultado = (0.5 * lado * ancho)
                txResultado.text = "Área triángulo: $resultado"
            }

            TipoFigura.CIRCULO -> {
                //lado= radio
                var resultado = PI * lado * lado
                var resulFormato = String.format("%.2f", resultado)
                txResultado.text = "Área círculo: $resulFormato"
            }

            TipoFigura.PENTAGONO -> {
                var resultado = 0.25 * sqrt(5 * (5 + 2 * sqrt(5.0))) * lado * lado
                var resulFormato = String.format("%.2f", resultado)
                txResultado.text = "Área pentágono: $resulFormato"
            }

            TipoFigura.HEXAGONO -> {
                var resultado = ((3 * sqrt(3.0)) / 2) * lado * lado
                var resulFormato = String.format("%.2f", resultado)
                txResultado.text = "Área hexágono es: $resulFormato"
            }
        }
    }

    private fun onBorrar() {
        // Se restablecen los valores
        txAlto.setText("")
        txAncho.setText("")

        txResultado.text = "Resultado:"

        //Llamamos a la función habilitar editTex para que los ponga por defecto
        habilitarEditText(true, true)

        txAlto.hint = "Seleccione la figura"
        txAncho.hint = "Seleccione la figura"
        txAlto.requestFocus()

    }
}