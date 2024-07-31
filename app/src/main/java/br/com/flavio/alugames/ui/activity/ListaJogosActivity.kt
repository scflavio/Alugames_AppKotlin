package br.com.flavio.alugames.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.flavio.alugames.database.AppDataBaseJogo
import br.com.flavio.alugames.database.dao.JogoDao
import br.com.flavio.alugames.databinding.ActivityListaJogosBinding
import br.com.flavio.alugames.model.Jogo
import br.com.flavio.alugames.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withContext

class ListaJogosActivity : AppCompatActivity() {


    private val adapter = ListaProdutosAdapter(context = this)

    private val binding by lazy {
        ActivityListaJogosBinding.inflate(layoutInflater)
    }

    private val jogoDao by lazy {
        AppDataBaseJogo.getInstanceDb(this).jogoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

        lifecycleScope.launch {
            jogoDao.buscaTodos().collect { jogos ->
                adapter.atualiza(jogos)
            }
        }

    }

    override fun onResume() {
        super.onResume()

    }

    private fun configuraFab() {
        val fabLista = binding.listaJogoBtnCadastrar
        fabLista.setOnClickListener {
            intentFormularioJogo()
        }
    }

    private fun intentFormularioJogo() {
        val intentForm = Intent(this, FormularioJogoActivity::class.java)
        startActivity(intentForm)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        adapter.cliqueNaListaListener = {
            Log.i("Lista Jogos", "Clique ${it}")
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra(CHAVE_JOGO_ID, it.id)
            }
            startActivity(intent)
        }
    }
}



