package dev.vengateshm.marvelcharacterapp.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(dbNote: DBNote)

    @Update
    fun updateNote(dbNote: DBNote)

    @Delete
    fun deleteNote(dbNote: DBNote)

    @Query("DELETE FROM ${Constants.NOTE_TABLE} WHERE characterId = :characterId")
    fun deleteAllNotes(characterId: Int)

    @Query("SELECT * FROM ${Constants.NOTE_TABLE} ORDER BY id")
    fun getAllNotes(): Flow<List<DBNote>>

    @Query("SELECT * FROM ${Constants.NOTE_TABLE} WHERE characterId = :characterId ORDER BY id ASC")
    fun getNote(characterId: Int): Flow<DBNote>
}