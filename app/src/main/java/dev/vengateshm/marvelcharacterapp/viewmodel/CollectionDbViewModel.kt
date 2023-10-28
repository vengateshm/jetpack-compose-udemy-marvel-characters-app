package dev.vengateshm.marvelcharacterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vengateshm.marvelcharacterapp.model.CharacterResult
import dev.vengateshm.marvelcharacterapp.model.Note
import dev.vengateshm.marvelcharacterapp.model.db.CollectionDBRepo
import dev.vengateshm.marvelcharacterapp.model.db.DBCharacter
import dev.vengateshm.marvelcharacterapp.model.db.DBNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDbViewModel @Inject constructor(private val collectionDBRepo: CollectionDBRepo) :
    ViewModel() {

    val currentCharacter = MutableStateFlow<DBCharacter?>(null)
    val collection = MutableStateFlow<List<DBCharacter>>(listOf())
    val notes = MutableStateFlow<List<DBNote>>(listOf())

    init {
        getCollection()
        getNotes()
    }

    private fun getCollection() {
        viewModelScope.launch(Dispatchers.IO) {
            collectionDBRepo.getCharacters().collect {
                collection.value = it
            }
        }
    }

    fun setCurrentCharacterId(characterId: Int?) {
        characterId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                collectionDBRepo.getCharacter(it).collect {
                    currentCharacter.value = it
                }
            }
        }
    }

    fun addCharacter(characterResult: CharacterResult) {
        viewModelScope.launch(Dispatchers.IO) {
            collectionDBRepo.addCharacter(DBCharacter.fromCharacter(characterResult))
        }
    }

    fun deleteCharacter(character: DBCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            collectionDBRepo.deleteAllNotes(character.id)
            collectionDBRepo.deleteCharacter(character)
        }
    }

    private fun getNotes() {
        viewModelScope.launch {
            collectionDBRepo.getAllNotes().collect {
                notes.value = it
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            collectionDBRepo.addNote(DBNote.fromNote(note))
        }
    }

    fun deleteNote(note: DBNote) {
        viewModelScope.launch(Dispatchers.IO) {
            collectionDBRepo.deleteNote(note)
        }
    }
}