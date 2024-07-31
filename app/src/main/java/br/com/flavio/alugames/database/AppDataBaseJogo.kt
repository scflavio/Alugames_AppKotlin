package br.com.flavio.alugames.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.flavio.alugames.database.converters.Converters
import br.com.flavio.alugames.database.dao.JogoDao
import br.com.flavio.alugames.model.Jogo


@Database(entities = [Jogo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBaseJogo : RoomDatabase() {
    abstract fun jogoDao(): JogoDao


    companion object {
        private var db: AppDataBaseJogo? = null

        fun getInstanceDb(context: Context): AppDataBaseJogo {
            return db ?: Room.databaseBuilder(
                context,
                AppDataBaseJogo::class.java,
                "alugames.db"
            ).build()
                .also {
                    db = it
                }
        }
    }
}