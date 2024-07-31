package br.com.flavio.alugames.database.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun doubleparaBdecimal(valor:Double? = null):BigDecimal{
        return valor?.let {
            BigDecimal(valor.toString())
        }?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bdecimalparaDouble(valor: BigDecimal?): Double?{
        return valor?.let {
            valor.toDouble()
        }
    }
}