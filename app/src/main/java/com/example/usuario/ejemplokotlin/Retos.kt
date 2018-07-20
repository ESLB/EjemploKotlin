package com.example.usuario.ejemplokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_retos.*

class Retos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retos)


        var retos = ArrayList<Reto>()
        val listItem = ArrayList<String>()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem)
        listView_retos.adapter = adaptador
        Singleton.mMyRef.child("Retos").addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children

                children.forEach{
                    val reto: Reto? = it.getValue(Reto::class.java)

                    retos.add(reto!!)
                }



                for(i in 0 until retos.size){
                    listItem.add("${retos[i].retador} ha retado al equipo ${retos[i].equipoRetado}")
                }

                adaptador.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })




    }
}
