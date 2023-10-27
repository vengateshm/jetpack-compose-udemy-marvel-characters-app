package dev.vengateshm.marvelcharacterapp.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.vengateshm.marvelcharacterapp.model.db.Constants.CHARACTER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacter(character: DBCharacter)

    @Update
    fun updateCharacter(character: DBCharacter)

    @Delete
    fun deleteCharacter(character: DBCharacter)

    @Query("SELECT * FROM $CHARACTER_TABLE ORDER BY id ASC")
    fun getCharacters(): Flow<List<DBCharacter>>

    @Query("SELECT * FROM $CHARACTER_TABLE WHERE id = :characterId")
    fun getCharacter(characterId: Int): Flow<DBCharacter>
}