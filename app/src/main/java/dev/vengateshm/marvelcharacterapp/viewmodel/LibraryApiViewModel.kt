package dev.vengateshm.marvelcharacterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vengateshm.marvelcharacterapp.model.api.MarvelApiRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryApiViewModel @Inject constructor(
    private val apiRepo: MarvelApiRepo
) : ViewModel() {
    val result = apiRepo.characters
    private val queryInput = Channel<String>(Channel.CONFLATED)

    private val _queryText = MutableStateFlow("")
    val queryText = _queryText.asStateFlow()

    val characterDetails = apiRepo.characterDetail

    init {
        retrieveCharacters()
    }

    @OptIn(FlowPreview::class)
    private fun retrieveCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            queryInput.receiveAsFlow()
                .filter { validateQuery(it) }
                .debounce(1000)
                .collect {
                    apiRepo.query(it)
                }
        }
    }

    private fun validateQuery(query: String): Boolean = query.length >= 2

    fun onQueryUpdate(query: String) {
        _queryText.value = query
        queryInput.trySend(query)
    }

    fun getSingleCharacter(id: Int) {
        apiRepo.getSingleCharacter(id)
    }
}