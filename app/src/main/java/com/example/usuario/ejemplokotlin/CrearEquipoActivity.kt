package com.example.usuario.ejemplokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_equipo.*

class CrearEquipoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)

        ce_crearequipo.setOnClickListener({
            var nombre = ce_nombreequipo.text.toString()
            var ubicacion = ce_ubicacionequipo.text.toString()
            var cant = ce_cantjugadores.text.toString().toInt()
            var equipo: Equipo = Equipo(nombre, ubicacion, cant, Singleton.mAuth!!.currentUser!!.email, null, null)
            var miembros: MutableList<String> = mutableListOf()
            miembros.add("Pedro")
            miembros.add("Hugo")
            equipo.miembros = miembros
            Singleton.mMyRef.child("Equipos").push().setValue(equipo)

        })



    }
}
