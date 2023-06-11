package com.drcmind.androidroomwithcompose.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drcmind.androidroomwithcompose.data.datasource.Note
import com.drcmind.androidroomwithcompose.data.repository.NoteRepository
import com.drcmind.androidroomwithcompose.util.DISPLAY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel(){
    val uiState = mutableStateOf(UiState())

    init {
        getNotes()
    }

    private fun getNotes(){
        when (uiState.value.displayType){
            DISPLAY_TYPE.ALL ->{
                viewModelScope.launch {
                    repository.getAll().collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
            DISPLAY_TYPE.FINISHED->{
                viewModelScope.launch {
                    repository.getFiltered(true).collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
            DISPLAY_TYPE.IN_PROGRESS->{
                viewModelScope.launch {
                    repository.getFiltered(false).collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
        }
    }

    fun switchDisplayType(dt: DISPLAY_TYPE){
        uiState.value = uiState.value.copy(
            displayType = dt
        )
        getNotes()
    }

    fun insert(note: Note){
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch {
            repository.update(note)
        }
    }
}