package com.example.usuario.ejemplokotlin

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_equipo.view.*
import org.jetbrains.anko.toast

class CustomAdapter (val equiposList: ArrayList<Equipo>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_equipo, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return equiposList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipo: Equipo = equiposList[position]

        holder.creador_tv.setText(equipo.creador)
        holder.cant_tv.setText(equipo.cant.toString())
        holder.nombre_tv.setText(equipo.nombre)
        holder.ubicacion_tv.setText(equipo.ubicacion)
        holder.identidad_tv.setText(equipo.key)

        holder.retar_btn.setOnClickListener {
            Log.d("RETAR", "mensaje")
            var reto =  Reto(Singleton.mAuth!!.currentUser!!.email, equipo.nombre)
            Singleton.mMyRef.child("Retos").push().setValue(reto)
        }

        holder.unirme_btn.setOnClickListener{
            Log.d("UNIRME", "mensaje 2")
            var solicitud = Solicitud(Singleton.mAuth!!.currentUser!!.email, equipo.nombre)
            Singleton.mMyRef.child("Solicitudes").push().setValue(solicitud)
        }
    }


    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val nombre_tv = itemView.ie_nombre
        val creador_tv = itemView.ie_creador
        val cant_tv = itemView.ie_cantidad
        val ubicacion_tv = itemView.ie_ubicacion
        val retar_btn = itemView.btn_retar
        val unirme_btn = itemView.btn_unirme
        val identidad_tv = itemView.ie_identidad

    }
}