package com.example.k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userId = findViewById<EditText>(R.id.userId)
        val loginBtn = findViewById<Button>(R.id.loginInBtn)
        val signInBtn = findViewById<Button>(R.id.newBtn)

        loginBtn.setOnClickListener {
            val uniqueId = userId.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this,"Enter ur id",Toast.LENGTH_SHORT).show()
            }
        }

        signInBtn.setOnClickListener {
            val intu = Intent(this,MainActivity::class.java)
            startActivity(intu)
        }
    }

    private fun readData(uniqueId: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueId).get().addOnSuccessListener {
            if (it.exists()){
                val intent = Intent(this,WelcomeActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"User invalid",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}