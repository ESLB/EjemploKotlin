package com.example.usuario.ejemplokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        mn_info_tv.setText(Singleton.user!!.email)
        Singleton.mMyRef.child("Usuarios").child(Singleton.user!!.uid).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(baseContext, ""+snapshot.getValue().toString(),Toast.LENGTH_SHORT).show()
                var user = snapshot.getValue(User::class.java)

            }

            override fun onCancelled(error: DatabaseError) {}
        })

        mn_crearequipos_btn.setOnClickListener({
            startActivity(Intent(this, CrearEquipoActivity::class.java))
        })

        mn_cerrarsesion_btn.setOnClickListener({
            Singleton.mAuth!!.signOut()
            Singleton.user=null
            startActivity(Intent(this, MainLoginActivity::class.java))
            finish()
        })

        mn_todosequipos_btn.setOnClickListener({
            startActivity(Intent(this, TodosEquiposActivity::class.java))
        })

        retos_btn.setOnClickListener({
            startActivity(Intent(this, Retos::class.java))
        })

        solicitudes_btn.setOnClickListener({
            startActivity(Intent(this, Solicitudes::class.java
            ))
        })
    }

}
