package com.example.socialx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {
    private lateinit var userAuth : FirebaseAuth
     private var checkBOX : CheckBox? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAuth = FirebaseAuth.getInstance()
        checkBOX = findViewById(R.id.checkBox)
        setContentView(R.layout.activity_signup)
        // register button for Sign up !
        tvRegister.setOnClickListener {
           signup()
        }
        // login textView for going to log in screeen
        tvlogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            it.background = ContextCompat.getDrawable(this,R.drawable.signup_login_background_design_on_click)
            tvsignUp.background = ContextCompat.getDrawable(this,R.drawable.signup_login_background_white_onclick)
        }
        // if already have an account directly log in
        tvRedLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


    }
    private fun signup(){
        val email = etEmailAddress1.text.toString()
        val password = etPassword1.text.toString()

        if(email.isEmpty() || password.isEmpty() || editTextTextPersonName.text.toString().isEmpty() ||
            editTextPhone.text.toString().isEmpty()){
            Toast.makeText(this,"Enter full details for \n sign up to App",Toast.LENGTH_SHORT).show()
            return
        }
        userAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Successfully Signed up",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Error in sign up \n check your details",Toast.LENGTH_SHORT).show()
            }
        }

    }
}