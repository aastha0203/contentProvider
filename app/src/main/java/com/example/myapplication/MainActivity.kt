package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.myapplication.ui.theme.MyApplicationTheme

import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.myapplication.contacts.Contact

import com.example.myapplication.contacts.ContactsViewModel
import com.example.myapplication.contacts.MainScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: ContactsViewModel by viewModels(){
            SavedStateViewModelFactory(application, this)
        }

//        ContactsViewModel(application)
//        var contacts= viewModel.getContacts(this)

        setContent {
            MyApplicationTheme {
                // on below line we are specifying background color for our application
                LaunchedEffect(key1 = viewModel.permission){

                }
                val context = LocalContext.current
                MainScreen { viewModel.getContacts(context,contentResolver) }
            }
        }
    }
}
