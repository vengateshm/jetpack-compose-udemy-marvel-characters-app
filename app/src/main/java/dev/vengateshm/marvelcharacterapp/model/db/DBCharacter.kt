package dev.vengateshm.marvelcharacterapp.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.vengateshm.marvelcharacterapp.model.CharacterResult
import dev.vengateshm.marvelcharacterapp.model.db.Constants.CHARACTER_TABLE

@Entity(tableName = CHARACTER_TABLE)
data class DBCharacter(
    @PrimaryKey
    val id: Int,
    val apiId: Int?,
    val name: String?,
    val thumbnail: String?,
    val comics: String?,
    val description: String?
) {
    companion object {
        fun fromCharacter(character: CharacterResult) =
            DBCharacter(
                id = 0,
                apiId = character.id,
                name = character.name,
                thumbnail = character.thumbnail?.path + "." + character.thumbnail?.extension,
                comics = character.comics?.items?.mapNotNull { it.name }?.joinToString(",")
                    ?: "no comics",
                description = character.description
            )
    }
}
