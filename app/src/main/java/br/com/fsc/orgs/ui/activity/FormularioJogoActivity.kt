package br.com.fsc.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.fsc.orgs.R
import br.com.fsc.orgs.modelo.Jogo
import java.math.BigDecimal

class FormularioJogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_jogo)

        var btnSalvar = findViewById<Button>(R.id.btn_salvar)

        btnSalvar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val campoNome = findViewById<EditText>(R.id.formulario_nome)
                val nome = campoNome.text.toString()

                val campoDesc = findViewById<EditText>(R.id.formulario_descricao)
                val descricao = campoDesc.text.toString()

                val campoValor = findViewById<EditText>(R.id.formulario_valor)
                val valorTexto = campoValor.text.toString()
                val valor = if (valorTexto.isBlank()){
                    BigDecimal.ZERO
                }else{
                    BigDecimal(valorTexto)
                }

                val jogo = Jogo(
                    nome, descricao, valor
                )
                Log.i("FormularioActivity", "Captura: $jogo")
            }
        })

    }
}