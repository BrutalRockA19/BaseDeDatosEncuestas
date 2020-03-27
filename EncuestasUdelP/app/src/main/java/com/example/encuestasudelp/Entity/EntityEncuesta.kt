package com.example.encuestasudelp.util

import android.text.BoringLayout

class EntityEncuesta (
    var id:Int,
    var nombre:String,
    var ApellidoPaterno:String,
    var ApellidoMaterno:String,
    var correo:String,
    var genero:Int,
    var viajado:Int,
    var frecuencia:Int,
    var experiencia:Int,
    var ejecutiva:Boolean,
    var economica:Boolean,
    var promo:Boolean,
    var servicio:String,
    var mejora:String,
    var ofertas:Boolean,
    var user:Int,
    var date:String

){ constructor():this(0,"","","","",0,0,
                     0,0,false,false,false,"",
                     "",false,0,"")

}