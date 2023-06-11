package com.drcmind.androidroomwithcompose.ui

import com.drcmind.androidroomwithcompose.data.datasource.Note
import com.drcmind.androidroomwithcompose.util.DISPLAY_TYPE

data class UiState(
    val displayType : DISPLAY_TYPE = DISPLAY_TYPE.ALL,
    val currentList : List<Note> = emptyList()
)
