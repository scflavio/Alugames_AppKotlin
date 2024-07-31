package br.com.flavio.alugames.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

import java.math.BigDecimal

@Parcelize
@Entity
data class Jogo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0L,
        val nome: String,
        val descricao: String,
        val valor: BigDecimal,
        var imagem: String? = null
) : Parcelable
