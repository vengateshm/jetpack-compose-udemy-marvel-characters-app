package dev.vengateshm.marvelcharacterapp.model.db

import kotlinx.coroutines.flow.Flow

interface CollectionDBRepo {
    fun getCharacters(): Flow<List<DBCharacter>>
    fun getCharacter(characterId: Int): Flow<DBCharacter>
    suspend fun addCharacter(character: DBCharacter)
    suspend fun updateCharacter(character: DBCharacter)
    suspend fun deleteCharacter(character: DBCharacter)
    fun getAllNotes(): Flow<List<DBNote>>
    fun getNote(characterId: Int): Flow<DBNote>
    suspend fun addNote(dbNote: DBNote)
    suspend fun updateNote(dbNote: DBNote)
    suspend fun deleteNote(dbNote: DBNote)
    suspend fun deleteAllNotes(characterId: Int)
}