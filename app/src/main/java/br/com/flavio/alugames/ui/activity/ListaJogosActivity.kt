package br.com.flavio.alugames.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.dao.jogosDao
import br.com.flavio.alugames.databinding.ActivityListaJogosBinding
import br.com.flavio.alugames.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaJogosActivity : AppCompatActivity() {

    private val dao = jogosDao()
    private val adapter =  ListaProdutosAdapter(context = this, jogos = dao.buscaTodos())

    private val binding by lazy {
        ActivityListaJogosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        configuraFab()
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
                putExtra(CHAVE_JOGO, it)
            }
            startActivity(intent)
        }



    }
}