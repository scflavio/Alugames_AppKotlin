package br.com.flavio.alugames.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.flavio.alugames.R
import br.com.flavio.alugames.dao.jogosDao
import br.com.flavio.alugames.databinding.ActivityFormularioImagemDialogBinding
import br.com.flavio.alugames.databinding.ActivityFormularioJogoBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil
import br.com.flavio.alugames.model.Jogo
import br.com.flavio.alugames.ui.dialog.FormularioImagemDialog
import coil.load
import java.math.BigDecimal


class FormularioJogoActivity : AppCompatActivity() {

    private val dao = jogosDao()
    private var url:String? = null

    private val binding by lazy {
        ActivityFormularioJogoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        title="Cadastro de Jogos"

        binding.activityFormularioImagem.setOnClickListener {
            FormularioImagemDialog(this).mostraDialog(url) {
                urlImagem ->
                url = urlImagem
                binding.activityFormularioImagem.carregaImagemCoil(url)
            }
        }
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
        val nome = campoNome.editText?.text.toString()

        val campoDescricao = binding.formularioJogoCampoDescricao
        val descricao = campoDescricao.editText?.text.toString()

        val campoValor = binding.formularioJogoCampoValor
        val valorTexto = campoValor.editText?.text.toString()

        val valor = if (valorTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorTexto)
        }

        return Jogo(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url


        )
    }
}