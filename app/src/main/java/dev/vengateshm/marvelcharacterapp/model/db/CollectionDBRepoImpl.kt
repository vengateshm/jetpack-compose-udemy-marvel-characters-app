package dev.vengateshm.marvelcharacterapp.model.db

import kotlinx.coroutines.flow.Flow

class CollectionDBRepoImpl(
    private val characterDao: CharacterDao,
    private val noteDao: NoteDao
) : CollectionDBRepo {
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

    override fun getAllNotes(): Flow<List<DBNote>> = noteDao.getAllNotes()

    override fun getNote(characterId: Int): Flow<DBNote> = noteDao.getNote(characterId)

    override suspend fun addNote(dbNote: DBNote) = noteDao.addNote(dbNote)

    override suspend fun updateNote(dbNote: DBNote) = noteDao.updateNote(dbNote)

    override suspend fun deleteNote(dbNote: DBNote) = noteDao.deleteNote(dbNote)

    override suspend fun deleteAllNotes(characterId: Int) = noteDao.deleteAllNotes(characterId)
}