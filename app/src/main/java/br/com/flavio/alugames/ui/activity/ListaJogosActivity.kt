package br.com.flavio.alugames.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.dao.jogosDao
import br.com.flavio.alugames.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaJogosActivity : AppCompatActivity(R.layout.activity_lista_jogos) {

    private val dao = jogosDao()
    private val adapter =  ListaProdutosAdapter(context = this, jogos = dao.buscaTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        configuraFab()
    }

    private fun configuraFab() {
        val fabLista = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabLista.setOnClickListener {
            intentFormularioJogo()
        }
    }

    private fun intentFormularioJogo() {
        val intentForm = Intent(this, FormularioJogoActivity::class.java)
        startActivity(intentForm)
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        Log.i("Main activity", "Criou adapter")

    }
}