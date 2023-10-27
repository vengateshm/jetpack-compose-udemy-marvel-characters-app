package dev.vengateshm.marvelcharacterapp.model.db

import kotlinx.coroutines.flow.Flow

class CollectionDBRepoImpl(private val characterDao: CharacterDao) : CollectionDBRepo {
    override fun getCharacters(): Flow<List<DBCharacter>> {
        return characterDao.getCharacters()
    }

    override fun getCharacter(characterId: Int): Flow<DBCharacter> {
        return characterDao.getCharacter(characterId)
    }

    override suspend fun addCharacter(character: DBCharacter) {
        return characterDao.addCharacter(character)
    }

    override suspend fun updateCharacter(character: DBCharacter) {
        return characterDao.updateCharacter(character)
    }

    override suspend fun deleteCharacter(character: DBCharacter) {
        return characterDao.deleteCharacter(character)
    }
}