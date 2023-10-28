package dev.vengateshm.marvelcharacterapp.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.vengateshm.marvelcharacterapp.model.Note

@Entity(tableName = Constants.NOTE_TABLE)
data class DBNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val characterId: Int,
    val title: String,
    val text: String
) {
    companion object {
        fun fromNote(note: Note) = DBNote(
            id = 0,
            characterId = note.characterId,
            title = note.title,
            text = note.text
        )
    }
}
