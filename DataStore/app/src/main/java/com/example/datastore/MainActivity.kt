package com.example.datastore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class MainActivity : ComponentActivity() {
    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var tvName: TextView
    lateinit var tvAge: TextView
    lateinit var saveButton: Button

    lateinit var userManager: UserManager
    var age = 0
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        tvName = findViewById(R.id.tv_name)
        tvAge = findViewById(R.id.tv_age)
        saveButton = findViewById(R.id.btn_save)
        userManager = UserManager(this)
        buttonSave()
        observeData()
    }

    private fun observeData() {
        this.userManager.userAgeFlow.asLiveData().observe(this) {
            tvAge.text = it.toString()
        }
        this.userManager.userNameFlow.asLiveData().observe(this) {
            tvName.text = it.toString()
        }
    }

    private fun buttonSave() {
        saveButton.setOnClickListener{
            name = etName.text.toString()
            age = etAge.text.toString().toInt()
            GlobalScope.launch{
                userManager.storeUserAge(age, name, this@MainActivity)
            }
        }
    }

}