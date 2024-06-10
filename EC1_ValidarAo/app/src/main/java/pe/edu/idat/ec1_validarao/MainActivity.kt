package pe.edu.idat.ec1_validarao

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

        val editTextYear = findViewById<EditText>(R.id.editTextYear)
        val buttonValidate = findViewById<Button>(R.id.buttonValidate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonValidate.setOnClickListener {
            val year = editTextYear.text.toString().toIntOrNull()

            if (year != null) {
                val isLeapYear = validateLeapYear(year)
                textViewResult.text = if (isLeapYear) {
                    "El año $year es bisiesto."
                } else {
                    "El año $year no es bisiesto."
                }
            } else {
                textViewResult.text = "Por favor, ingrese un año válido."
            }
        }
    }

    private fun validateLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}
