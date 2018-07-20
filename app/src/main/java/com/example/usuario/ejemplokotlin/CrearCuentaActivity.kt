package com.example.usuario.ejemplokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_crearcuenta.*
import kotlinx.android.synthetic.main.activity_main.*

class CrearCuentaActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth?=null
    private var database = FirebaseDatabase.getInstance()
    private var mMyRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crearcuenta)

        mAuth = FirebaseAuth.getInstance()

        crearusuario_btn_lgn.setOnClickListener({
            var email = email_et_lgn.text.toString()
            var pass = pass_et_lgn.text.toString()

            mAuth!!.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful) {
                            var currentUser = mAuth!!.currentUser
                            var usuario = User(currentUser!!.email, edad_et_lgn.text.toString().toInt(), nombre_et_lgn.text.toString(), ubicacion_et_lgn.text.toString(), null)
                            var edad = edad_et_lgn.text.toString().toInt()
                            var recibida = usuario.edad
                            Log.d("InfoUsuario", "recibida = $recibida")
                            Log.d("InfoUsuario", "edad = $edad")
                            val numbers: MutableList<String> = mutableListOf()
                            numbers.add("Hola")
                            numbers.add("Como estás")
                            usuario.compas = numbers
                            mMyRef.child("Usuarios").child(mAuth!!.currentUser!!.uid).setValue(usuario)
                            Toast.makeText(this, "Todo fue bien", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show()
                        }
                    }

        })


    }
}
