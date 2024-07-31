package br.com.flavio.alugames.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.alugames.R
import br.com.flavio.alugames.databinding.JogoItemBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil
import br.com.flavio.alugames.model.Jogo
import coil.load
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.log

class ListaProdutosAdapter(
    private val context: Context,
    jogos: List<Jogo> = emptyList(),
    var cliqueNaListaListener: (jogo: Jogo) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val jogos = jogos.toMutableList()


    inner class ViewHolder(private val binding: JogoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var jogo: Jogo

        init {
            itemView.setOnClickListener {
                if (::jogo.isInitialized){
                    cliqueNaListaListener(jogo)
                }
            }
        }

        fun vincula(jogo: Jogo) {
            this.jogo = jogo

            val nome = binding.jogoItemNome
            nome.text = jogo.nome

            val descricao = binding.jogoItemDescricao
            descricao.text = jogo.descricao

            val valor = binding.jogoItemValor
            val valorEmReal = formataValorReal(jogo)
            valor.text = valorEmReal

            binding.imageView.carregaImagemCoil(jogo.imagem)
        }

        private fun formataValorReal(jogo: Jogo): String? {
            val formatadorMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatadorMoeda.format(jogo.valor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JogoItemBinding.inflate(LayoutInflater.from(context), parent, false)
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