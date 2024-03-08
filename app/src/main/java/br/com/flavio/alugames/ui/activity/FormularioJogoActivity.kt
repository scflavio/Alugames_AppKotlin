package br.com.flavio.alugames.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.flavio.alugames.R
import br.com.flavio.alugames.model.Jogo
import java.math.BigDecimal


class FormularioJogoActivity : AppCompatActivity(R.layout.activity_formulario_jogo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnsalvar = findViewById<Button>(R.id.botao_salvar_formulario)
        btnsalvar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val campoNome = findViewById<EditText>(R.id.campo_nome)
                val nome = campoNome.text.toString()

                val campoDescricao = findViewById<EditText>(R.id.campo_descricao)
                val descricao = campoDescricao.text.toString()

                val campoValor = findViewById<EditText>(R.id.campo_valor)
                val valorTexto = campoValor.text.toString()
                val valor = if (valorTexto.isBlank()){
                    BigDecimal.ZERO
                }else{
                    BigDecimal(valorTexto)
                }

                val jogo = Jogo(
                    nome = nome,
                    descricao = descricao,
                    valor = valor
                )

                Log.i("FormularioJogo", "Clique: $jogo")

            }
        })


    }
}