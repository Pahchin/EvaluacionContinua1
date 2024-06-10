package pe.edu.idat.ec1_numero

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextLimite = findViewById<EditText>(R.id.editTextLimite)
        val botonVerificar = findViewById<Button>(R.id.botonVerificar)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        botonVerificar.setOnClickListener {
            val limite = editTextLimite.text.toString().toIntOrNull()
            if (limite != null) {
                val numerosPerfectos = encontrarNumerosPerfectos(limite)
                textViewResultado.text = if (numerosPerfectos.isNotEmpty()) {
                    "Números perfectos hasta $limite: ${numerosPerfectos.joinToString(", ")}"
                } else {
                    "No hay números perfectos hasta $limite."
                }
            } else {
                textViewResultado.text = "Por favor, ingrese un número válido."
            }
        }
    }

    private fun encontrarNumerosPerfectos(limite: Int): List<Int> {
        val numerosPerfectos = mutableListOf<Int>()
        for (num in 1..limite) {
            if (esNumeroPerfecto(num)) {
                numerosPerfectos.add(num)
            }
        }
        return numerosPerfectos
    }

    private fun esNumeroPerfecto(numero: Int): Boolean {
        var suma = 0
        for (i in 1 until numero) {
            if (numero % i == 0) {
                suma += i
            }
        }
        return suma == numero
    }
}
