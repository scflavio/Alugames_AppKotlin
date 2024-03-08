package br.com.flavio.alugames.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.model.Jogo
import br.com.flavio.alugames.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, jogos = listOf(
            Jogo(nome = "teste",
                descricao = "teste desc",
                valor = BigDecimal("19.99")
            ),
            Jogo(nome = "teste 1",
                descricao = "teste desc 1",
                valor = BigDecimal("29.99")
            ),
            Jogo(nome = "teste 2",
                descricao = "teste desc 2",
                valor = BigDecimal("39.99")
            ),
        ))

        val fabLista = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabLista.setOnClickListener {
           val intentForm =  Intent(this,FormularioJogoActivity::class.java)
            startActivity(intentForm)

        }

    }

}