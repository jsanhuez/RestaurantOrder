package com.jsanhueza.prog0523.u1.restaurantorder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.jsanhueza.prog0523.u1.restaurantorder.model.CuentaMesa
import com.jsanhueza.prog0523.u1.restaurantorder.model.ItemMenu
import com.jsanhueza.prog0523.u1.restaurantorder.model.ItemMesa
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etPastelCant: EditText
    private lateinit var tvPastelSubtotal: TextView
    private lateinit var etCazuelaCant: EditText
    private lateinit var tvCazuelaSubtotal: TextView
    private lateinit var swPropina: SwitchCompat
    private lateinit var tvComidaMonto: TextView
    private lateinit var tvPropinaMonto: TextView
    private lateinit var tvTotalMonto: TextView

    private val itemPastel = ItemMenu("Pastel de Choclo", "12000")
    private val itemCazuela = ItemMenu("Cazuela", "10000")

    private val itemMesaPastel = ItemMesa(itemPastel, 0)
    private val itemMesaCazuela = ItemMesa(itemCazuela, 0)

    private val cuenta = CuentaMesa(1)

    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).apply {
        maximumFractionDigits = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Vistas
        etPastelCant = findViewById(R.id.etPastelCant)
        tvPastelSubtotal = findViewById(R.id.tvPastelSubtotal)
        etCazuelaCant = findViewById(R.id.etCazuelaCant)
        tvCazuelaSubtotal = findViewById(R.id.tvCazuelaSubtotal)
        swPropina = findViewById(R.id.swPropina)
        tvComidaMonto = findViewById(R.id.tvComidaMonto)
        tvPropinaMonto = findViewById(R.id.tvPropinaMonto)
        tvTotalMonto = findViewById(R.id.tvTotalMonto)

        // Configurar CuentaMesa
        cuenta.agregarItem(itemMesaPastel)
        cuenta.agregarItem(itemMesaCazuela)

        // Listeners para actualizar montos inmediatamente
        etPastelCant.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cant = s.toString().toIntOrNull() ?: 0
                itemMesaPastel.cantidad = cant
                actualizarTotales()
            }
        })

        etCazuelaCant.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cant = s.toString().toIntOrNull() ?: 0
                itemMesaCazuela.cantidad = cant
                actualizarTotales()
            }
        })

        swPropina.setOnCheckedChangeListener { _, isChecked ->
            cuenta.aceptaPropina = isChecked
            actualizarTotales()
        }

        actualizarTotales()
    }

    private fun actualizarTotales() {
        // Actualizar subtotales por plato
        tvPastelSubtotal.text = currencyFormat.format(itemMesaPastel.calcularSubtotal())
        tvCazuelaSubtotal.text = currencyFormat.format(itemMesaCazuela.calcularSubtotal())

        // Actualizar totales generales
        tvComidaMonto.text = currencyFormat.format(cuenta.calcularTotalSinPropina())
        tvPropinaMonto.text = currencyFormat.format(cuenta.calcularPropina())
        tvTotalMonto.text = currencyFormat.format(cuenta.calcularTotalConPropina())
    }
}
