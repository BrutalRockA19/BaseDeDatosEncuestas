package com.example.encuestasudelp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.ListEncuestas
import com.example.encuestasudelp.util.ListEncuestas.Companion.listaEncuesta
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_misencuestas.*

class DetailActivity : AppCompatActivity() {
    val encuestasDb = EncuestasDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //------------------------------------------------------------------------
        val position = intent.extras.getString("ID")
       // Log.d("udelp","$user")
        // Toast.makeText(this@HomeActivity,"Hola $user", Toast.LENGTH_SHORT).show()
        var encuesta = EntityEncuesta()
        //encuesta = listaEncuesta[position.toInt()]
        var idencuesta = position.toInt()
        encuesta = encuestasDb.encuestasGetOne(idencuesta)
        var genero =""
        var viajado=""
        var frecuencia=""
        var exp=""
        var ofertas=""
        //val numEncuesta = ((position.toInt()))
        txvNombreDetalle.setText("${encuesta.nombre.toString()}")
        txvNumeroEncuesta.setText(":${idencuesta.toString()}")
        txvApellidoPaternoDetalle.setText("${encuesta.ApellidoPaterno.toString()}")
        txvApellidoMaternoDetalle.setText("${encuesta.ApellidoMaterno.toString()}")
        txvCorreoDetalle.setText("${encuesta.correo.toString()}")
        //meter if 0/1 genero
        if(encuesta.genero==1){
             genero= "Masculino"
        }else{
            genero= "Femenino"
        }
        txvGeneroDetalle.setText("$genero")
        if(encuesta.viajado==1){
            viajado= "Si ha viajadado en AeroPatito"
        }else{
            viajado= "No se como pudiste contestar , la traicion hermano"
        }
        txvViajadoDetalle.setText("$viajado")
        when(encuesta.frecuencia){
            1->{
                frecuencia = "1 a 3 veces al año"
            }
            2->{
                frecuencia = "4 a 7 veces al año"
            }
            3->{
                frecuencia = "mas de 7 veces al año"
            }
        }
        txvFrecuenciaDetalle.setText("$frecuencia")
        when(encuesta.experiencia){
            1->{
                exp="Mala"
            }
            2->{
                exp="Regular"
            }
            3->{
                exp="Buena"
            }
            4->{
                exp="Excelente servicio 5 estrellas"
            }
        }
        txvExperienciaDetalle.setText("$exp")
        //?
        var esquema = ""
        if(encuesta.economica == true){
            esquema += "Económica "
        }
        if(encuesta.promo == true){
            esquema += "Promo "
        }
        if(encuesta.ejecutiva){
            esquema += "Ejecutiva "
        }



        txvEsquemaDetalle.setText("$esquema")

       // txvEsquemaDetalle.setText("${encuesta.ejecutiva.toString()} ${encuesta.promo.toString()} ${encuesta.economica.toString()}")

        txvServicioDetalle.setText("${encuesta.servicio.toString()}")
       // txvMejorasDetalle.setText("${encuesta.mejora.toString()}")
        txvMejorasDetalle
        txvMejorasDetalle.setText("${encuesta.mejora}")
        if(encuesta.ofertas==true){
            ofertas = "Quiero recibir ofertas y descuentos"
        }else{
            ofertas = "No quiero recibir ofertas y descuentos"
        }
        txvOfertasDescuentosDetalle.setText("$ofertas")
        //Log.d("Usuario",encuesta.user)

    }
}