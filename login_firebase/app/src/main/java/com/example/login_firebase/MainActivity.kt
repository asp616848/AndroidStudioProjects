package com.example.login_firebase

import android.content.Intent
import android.os.Bundle
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
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var authState : FirebaseAuth.AuthStateListener
    var providers : List<AuthUI.IdpConfig> = listOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        authState = FirebaseAuth.AuthStateListener {
            val user = firebaseAuth.currentUser
            if (user != null) {
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
                finish()
            } else {
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.baseline_account_box_24)
                        .setAvailableProviders(providers)
                        .build(),
                    1
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authState)
    }
    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authState)
    }

}