package br.com.flavio.alugames.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.flavio.alugames.R
import br.com.flavio.alugames.database.AppDataBaseJogo
import br.com.flavio.alugames.databinding.ActivityJogoDetalhesBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil
import br.com.flavio.alugames.extensions.formataParaMoedaBrasileira
import br.com.flavio.alugames.model.Jogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesProdutoActivity : AppCompatActivity() {

    private var idJogo: Long = 0L
    private var jogo: Jogo? = null

    val jogoDao by lazy {
        AppDataBaseJogo.getInstanceDb(this).jogoDao()
    }

    private val binding by lazy {
        ActivityJogoDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarJogo()
        buscaJogoDB()
    }

    private fun buscaJogoDB() {
        lifecycleScope.launch {
            jogoDao.buscaPorId(idJogo).collect { jogoEncontrado ->
                jogo = jogoEncontrado
                jogo?.let {
                    preencheCamposJogo(it)
                } ?: finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_jogo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_jogo_excluir -> {

                jogo?.let {
                    lifecycleScope.launch {
                        jogoDao.deletaJogo(it)
                        finish()
                    }
                }
                Log.i("DetalheJogos", "$jogo")
            }

            R.id.menu_detalhes_jogo_editar -> {
                Intent(this, FormularioJogoActivity::class.java).apply {
                    putExtra(CHAVE_JOGO_ID, idJogo)
                    Log.i("Detalhes", "$idJogo")
                    startActivity(this)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarJogo() {
        idJogo = intent.getLongExtra(CHAVE_JOGO_ID, 0L)
    }

    private fun preencheCamposJogo(jogoCarregado: Jogo) {
        with(binding) {
            activityDetalhesJogoImagem.carregaImagemCoil(jogoCarregado.imagem)
            activityDetalhesJogoNome.text = jogoCarregado.nome
            activityDetalhesJogoDescricao.text = jogoCarregado.descricao
            activityDetalhesJogoValor.text = jogoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}
