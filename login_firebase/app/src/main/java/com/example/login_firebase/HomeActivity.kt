package com.example.login_firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.login_firebase.ui.theme.Login_firebaseTheme
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val logoutBtn = findViewById<Button>(R.id.idBtnLogout)

        logoutBtn.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(this) {
                if(it.isSuccessful) {
                    Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else {
                    Toast.makeText(this, "Logout Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}