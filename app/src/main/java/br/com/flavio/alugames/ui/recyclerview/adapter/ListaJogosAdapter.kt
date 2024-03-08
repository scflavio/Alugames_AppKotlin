package br.com.flavio.alugames.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.model.Jogo

class ListaProdutosAdapter(
    private val context: Context,
    private val jogos: List<Jogo>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun vincula(jogo: Jogo) {
            val nome = itemView.findViewById<TextView>(R.id.nome)
            nome.text = jogo.nome
            val descricao = itemView.findViewById<TextView>(R.id.descricao)
            descricao.text = jogo.descricao
            val valor = itemView.findViewById<TextView>(R.id.valor)
            valor.text = jogo.valor.toPlainString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogo = jogos[position]
        holder.vincula(jogo)
    }

    override fun getItemCount(): Int = jogos.size

}
