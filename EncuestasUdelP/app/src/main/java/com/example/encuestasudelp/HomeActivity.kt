package com.example.encuestasudelp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado
//import com.example.encuestasudelp.util.ListUsers.Companion.usuario_registrado
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    var user =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
         user = intent.extras.getString("usuario")
        Log.d("udelp","$user")
       // Toast.makeText(this@HomeActivity,"Hola $user", Toast.LENGTH_SHORT).show()
        //txvUsuarioIdentificado.setText("$user")
        txvUsuarioIdentificado.setText(usuario_registrado.nombre.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmRealizarEncuesta->{
                Log.d("UdelP","Registro")

                //cambiar por registro encuesta

                val intent = Intent(this@HomeActivity,RealizarEncuestaActivity :: class.java)
                // user = intent.extras?.getString("usuario").toString()
                intent.putExtra("usuario", "$user");
                startActivity(intent)
            }
            R.id.itmMisEncuestas->{
                Log.d("UdelP","Registro")

                //cambiar por registro encuesta

                val intent = Intent(this@HomeActivity,SurveysActivity :: class.java)
                // user = intent.extras?.getString("usuario")
                intent.putExtra("usuario", "$user");
                startActivity(intent)
            }
            R.id.itmEliminarEncuesta->{
                Log.d("UdelP","Registro")

                //cambiar por registro encuesta

                val intent = Intent(this@HomeActivity,ListDelete :: class.java)
                //val user = intent.extras?.getString("usuario")
                intent.putExtra("usuario", "$user");
                startActivity(intent)
            }
            R.id.itmEditarEncuesta->{
                Log.d("UdelP","Registro")

                //cambiar por registro encuesta

                val intent = Intent(this@HomeActivity,ListEdit :: class.java)
               // val user = intent.extras.getString("usuario")
                intent.putExtra("usuario", "$user");
                startActivity(intent)
            }
        }

        return true
    }

}