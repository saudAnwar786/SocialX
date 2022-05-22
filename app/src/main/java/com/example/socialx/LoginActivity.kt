package com.example.socialx

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tvlogin
import kotlinx.android.synthetic.main.activity_login.tvsignUp
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {
    private lateinit var authuser : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authuser = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_login)
        if (authuser.currentUser != null) {
            Toast.makeText(this, "Already Logged in ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        tvlogingin.setOnClickListener {
            login()
        }
        tvsignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        tvRedSign.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        tvsignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            it.background =
                ContextCompat.getDrawable(this, R.drawable.signup_login_background_design_on_click)
            tvlogin.background =
                ContextCompat.getDrawable(this, R.drawable.signup_login_background_white_onclick)
        }
        // ====================configure google sign in ===========================

        // onActivity
    }




    private fun login(){
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()

        authuser.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Logged in ",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Error in signing in ",Toast.LENGTH_SHORT).show()
            }
        }
    }

}