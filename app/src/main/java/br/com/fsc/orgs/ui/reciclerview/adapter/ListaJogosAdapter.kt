package br.com.fsc.orgs.ui.reciclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fsc.orgs.databinding.ProdutoItemBinding
import br.com.fsc.orgs.modelo.Jogo
import java.text.NumberFormat
import java.util.Locale

class ListaJogosAdapter (jogos: List<Jogo>, private val context: Context)
    : RecyclerView.Adapter<ListaJogosAdapter.ViewHolder>() {

    private val jogoslistaInterna = jogos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(jogo: Jogo) {
            val nome = binding.nome
            nome.text = jogo.nome

            val descricao = binding.descricao
            descricao.text = jogo.descricao

            val valor = binding.preco
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt","br"))
            val valorEmReais: String = formatador.format(jogo.valor)
            valor.text = valorEmReais

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jogoslistaInterna.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogo = jogoslistaInterna[position]
        holder.vincula(jogo)
    }

    fun atualizaLista(jogos: List<Jogo>) {
        this.jogoslistaInterna.clear()
        this.jogoslistaInterna.addAll(jogos)
        notifyDataSetChanged()
    }
}
