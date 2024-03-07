package br.com.fsc.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fsc.orgs.R
import br.com.fsc.orgs.dao.JogosDao
import br.com.fsc.orgs.databinding.ActivityListaJogosBinding
import br.com.fsc.orgs.ui.reciclerview.adapter.ListaJogosAdapter

class ListaJogosActivity: AppCompatActivity(R.layout.activity_lista_jogos){

    private val dao = JogosDao()
    private val adapter = ListaJogosAdapter( context = this, jogos = dao.buscaLista()
    )
    private val  binding by lazy {
        ActivityListaJogosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizaLista(dao.buscaLista())
        configuraFab()
    }

    private fun configuraFab() {
        val fabLista = binding.fabLista
        fabLista.setOnClickListener {
            val intent = Intent(this, FormularioJogoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraRecyclerView() {
        val rvprodutos = binding.rviewProdutos
        rvprodutos.adapter = adapter
   }
}
