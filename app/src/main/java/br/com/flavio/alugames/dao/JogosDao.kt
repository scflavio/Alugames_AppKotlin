package br.com.flavio.alugames.dao

import br.com.flavio.alugames.modelo.Jogo
import java.math.BigDecimal

class JogosDao {

    fun adiciona(jogo: Jogo){
        jogos.add(jogo)

    }

    fun buscaLista(): List<Jogo>{
        return jogos.toList()
    }

    companion object {
        private val jogos = mutableListOf<Jogo>(
            Jogo(
                nome = "Resident Evil 2",
                descricao = "Remake PS4 para todos os consoles que possuem a capacidade acima de 32 gigas de memória",
                valor = BigDecimal( "79.90")
            )
        )
    }
}