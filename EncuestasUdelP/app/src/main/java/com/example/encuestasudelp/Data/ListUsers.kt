package com.example.encuestasudelp.util

import android.util.Log

class ListUsers {
    companion object {
        val listaUsers = arrayListOf<EntityUser>()
       // var usuario_registrado = ""

        //private  val listString = arrayListOf<String>()
    }
    public fun agregarUsuario(user:EntityUser): Int{

        //val encuestaR = EntityEncuesta(nombre,apellidoP,apellidoM,correo,genero,viajado,frecuencia,experiencia,ejecutiva,economica,promo,servicio,mejora,ofertas)
        listaUsers.add(user)

        Log.d("encuestasUDELP","$user")

        return listaUsers.size
    }
    public fun buscarUsuario(login: EntityUser): Int {
        var encontrado =- 1
        for (user in listaUsers){
            if(login.nombre == user.nombre && login.password == user.password){
                encontrado = 1
            }
        }
        return encontrado

    }




}