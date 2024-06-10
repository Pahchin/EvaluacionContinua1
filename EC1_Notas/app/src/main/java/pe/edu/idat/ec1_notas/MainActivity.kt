package pe.edu.idat.ec1_notas

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

        val editTextNota1 = findViewById<EditText>(R.id.editTextNota1)
        val editTextNota2 = findViewById<EditText>(R.id.editTextNota2)
        val editTextNota3 = findViewById<EditText>(R.id.editTextNota3)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        buttonCalcular.setOnClickListener {
            val nota1 = editTextNota1.text.toString().toDoubleOrNull()
            val nota2 = editTextNota2.text.toString().toDoubleOrNull()
            val nota3 = editTextNota3.text.toString().toDoubleOrNull()

            if (nota1 != null && nota2 != null && nota3 != null) {
                val promedio = (nota1 + nota2 + nota3) / 3
                val promedioFormateado = String.format("%.2f", promedio)
                textViewResultado.text = if (promedio >= 70) {
                    "El alumno aprueba con un promedio de $promedioFormateado."
                } else {
                    "El alumno reprueba con un promedio de $promedioFormateado."
                }
            } else {
                textViewResultado.text = "Por favor, ingrese todas las calificaciones válidas."
            }
        }
    }
}
