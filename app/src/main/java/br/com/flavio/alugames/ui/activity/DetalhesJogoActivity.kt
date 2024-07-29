package br.com.flavio.alugames.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.flavio.alugames.databinding.ActivityJogoDetalhesBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil
import br.com.flavio.alugames.extensions.formataParaMoedaBrasileira
import br.com.flavio.alugames.model.Jogo



class DetalhesProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityJogoDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarJogo()
    }

    private fun tentaCarregarJogo() {
        intent.getParcelableExtra<Jogo>(CHAVE_JOGO)?.let { jogoCarregado ->
            preencheCamposJogo(jogoCarregado)
        } ?: finish()
    }

    private fun preencheCamposJogo(jogoCarregado: Jogo) {
        with(binding) {
            activityDetalhesJogoImagem.carregaImagemCoil(jogoCarregado.imagem)
            activityDetalhesJogoNome.text = jogoCarregado.nome
            activityDetalhesJogoDescricao.text = jogoCarregado.descricao
            activityDetalhesJogoValor.text = jogoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}
