package br.com.flavio.alugames.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.flavio.alugames.model.Jogo
import kotlinx.coroutines.flow.Flow

@Dao
interface JogoDao {

    @Query (value = "SELECT * FROM Jogo")
    fun buscaTodos(): Flow <List<Jogo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvaJogo(jogo: Jogo)

    @Delete
    suspend fun deletaJogo(jogo:Jogo)


    @Query("SELECT * FROM Jogo WHERE id = :idJogo")
    fun buscaPorId(idJogo: Long):Flow <Jogo>

}
