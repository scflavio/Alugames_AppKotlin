package br.com.fsc.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fsc.orgs.dao.JogosDao
import br.com.fsc.orgs.databinding.ActivityFormularioJogoBinding
import br.com.fsc.orgs.modelo.Jogo
import java.math.BigDecimal

class FormularioJogoActivity : AppCompatActivity() {

    private val  binding by lazy {
        ActivityFormularioJogoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        salvaJogo()
    }

    private fun salvaJogo() {
        val btnSalvar = binding.btnSalvar
        btnSalvar.setOnClickListener { montaJogo() }
    }

    private fun montaJogo() {
        val campoNome = binding.formularioNome
        val nome = campoNome.text.toString()
        val campoDesc = binding.formularioDescricao
        val descricao = campoDesc.text.toString()
        val campoValor = binding.formularioValor
        val valorTexto = campoValor.text.toString()
        val valor = if (valorTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorTexto)
        }
        val jogo = Jogo(
            nome, descricao, valor
        )
        val dao = JogosDao()
        dao.adiciona(jogo)
        finish()
    }
}