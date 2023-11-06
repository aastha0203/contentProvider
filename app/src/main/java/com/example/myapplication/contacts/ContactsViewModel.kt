package com.example.myapplication.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ContactsViewModel : ViewModel(){//(application: Application) : AndroidViewModel(application) {

    private val _permission = MutableStateFlow(false)
    var permission = _permission.asStateFlow()


    @SuppressLint("Range")
    fun getContacts(context: Context, contentResolver: ContentResolver): List<Contact> {
        val contacts = mutableListOf<Contact>()

        // Asking Permission
//        requestContactPermission(context, context as Activity)
//        LaunchedEffect(key1 = hasContactPermission(context)) {
//
//        }

//        val permission: Deferred<Unit>
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) !=
            PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                0
            )

        }
        if (hasContactPermission(context)) {
            // if permission granted open intent to pick contact/
//            val contentResolver = getApplication<Application>().contentResolver

            val cr = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )


            cr?.let { cursor ->
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    if (cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) == "1") {
                        val phone =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        contacts.add(Contact(id, name, phone))
                    }
                }
            }
            cr?.close()
            return contacts
        }
        return contacts
    }
}

fun hasContactPermission(context: Context): Boolean =
    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED


fun requestContactPermission(context: Context, activity: Activity) {

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) !=
        PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.READ_CONTACTS),
            0
        )
    }
}