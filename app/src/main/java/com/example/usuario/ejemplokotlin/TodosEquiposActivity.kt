package com.example.usuario.ejemplokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_todos_equipos.*
import android.content.ClipData.Item
import android.util.Log

class TodosEquiposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_equipos)

        todosequipos_recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var equipos = ArrayList<Equipo>()

        val adapter = CustomAdapter(equipos)

        todosequipos_recycler.adapter = adapter

        Singleton.mMyRef.child("Equipos").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children

                equipos.removeAll(equipos)

                children.forEach{
                    val equipo: Equipo? = it.getValue(Equipo::class.java)
                    equipo!!.key = it.key
                    equipos.add(equipo!!)
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}
