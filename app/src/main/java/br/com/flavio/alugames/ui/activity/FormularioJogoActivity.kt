package br.com.flavio.alugames.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.flavio.alugames.R
import br.com.flavio.alugames.dao.jogosDao
import br.com.flavio.alugames.databinding.ActivityFormularioJogoBinding
import br.com.flavio.alugames.model.Jogo
import java.math.BigDecimal


class FormularioJogoActivity : AppCompatActivity() {

    private val dao = jogosDao()

    private val binding by lazy{
        ActivityFormularioJogoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val btnsalvar = binding.formularioJogoBotaoSalvar
        btnsalvar.setOnClickListener {
            val jogo = criaJogo()

            dao.adiciona(jogo)
            finish()
        }
    }

    private fun criaJogo(): Jogo {
        val campoNome = binding.formularioJogoCampoNome
        val nome = campoNome.text.toString()

        val campoDescricao = binding.formularioJogoCampoDescricao
        val descricao = campoDescricao.text.toString()

        val campoValor = binding.formularioJogoCampoValor
        val valorTexto = campoValor.text.toString()

        val valor = if (valorTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorTexto)
        }

        return Jogo(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}