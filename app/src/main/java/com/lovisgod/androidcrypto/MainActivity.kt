package com.lovisgod.androidcrypto

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lovisgod.androidcrypto.ui.theme.AndroidCryptoTheme
import com.lovisgod.androidcrypto.utils.AesCryptoManager
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cryptoMaanager = AesCryptoManager()
        setContent {
            AndroidCryptoTheme {
                var messageToEncrypt by remember {
                   mutableStateOf("")
                }

                var messageToDecrypt by remember {
                    mutableStateOf("")
                }

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)) {
                    TextField(
                        value = messageToEncrypt,
                        onValueChange = {
                        messageToEncrypt =it
                    },
                        modifier = Modifier.fillMaxWidth().background(color = Color.DarkGray),
                        placeholder = { Text(text = "Enter text to encrypt")}
                    )
                    
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Button(onClick = {
                            val bytes = messageToEncrypt.encodeToByteArray()
                            val file = File(filesDir, "Secret.txt")
                            if (!file.exists()) {
                                file.createNewFile()
                            }
                            val fos = FileOutputStream(file)
                            messageToDecrypt = cryptoMaanager.encrypt( // this method does the encryption and saves
                                // it into an output stream which in turn saves it into the file
                                // TODO: check about output and input stream
                                bytes, fos
                            ).decodeToString() // encrypt and convert to string
                        }){
                            Text(text = "Encrypt")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            val file = File(filesDir, "Secret.txt")
                            messageToEncrypt = cryptoMaanager.decrypt(FileInputStream(file)).decodeToString()
                            // this get an input stream from file and decode it the turn it to string
                        }){
                            Text(text = "Decrypt")
                        }
                    }

                    Text(text = messageToDecrypt)
                }

            }
        }
    }
}
