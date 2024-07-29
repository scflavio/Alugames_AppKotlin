package br.com.flavio.alugames.dao

import br.com.flavio.alugames.model.Jogo
import java.math.BigDecimal

class jogosDao {

    fun adiciona(jogo:Jogo){
        jogos.add(jogo)

    }

    fun buscaTodos():List<Jogo> {
        return jogos.toList()
    }

    companion object {
        private val jogos = mutableListOf<Jogo>(
            Jogo(
                nome = "Res. Evil: Gun Survivor",
                descricao = "Jogo retrô PS1",
                valor = BigDecimal("99.99")
            )
        )
    }
}