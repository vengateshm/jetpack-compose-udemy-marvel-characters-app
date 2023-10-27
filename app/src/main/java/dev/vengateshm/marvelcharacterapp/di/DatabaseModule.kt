package dev.vengateshm.marvelcharacterapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vengateshm.marvelcharacterapp.model.db.CharacterDao
import dev.vengateshm.marvelcharacterapp.model.db.CollectionDB
import dev.vengateshm.marvelcharacterapp.model.db.CollectionDBRepo
import dev.vengateshm.marvelcharacterapp.model.db.CollectionDBRepoImpl
import dev.vengateshm.marvelcharacterapp.model.db.Constants.DB_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCollectionDB(@ApplicationContext context: Context): CollectionDB {
        return Room.databaseBuilder(context, CollectionDB::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(collectionDB: CollectionDB): CharacterDao {
        return collectionDB.characterDao()
    }

    @Provides
    @Singleton
    fun provideCollectionDBRepo(characterDao: CharacterDao): CollectionDBRepo {
        return CollectionDBRepoImpl(characterDao)
    }
}