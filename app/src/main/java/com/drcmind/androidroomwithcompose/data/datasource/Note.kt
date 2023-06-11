package com.drcmind.androidroomwithcompose.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_entity")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val title : String,
    val description : String,
    val isFinished: Boolean
)
