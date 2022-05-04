package br.edu.infnet.ads_e2_a2_gorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalConta : EditText
    private lateinit var txtPessoas : EditText
    private lateinit var skGorjeta : SeekBar
    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // o que é o "R"?
        setContentView(R.layout.activity_main)

        txtTotalConta = this.findViewById<EditText>(R.id.txtTotalConta)
        txtTotalConta.setOnFocusChangeListener(this)

        txtPessoas = this.findViewById<EditText>(R.id.personCount)
        txtPessoas.setOnFocusChangeListener(this)

        skGorjeta = this.findViewById<SeekBar>(R.id.skGorjeta)
        skGorjeta.setOnSeekBarChangeListener(this)

    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var lblPercentualGorjeta = this.findViewById<TextView>(R.id.lblPercentualGorjeta)
        lblPercentualGorjeta.setText(skGorjeta.progress.toString() + "%")

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun atualizaDadosConta() {
        if (txtTotalConta.text.toString().isNotEmpty() && txtPessoas.text.toString().isNotEmpty()) {

            var valorConta = txtTotalConta.text.toString().toDouble()
            var qtdPessoas = txtPessoas.text.toString().toInt()

            var lblValorRealGorjeta = this.findViewById<TextView>(R.id.lblValorRealGorjeta)
            var valorRealGorjeta = valorConta * skGorjeta.progress / 100
            lblValorRealGorjeta.setText(formatador.format(valorRealGorjeta))

            var lblValorRealConta = this.findViewById<TextView>(R.id.lblValorRealConta)
            lblValorRealConta.setText(formatador.format(valorConta + valorRealGorjeta))
            var lblValorRealPorPessoa = this.findViewById<TextView>(R.id.lblValorRealPorPessoa)
            lblValorRealPorPessoa.setText(formatador.format((valorConta + valorRealGorjeta) / qtdPessoas))
        }
    }
}