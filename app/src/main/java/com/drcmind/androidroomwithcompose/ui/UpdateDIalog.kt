package com.drcmind.androidroomwithcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.drcmind.androidroomwithcompose.data.datasource.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDialog(
    viewModel: MainViewModel,
    onDismissRequest : ()->Unit
){
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        var title by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Ajouter une note",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = title,
                onValueChange = {
                    title = it
                },
                placeholder = {
                    Text(text = "Titre")
                },
                singleLine = true
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                placeholder = {
                    Text(text = "Description")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                viewModel.insert(
                    Note(null,title,description, false)
                )
                onDismissRequest()
            }) {
                Text(text = "Ajouter")
            }
        }
    }
}