package com.example.encuestasudelp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasudelp.Data.EncuestasDb
import com.example.encuestasudelp.Data.EncuestasDb.Companion.listStringIDS
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.ListEncuestas
//import com.example.encuestasudelp.util.ListEncuestas.Companion.listStringIndex
import kotlinx.android.synthetic.main.activity_misencuestas.*

//mis encuestas
class SurveysActivity : AppCompatActivity() {

   // var ListaEncuestas = ListEncuestas()
        val encuestasDb = EncuestasDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_misencuestas)
      //  Lista2 = ListaEncuestas.devuelveListEncuestaString()
        if(encuestasDb.encuestasGetAllString().size >0){
        val miAdaptador = ArrayAdapter<String> (this@SurveysActivity, android.R.layout.simple_list_item_1,encuestasDb.encuestasGetAllString())
        encuestasDb.encuestasGetAll()
        ltvMisEncuestas.adapter=miAdaptador

        ltvMisEncuestas.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
            val itemSeleccionado = adapterView.getItemAtPosition(position)
            /*Toast.makeText(
                this@SurveysActivity,
                "$position $id $itemSeleccionado",
                Toast.LENGTH_SHORT
            ).show()*/
             var index= listStringIDS[position]
            Toast.makeText(
                this@SurveysActivity,
                "Seleccionaste la encuesta $index",
                Toast.LENGTH_SHORT
            ).show()
//                 intent para detalle que le pasa el id de la encuesta
            val intent = Intent(this@SurveysActivity, DetailActivity::class.java)
            intent.putExtra("ID", index)
            startActivity(intent)
        }
        }






    }
}