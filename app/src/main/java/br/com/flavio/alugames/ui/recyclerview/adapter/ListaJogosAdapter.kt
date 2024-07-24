package br.com.flavio.alugames.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.databinding.JogoItemBinding
import br.com.flavio.alugames.model.Jogo

class ListaProdutosAdapter(
    private val context: Context,
    jogos: List<Jogo>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

     private val jogos = jogos.toMutableList()
    class ViewHolder(binding: JogoItemBinding):RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.jogoItemNome
        private val descricao = binding.jogoItemDescricao
        private val valor = binding.jogoItemValor

        fun vincula(jogo: Jogo) {

            nome.text = jogo.nome
            descricao.text = jogo.descricao
            valor.text = jogo.valor.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JogoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogo = jogos[position]
        holder.vincula(jogo)
    }

    override fun getItemCount(): Int = jogos.size
    fun atualiza(jogos: List<Jogo>) {
        this.jogos.clear()
        this.jogos.addAll(jogos)
        notifyDataSetChanged()
    }
}