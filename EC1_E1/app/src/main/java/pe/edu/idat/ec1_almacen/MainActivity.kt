package pe.edu.idat.ec1_almacen

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

        val editTextPrice = findViewById<EditText>(R.id.editTextPrice)
        val editTextQuantity = findViewById<EditText>(R.id.editTextQuantity)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            val price = editTextPrice.text.toString().toDoubleOrNull()
            val quantity = editTextQuantity.text.toString().toIntOrNull()

            if (price != null && quantity != null) {
                val total = calculateTotalPrice(price, quantity)
                textViewResult.text = "Total a pagar: $total"
            } else {
                textViewResult.text = "Por favor, ingrese valores válidos"
            }
        }
    }

    private fun calculateTotalPrice(price: Double, quantity: Int): Double {
        val total = price * quantity
        val discount = when {
            quantity > 20 -> 0.10
            quantity > 10 -> 0.05
            else -> 0.0
        }
        return total * (1 - discount)
    }
}
