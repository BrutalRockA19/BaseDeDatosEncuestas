package com.example.encuestasudelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.encuestasudelp.Data.UsersDb
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.EntityUser
import com.example.encuestasudelp.util.ListEncuestas
import com.example.encuestasudelp.util.ListUsers
import com.example.encuestasudelp.util.ListUsers.Companion.listaUsers
//import com.example.encuestasudelp.util.ListUsers.Companion.usuario_registrado
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val misusuario = ListUsers()
    var encontrado = 0
    val usersDB = UsersDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener {
            val user=edtUsuarioLogin.text.toString()
            val password=edtPasswordLogin.text.toString()


//revisar la busueda
            val user1 = EntityUser()
            user1.nombre = "admin"
            user1.password = "root"
            misusuario.agregarUsuario(user1)
            val user2 = EntityUser()
            user2.nombre = user
            user2.password = password
            //encontrado = misusuario.buscarUsuario(user2)
            encontrado = usersDB.loginUserdb(user2)
            if (encontrado >= 0){
                Snackbar.make(it,"Usuario encontrado con metodo $user $password", Snackbar.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity,"id $encontrado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity,HomeActivity::class.java)
                intent.putExtra("usuario", "$user");
                usuario_registrado = usersDB.usersGetOne(encontrado)
                usersDB.usersGetAll()
                startActivity(intent)
            }else{
                Snackbar.make(it,"No existe lo que mandaste bro", Snackbar.LENGTH_SHORT).show()
            }
            /*
            if(user.equals("admin") && password.equals("admin")){



                val intent = Intent(this@MainActivity,HomeActivity::class.java)
                intent.putExtra("usuario", "$user");
                startActivity(intent)


            }else
            {
                Toast.makeText(this@MainActivity,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()


            } */

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmRegistro->{
                Log.d("UdelP","Registro")
                val intent = Intent(this@MainActivity,RegistroActivity :: class.java)
                startActivity(intent)
            }

        }

        return true
    }


}
