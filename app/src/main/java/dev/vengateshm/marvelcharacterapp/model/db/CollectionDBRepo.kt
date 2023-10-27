package dev.vengateshm.marvelcharacterapp.model.db

import kotlinx.coroutines.flow.Flow

interface CollectionDBRepo {
    fun getCharacters(): Flow<List<DBCharacter>>
    fun getCharacter(characterId: Int): Flow<DBCharacter>

    suspend fun addCharacter(character: DBCharacter)

    suspend fun updateCharacter(character: DBCharacter)
    suspend fun deleteCharacter(character: DBCharacter)
}