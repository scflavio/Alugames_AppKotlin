package br.com.fsc.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.fsc.orgs.R
import br.com.fsc.orgs.R.id.rview_produtos
import br.com.fsc.orgs.modelo.Jogo
import br.com.fsc.orgs.ui.reciclerview.adapter.ListaJogosAdapter
import java.math.BigDecimal

class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvprodutos = findViewById<RecyclerView>(rview_produtos)
        rvprodutos.adapter = ListaJogosAdapter(context = this, jogos = listOf(
            Jogo(
                nome = "Resident Evil 2",
                descricao = "Survival Horror ",
                valor = BigDecimal("99.00")
            ),
            Jogo(
                nome = "Luto",
                descricao = "Demo version ",
                valor = BigDecimal("56.00")
            ),
            Jogo(
                nome = "Martha is dead",
                descricao = "Collector's edition ",
                valor = BigDecimal("600.00")
            ),
        ))
    }
}