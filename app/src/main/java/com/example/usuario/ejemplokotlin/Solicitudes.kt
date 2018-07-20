package com.example.usuario.ejemplokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_solicitudes.*

class Solicitudes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitudes)

        var solicitudes = ArrayList<Solicitud>()
        val listItem = ArrayList<String>()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        listView_solicitudes.adapter = adaptador
        Singleton.mMyRef.child("Solicitudes").addValueEventListener(object  : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val children = p0.children

                children.forEach{
                    val solicitud: Solicitud? = it.getValue(Solicitud::class.java)

                    solicitudes.add(solicitud!!)
                }



                for(i in 0 until solicitudes.size){
                    listItem.add("${solicitudes[i].solicitante} quiere unirse al equipo ${solicitudes[i].equipoSolictado}")
                }

                adaptador.notifyDataSetChanged()
            }

        })

    }
}
