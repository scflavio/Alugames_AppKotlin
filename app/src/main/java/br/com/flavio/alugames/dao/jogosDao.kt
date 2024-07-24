package br.com.flavio.alugames.dao

import br.com.flavio.alugames.model.Jogo

class jogosDao {

    fun adiciona(jogo:Jogo){
        jogos.add(jogo)

    }

    fun buscaTodos():List<Jogo> {
        return jogos.toList()
    }

    companion object {
        private val jogos = mutableListOf<Jogo>()
    }
}