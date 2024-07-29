package br.com.flavio.alugames.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import java.math.BigDecimal

@Parcelize
data class Jogo(
        val nome: String,
        val descricao: String,
        val valor: BigDecimal,
        val imagem: String? = null
) : Parcelable
