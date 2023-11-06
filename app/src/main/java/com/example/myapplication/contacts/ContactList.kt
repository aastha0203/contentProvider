package com.example.myapplication.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn (
        contentPadding = PaddingValues(4.dp)
    ){
        items(contacts) { contact ->
            // Display each contact in the list
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(vertical = 4.dp)
            ) {
                Column {
                    Text(text = contact.name)
                    Text(text =contact.phone)
                }
            }
        }
    }
}
