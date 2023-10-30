package dev.vengateshm.marvelcharacterapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBCharacter::class], version = 1, exportSchema = false)
abstract class CollectionDB : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}