package com.example.encuestasudelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.ListEncuestas
//import com.example.encuestasudelp.util.ListUsers.Companion.usuario_registrado
import com.example.encuestasudelp.util.validation.Companion.isEmailValid
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_realizar_encuesta.*
import kotlinx.android.synthetic.main.activity_registsro.*
import java.time.LocalDateTime


class RealizarEncuestaActivity : AppCompatActivity() {
    val misencuestas = ListEncuestas()
    //val miencuesta = EntityEncuesta()
    var user =""
    val encuestasDb = EncuestasDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realizar_encuesta)
         user = intent.extras.getString("usuario")
        btnEnviar.setOnClickListener {
            val miencuesta = EntityEncuesta()
            if (edtNombreE.text.toString().trim().isNotEmpty()) {
                //Toast.makeText(this@RealizarEncuestaActivity,"Que transa si le metiste datos",Toast.LENGTH_LONG).show()
               // Snackbar.make(it, "Que transa si le metiste datos", Snackbar.LENGTH_SHORT).show()
                //Aqui marcara error hasta tener la clase kotlin encuesta
                  miencuesta.nombre = edtNombreE.text.toString()
                //------------------Anidando if para que si el nombre esta lleno se siga al siguiente campo
                if (edtApellidoPaternoE.text.toString().trim().isNotEmpty()) {
                    miencuesta.ApellidoPaterno = edtApellidoPaternoE.text.toString()

                    if (edtApellidoMaternoE.text.toString().trim().isNotEmpty()) {
                        miencuesta.ApellidoMaterno = edtApellidoMaternoE.text.toString()
                            //validando que sea email
                        if ((etdCorreoEncuesta.text.toString().trim().isNotEmpty()) and (isEmailValid(etdCorreoEncuesta.text.toString().trim().toString()))){
                            miencuesta.correo = etdCorreoEncuesta.text.toString()
                            val generoposition = spnGeneroE.selectedItemPosition

                            if (generoposition > 0){
                                    miencuesta.genero = generoposition
                                val genero = spnGeneroE.getSelectedItemId().toString()
                                Log.d("udelp","Seleccionaste Genero $genero")
                                val aeropatitoposition = spnAero.selectedItemPosition
                                if (aeropatitoposition > 0){

                                    miencuesta.viajado = aeropatitoposition

                                    val frecuenciaposition = spnFrecuencia.selectedItemPosition

                                    if (frecuenciaposition > 0){
                                        miencuesta.frecuencia = frecuenciaposition
                                        val experienciaposition =spnExperiencia.selectedItemPosition
                                        if (experienciaposition > 0){
                                           miencuesta.experiencia = experienciaposition

                                            val selectedServicio = rdgServicios.checkedRadioButtonId
                                            if (selectedServicio != -1){
                                                when(selectedServicio){
                                                    rdbExcelente.id->{
                                                       miencuesta.servicio = "Excelente"
                                                    }
                                                    rdbBueno.id->{
                                                        miencuesta.servicio  = "Bueno"
                                                    }
                                                    rdbMalo.id->{
                                                        miencuesta.servicio  = "Malo"
                                                    }
                                                }

                                                if((ckbEconomica.isChecked) or (ckbEjecutiva.isChecked) or (ckbPromo.isChecked)){
                                                        if(ckbEconomica.isChecked){
                                                            miencuesta.economica = true
                                                        }
                                                        if(ckbPromo.isChecked){
                                                            miencuesta.promo = true
                                                        }
                                                        if(ckbEjecutiva.isChecked){
                                                            miencuesta.ejecutiva = true
                                                        }


                                                    //Toast.makeText(this@RealizarEncuestaActivity,"Se guardo el estudiante ${miencuesta}",Toast.LENGTH_SHORT).show()
                                                    Log.d("checkbox",miencuesta.correo)
                                                    if(swtOfertasDescuentos.isChecked){
                                                        miencuesta.ofertas =true
                                                    }
                                                    miencuesta.mejora = edtMejorarADD.text.toString()
                                                    //miencuesta.mejora = edtMejorar.text.toString()
                                                   // miencuesta.date= LocalDateTime.now() .toString()
                                                    miencuesta.user = usuario_registrado.id//cuando sea con base de datos cambiar por id
                                                    // Log.d("tamañoLISTA",misencuestas.agregarEncuestaPRUEBA(miencuesta).toString())
                                                    encuestasDb.encuestasAdd(miencuesta)
                                                    Toast.makeText(this@RealizarEncuestaActivity,"Guardado by $user txt ${edtMejorarADD.text.toString()}",Toast.LENGTH_SHORT).show()
                                                    //--------------Limpiar
                                                    edtNombreE.text.clear()
                                                    edtApellidoPaternoE.text.clear()
                                                    edtApellidoMaternoE.text.clear()
                                                    etdCorreoEncuesta.text.clear()
                                                    spnGeneroE.setSelection(0)
                                                    spnAero.setSelection(0)
                                                    spnFrecuencia.setSelection(0)
                                                    spnExperiencia.setSelection(0)
                                                    ckbEconomica.isChecked = false
                                                    ckbPromo.isChecked = false
                                                    ckbEjecutiva.isChecked = false
                                                    rdgServicios.clearCheck()
                                                    edtMejorarADD.text.clear()
                                                    swtOfertasDescuentos.isChecked = false
//                                                   --------------------------------

                                                }else{
                                                    Snackbar.make(it,"Selecciona que tipo de esquema usaste ",Snackbar.LENGTH_SHORT).show()

                                                }

                                            }else{
                                                Snackbar.make(it,"Selecciona como es nuestro servicio",Snackbar.LENGTH_SHORT).show()
                                            }


                                        }else{
                                            Snackbar.make(it,"Selecciona tu experiencia",Snackbar.LENGTH_SHORT).show()
                                        }

                                    }else{
                                        Snackbar.make(it, "Por favor selecciona la frecuencia", Snackbar.LENGTH_SHORT).show()
                                    }

                                }else{
                                    Snackbar.make(it, "Por favor selecciona si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
                                }


                            }else{
                                Snackbar.make(it, "Por favor selecciona Genero", Snackbar.LENGTH_SHORT).show()
                            }


                        }else{
                            Snackbar.make(it, "Por favor ingresa el Correo", Snackbar.LENGTH_SHORT).show()
                        }


                    } else {
                        //Toast.makeText(this@RealizarEncuestaActivity,"Te dije que le metieras datos culo",Toast.LENGTH_LONG).show()
                        Snackbar.make(it, "Por favor ingresa el Apellido Materno", Snackbar.LENGTH_SHORT).show()
                    }



                } else {
                    //Toast.makeText(this@RealizarEncuestaActivity,"Te dije que le metieras datos culo",Toast.LENGTH_LONG).show()
                    Snackbar.make(it, "Por favor ingresa el Apellido Paterno", Snackbar.LENGTH_SHORT).show()
                }


            } else {
                //Toast.makeText(this@RealizarEncuestaActivity,"Te dije que le metieras datos culo",Toast.LENGTH_LONG).show()
                Snackbar.make(it, "Por favor ingresa el nombre", Snackbar.LENGTH_SHORT).show()


            }

        }
        }
}
