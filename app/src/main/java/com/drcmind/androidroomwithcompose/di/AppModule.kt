package com.drcmind.androidroomwithcompose.di

import android.content.Context
import androidx.room.Room
import com.drcmind.androidroomwithcompose.data.datasource.NoteDAO
import com.drcmind.androidroomwithcompose.data.datasource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "Notes.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: NoteDatabase): NoteDAO = database.noteDAO()
}