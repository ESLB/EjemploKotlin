package com.example.usuario.ejemplokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainLoginActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mAuth:FirebaseAuth?=null
    private var logueado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = Singleton.mAuth
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (Singleton.user != null) {
            pasarAMenu()
        }

        crearcuenta_btn.setOnClickListener {

            startActivity(Intent(this,CrearCuentaActivity::class.java))

        }

        iniciarsesion_btn.setOnClickListener({
            var email = email_et.text.toString()
            var pass = pass_et.text.toString()
            mAuth!!.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                       if(task.isSuccessful){
                           //var user:FirebaseUser? = mAuth!!.currentUser
                           Singleton.user = mAuth!!.currentUser

                           pasarAMenu()
                       } else {

                       }
                     }
        })


    }


    fun checkearUsuario(){
        if(Singleton.mAuth!!.currentUser!!.uid!=""){
            Log.d("FirebaseInitState", Singleton.mAuth!!.currentUser!!.uid)
            pasarAMenu()
        }
    }

    fun pasarAMenu(){
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}
