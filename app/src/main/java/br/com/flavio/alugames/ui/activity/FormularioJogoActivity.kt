package br.com.flavio.alugames.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.flavio.alugames.database.AppDataBaseJogo
import br.com.flavio.alugames.databinding.ActivityFormularioJogoBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil
import br.com.flavio.alugames.model.Jogo
import br.com.flavio.alugames.ui.dialog.FormularioImagemDialog
import kotlinx.coroutines.launch
import java.math.BigDecimal


class FormularioJogoActivity : AppCompatActivity() {


    private var idJogo: Long = 0L
    private var url: String? = null


    private val binding by lazy {
        ActivityFormularioJogoBinding.inflate(layoutInflater)
    }

    private val jogoDao by lazy {
        AppDataBaseJogo.getInstanceDb(this).jogoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastro de Jogos"
        configuraBotaoSalvar()

        binding.activityFormularioImagem.setOnClickListener {
            FormularioImagemDialog(this).mostraDialog(url) { urlImagem ->
                url = urlImagem
                binding.activityFormularioImagem.carregaImagemCoil(url)
            }
        }
        tentaCarregarJogo()
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarJogo()
    }

    private fun tentaBuscarJogo() {
        lifecycleScope.launch {
            jogoDao.buscaPorId(idJogo).collect {
                it?.let { jogoEncontrado ->
                    title = "Alterar produto"
                    preencheCampos(jogoEncontrado)
                }
            }
        }
    }

    private fun preencheCampos(jogo: Jogo) {

        url = jogo.imagem
        binding.activityFormularioImagem.carregaImagemCoil(jogo.imagem)
        binding.formularioJogoCampoNome.editText?.setText(jogo.nome)
        binding.formularioJogoCampoDescricao.editText?.setText(jogo.descricao)
        binding.formularioJogoCampoValor.editText?.setText(jogo.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val btnsalvar = binding.formularioJogoBotaoSalvar
        btnsalvar.setOnClickListener {
            val jogoNovo = criaJogo()
            lifecycleScope.launch {
                jogoDao.salvaJogo(jogoNovo)
                finish()
            }
        }
    }

    private fun tentaCarregarJogo() {
        idJogo = intent.getLongExtra(CHAVE_JOGO_ID, 0L)


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
            id = idJogo,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}
