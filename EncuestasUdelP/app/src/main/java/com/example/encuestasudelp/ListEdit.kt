package com.example.encuestasudelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.util.ListEncuestas
import kotlinx.android.synthetic.main.activity_list_edit.*
import kotlinx.android.synthetic.main.activity_misencuestas.*

class ListEdit : AppCompatActivity() {
    //var ListaEncuestas = ListEncuestas()
    var Lista2 = arrayListOf<String>()
    val encuestasDb = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_edit)
        //  Lista2 = ListaEncuestas.devuelveListEncuestaString()
        if(encuestasDb.encuestasGetAllString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@ListEdit, android.R.layout.simple_list_item_1,encuestasDb.encuestasGetAllString())
            encuestasDb.encuestasGetAll()
            ltvMisEncuestas_E.adapter=miAdaptador

            ltvMisEncuestas_E.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)
                /*Toast.makeText(
                    this@SurveysActivity,
                    "$position $id $itemSeleccionado",
                    Toast.LENGTH_SHORT
                ).show()*/
                var index= EncuestasDb.listStringIDS[position]
                Toast.makeText(
                    this@ListEdit,
                    "Seleccionaste la encuesta $index",
                    Toast.LENGTH_SHORT
                ).show()
//                 intent para detalle que le pasa el id de la encuesta
                val intent = Intent(this@ListEdit, EditActivity::class.java)
                intent.putExtra("ID", index)
                startActivity(intent)
            }
        }






    }



}

