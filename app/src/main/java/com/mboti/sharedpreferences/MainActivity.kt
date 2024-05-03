package com.mboti.sharedpreferences

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mboti.sharedpreferences.ui.theme.SharedPreferencesTheme


/**
 * mport android.content.Context
 *
 * class MyPreferences(context: Context) {
 *     private val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
 *
 *     fun saveString(key: String, value: String) {
 *         sharedPreferences.edit().putString(key, value).apply()
 *     }
 *
 *     fun getString(key: String, defaultValue: String): String? {
 *         return sharedPreferences.getString(key, defaultValue)
 *     }
 *
 *     // Similarly, you can implement methods for other data types
 * }
 *
 *
 *
 * -------------------------------------------------------------------------------------------------
 *
 * // Saving data
 * val myPreferences = MyPreferences(context)
 * myPreferences.saveString("username", "John")
 *
 * // Reading data
 * val username = myPreferences.getString("username", "DefaultUsername")
 */



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SharedPreferencesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextFieldWithInputType()
                }
            }
        }
    }
}


@Composable
fun TextFieldWithInputType(context: Context = LocalContext.current) {
    Column ( modifier = Modifier.fillMaxWidth()){

        val myPreferences = MyPreferences(context)

        // Reading data
        val username = myPreferences.getString("username", "DefaultUsername")

        var myText by remember { mutableStateOf(TextFieldValue(username.toString())) }

        OutlinedTextField(
            value = myText,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "emailIcon") },
            //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            onValueChange = {
                myText = it
            },
            label = { Text(text = "User") },
            placeholder = { Text(text = "Enter your name") },
        )

        Button(onClick = {
            // Saving data
            myPreferences.saveString("username", myText.text)
        }) {
            Text(text = "Save")
        }
    }
}
