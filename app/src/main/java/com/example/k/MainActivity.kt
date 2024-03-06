package com.example.k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = findViewById<EditText>(R.id.uniqueId)
        val userName = findViewById<EditText>(R.id.userName)
        val userPswd = findViewById<EditText>(R.id.passsword)
        val userMail = findViewById<EditText>(R.id.userMail)
        val signInBtn = findViewById<Button>(R.id.signUpBtn)

        signInBtn.setOnClickListener {
            val id = userId.text.toString()
            val name = userName.text.toString()
            val pswd = userPswd.text.toString()
            val mail = userMail.text.toString()

            val user = UserModel(name,mail,pswd,id)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(id).setValue(user).addOnSuccessListener {
                userName.text.clear()
                userPswd.text.clear()
                userMail.text.clear()
                userId.text.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }

        }
    }
}