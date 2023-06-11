package com.drcmind.androidroomwithcompose.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NoteDAO {
    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query ("SELECT * FROM notes_entity")
    fun getAll() : Flow<List<Note>>

    @Query("SELECT * FROM notes_entity WHERE isFinished = :isFinished")
    fun getFiltered(isFinished : Boolean) : Flow<List<Note>>
}