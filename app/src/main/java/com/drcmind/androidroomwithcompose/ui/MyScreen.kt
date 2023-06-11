package com.drcmind.androidroomwithcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drcmind.androidroomwithcompose.data.datasource.Note
import com.drcmind.androidroomwithcompose.util.DISPLAY_TYPE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(
    viewModel: MainViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState
    var expanded by remember {
        mutableStateOf(false)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NotesApp")},
                actions = {
                    IconButton(onClick = {expanded = !expanded }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false}) {
                        DropdownMenuItem(
                            text = { Text(text = "Toutes") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.ALL)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "En cours") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.IN_PROGRESS)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Terminées") },
                            onClick = {
                                viewModel.switchDisplayType(DISPLAY_TYPE.FINISHED)
                                expanded = false
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ){
                items(uiState.value.currentList){item: Note ->
                    NoteItem(
                        note = item,
                        onDoneClick = {
                            viewModel.update(
                                Note(
                                    item.id,
                                    item.title,
                                    item.description,
                                    true))
                        },
                        onDeleteClick = {
                            viewModel.delete(item)
                        }
                    )
                }
            }
        }

        if(showDialog){
            UpdateDialog(
                viewModel = viewModel,
                onDismissRequest = {
                    showDialog = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview(){
    MyScreen()
}