package com.example.encuestasudelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.util.ListEncuestas
import kotlinx.android.synthetic.main.activity_misencuestas.*

class ListDelete : AppCompatActivity() {
   // var ListaEncuestas = ListEncuestas()
    var Lista2 = arrayListOf<String>()
    val encuestasDb = EncuestasDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_delete)
        //  Lista2 = ListaEncuestas.devuelveListEncuestaString()
        if(encuestasDb.encuestasGetAllString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@ListDelete, android.R.layout.simple_list_item_1,encuestasDb.encuestasGetAllString())
            encuestasDb.encuestasGetAll()
            ltvMisEncuestas.adapter=miAdaptador

            ltvMisEncuestas.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)
                /*Toast.makeText(
                    this@SurveysActivity,
                    "$position $id $itemSeleccionado",
                    Toast.LENGTH_SHORT
                ).show()*/
                var index= EncuestasDb.listStringIDS[position]
                Toast.makeText(
                    this@ListDelete,
                    "Seleccionaste la encuesta $index",
                    Toast.LENGTH_SHORT
                ).show()
//                 intent para detalle que le pasa el id de la encuesta
                 miDialogo("Eliminar a $index ",index.toInt()).show()


            }
        }






    }





   // miDialogo("Eliminar a $itemSeleccionado",id.toInt()).show()
    //------Dialogo
    private fun miDialogo(texto:String , id:Int): AlertDialog {
        //CONTEXTO ES DONDE TE UBICAS
        val miAlerta = AlertDialog.Builder(this@ListDelete)
        miAlerta.setTitle("Estar seguro que deseas eliminar?").setMessage(texto)
        miAlerta.setPositiveButton("SI") { dialog, which ->
            encuestasDb.encuestasDelete(id)
            Toast.makeText(this@ListDelete, "Ok,  Eliminado ", Toast.LENGTH_SHORT)
                .show()
            finish();
            startActivity(intent);
        }

        miAlerta.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(this@ListDelete, "clic en no .", Toast.LENGTH_SHORT).show()
        }
        return miAlerta.create()
    }



}