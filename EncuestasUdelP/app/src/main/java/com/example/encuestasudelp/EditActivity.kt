package com.example.encuestasudelp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.Data.UsersDb
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.ListEncuestas
import com.example.encuestasudelp.util.ListEncuestas.Companion.listaEncuesta
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.btnEnviar
import kotlinx.android.synthetic.main.activity_edit.ckbEconomica
import kotlinx.android.synthetic.main.activity_edit.ckbEjecutiva
import kotlinx.android.synthetic.main.activity_edit.ckbPromo
import kotlinx.android.synthetic.main.activity_edit.edtMejorar
import kotlinx.android.synthetic.main.activity_edit.edtNombreE
import kotlinx.android.synthetic.main.activity_edit.rdbBueno
import kotlinx.android.synthetic.main.activity_edit.rdbExcelente
import kotlinx.android.synthetic.main.activity_edit.rdbMalo
import kotlinx.android.synthetic.main.activity_edit.rdgServicios
import kotlinx.android.synthetic.main.activity_edit.spnAero
import kotlinx.android.synthetic.main.activity_edit.spnExperiencia
import kotlinx.android.synthetic.main.activity_edit.spnFrecuencia
import kotlinx.android.synthetic.main.activity_edit.swtOfertasDescuentos
import kotlinx.android.synthetic.main.activity_realizar_encuesta.*

class EditActivity : AppCompatActivity() {
    //val encuestas = ListEncuestas()
    var miencuesta = EntityEncuesta()
    val encuestasDb = EncuestasDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val position = intent.extras?.getString("ID")

        if (position != null) {
            miencuesta = encuestasDb.encuestasGetOne(position.toInt())
            // miencuesta = listaEncuesta[position.toInt()]
        }
        //edtNombreE.setText("${miencuesta.nombre}")
        edtNombreE.setText("${miencuesta.nombre.toString()}")
        edtApellidosEditar.setText("${miencuesta.ApellidoPaterno.toString()} ${miencuesta.ApellidoMaterno.toString()}")
        etdCorreoEditar.setText("${miencuesta.correo.toString()}")
        var genero=""
        if (miencuesta.genero == 1) {
            genero = "Masculino"
        } else {
            genero = "Femenino"
        }
        txvGeneroEditar.setText("$genero")
        spnAero.setSelection(miencuesta.viajado)
        spnFrecuencia.setSelection(miencuesta.frecuencia)
        spnExperiencia.setSelection(miencuesta.experiencia)
        edtMejorar.setText(miencuesta.mejora)
        if(miencuesta.promo){
            ckbPromo.isChecked = true
        }
        if(miencuesta.ejecutiva){
           ckbEjecutiva.isChecked = true
        }
        if(miencuesta.economica){
            ckbEconomica.isChecked = true
        }
        if(miencuesta.servicio == "Excelente"){
            rdbExcelente.isChecked =true

        }
        if(miencuesta.servicio == "Bueno"){
            rdbBueno.isChecked =true

        }
        if(miencuesta.servicio == "Malo"){
            rdbMalo.isChecked =true

        }
       if(miencuesta.ofertas == true){
           swtOfertasDescuentos.isChecked = true
       }


        btnEnviar.setOnClickListener {
           // var miencuesta = EntityEncuesta()


            var viajado = ""
            var frecuencia = ""
            var exp = ""
            var ofertas = ""


            val aeropatitoposition = spnAero.selectedItemPosition
            if (aeropatitoposition > 0) {
                miencuesta.viajado = aeropatitoposition

                val frecuenciaposition = spnFrecuencia.selectedItemPosition
                if (frecuenciaposition > 0) {
                    miencuesta.frecuencia = frecuenciaposition
                    val experienciaposition = spnExperiencia.selectedItemPosition
                    if (experienciaposition > 0) {
                        miencuesta.experiencia = experienciaposition

                        val selectedServicio = rdgServicios.checkedRadioButtonId
                        if (selectedServicio != -1) {
                            when (selectedServicio) {
                                rdbExcelente.id -> {
                                    miencuesta.servicio = "Excelente"
                                }
                                rdbBueno.id -> {
                                    miencuesta.servicio = "Bueno"
                                }
                                rdbMalo.id -> {
                                    miencuesta.servicio = "Malo"
                                }
                            }//fin when
                            if ((ckbEconomica.isChecked) or (ckbEjecutiva.isChecked) or (ckbPromo.isChecked)) {
                                //val encuesta.aqui ver los checkbox = True
                                if(ckbEconomica.isChecked){
                                    miencuesta.economica = true
                                }
                                if(ckbPromo.isChecked){
                                    miencuesta.promo = true
                                }
                                if(ckbEjecutiva.isChecked){
                                    miencuesta.ejecutiva = true
                                }
                                //   misencuestas.agregarEncuesta()
                                //Toast.makeText(this@RealizarEncuestaActivity,"Se guardo el estudiante ${miencuesta}",Toast.LENGTH_SHORT).show()
                                Log.d("udelp", miencuesta.correo)
                               
                                    miencuesta.ofertas = swtOfertasDescuentos.isChecked

                                miencuesta.mejora = edtMejorar.text.toString()

                                if (position != null) {
                                    miencuesta.user =usuario_registrado.id
                                    encuestasDb.encuestasEdit(miencuesta)
                                   //Log.d("tamañoLISTA",encuestas.editarEncuesta(position.toInt(),miencuesta).toString())
                                }


                                 Toast.makeText(this@EditActivity,"Se guardo la encuesta ${miencuesta.nombre} ${miencuesta.ApellidoPaterno} ",Toast.LENGTH_SHORT).show()

                            } else {//fin checkbox
                                Snackbar.make(it,"Selecciona que tipo de esquema usaste ", Snackbar.LENGTH_SHORT).show()

                            }
                        }else{
                            Snackbar.make(it,"Selecciona como es nuestro servicio",Snackbar.LENGTH_SHORT).show()
                        }//fin servicio
                    }else{
                        Snackbar.make(it,"Selecciona tu experiencia",Snackbar.LENGTH_SHORT).show()
                    }//fin exp
                }else{
                    Snackbar.make(it, "Por favor selecciona la frecuencia", Snackbar.LENGTH_SHORT).show()
                } //fin frecuencia
            } else{
                Snackbar.make(it, "Por favor selecciona si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
            }//fin aero
        } //fin del boton enviar
    }// fin oncreate
}