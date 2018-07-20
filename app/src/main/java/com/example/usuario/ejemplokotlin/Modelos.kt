package com.example.usuario.ejemplokotlin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class User(val email: String?, val edad: Int?, var nombre: String?, var ubicacion: String?, var compas: MutableList<String>?){
    constructor():this("",-1,"","",null)
}

data class Equipo(var nombre: String?, var ubicacion: String?, var cant: Int?, var creador: String?, var miembros: MutableList<String>?, var key:String?){
    constructor():this("", "", -1, null, null, null)
}

data class Reto(var retador: String?, var equipoRetado: String?){
    constructor():this("Nadie","Nadie")
}

data class Solicitud(var solicitante: String?, var equipoSolictado: String?){
    constructor():this("Nadie","Nadie")
}


object Singleton{

    var mAuth: FirebaseAuth?=null
    var database:FirebaseDatabase
    var mMyRef:DatabaseReference
    init{
        database = FirebaseDatabase.getInstance()
        mMyRef = database.reference
        mAuth = FirebaseAuth.getInstance()
    }

    var user:FirebaseUser?=null

}