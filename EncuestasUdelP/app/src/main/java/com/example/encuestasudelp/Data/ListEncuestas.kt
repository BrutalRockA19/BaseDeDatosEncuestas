package com.example.encuestasudelp.util

import android.util.Log
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado
//import com.example.encuestasudelp.util.ListUsers.Companion.usuario_registrado

class ListEncuestas {

    companion object {
         val listaEncuesta = arrayListOf<EntityEncuesta>()
        private  val listString = arrayListOf<String>()
          val listStringIndex = arrayListOf<String>()
    }



    public fun agregarEncuestaPRUEBA(encuestaR:EntityEncuesta): Int{

        //val encuestaR = EntityEncuesta(nombre,apellidoP,apellidoM,correo,genero,viajado,frecuencia,experiencia,ejecutiva,economica,promo,servicio,mejora,ofertas)
        listaEncuesta.add(encuestaR)

        Log.d("encuestasUDELP","$encuestaR")

        return listaEncuesta.size
    }

     public fun eliminarEncuesta(id: Int){
         listaEncuesta.removeAt(id)


     }
    public fun editarEncuestaPRUEBA(encuestaR:EntityEncuesta): Int{

        for((index,item)in listaEncuesta.withIndex())
        {
            if(item == listaEncuesta[index]){
                listaEncuesta.add(index, encuestaR)
                Log.d("encuestasUDELP","$encuestaR")
            }
        }
        return listaEncuesta.indexOf(encuestaR)
    }


     public fun editarEncuesta(id:Int,encuestaE:EntityEncuesta){
       /*  var encuestaEditada = EntityEncuesta()
            encuestaEditada = encuestaE
         listaEncuesta[id] =  encuestaEditada*/
         listaEncuesta.set(id,encuestaE)


         }



 /*
    public fun muestraEncuesta(){
        for((index,item) in listaEncuesta.withIndex()){
            Log.d("UDELP","$index $item")

        }
    }
*/


    public fun devuelveListEncuestaString(): Array<String> {
        listString.clear()
        listStringIndex.clear()
        var index = 0 //agregado
        for (entityEncuesta in listaEncuesta) {
            if(entityEncuesta.user == usuario_registrado.id){// agregaste el .nombre en base de datos sera .id
                listString.add(entityEncuesta.nombre)
                //Esto lo agrege
                listStringIndex.add(index.toString())
            }
            index ++// agregado
        }
        val elems = listString
        return elems.toTypedArray()

    }
    public fun devuelveListEncuesta(): Array<EntityEncuesta> {
        val elems = listaEncuesta
        return elems.toTypedArray()

    }


}