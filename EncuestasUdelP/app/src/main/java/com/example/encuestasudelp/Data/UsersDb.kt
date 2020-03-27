package com.example.encuestasudelp.Data

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context

import android.util.Log
import com.example.encuestasudelp.util.EntityUser
import com.example.encuestasudelp.util.ListUsers

class UsersDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    fun  usersAdd(User:EntityUser): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(Nombre,User.nombre)
        values.put(ApellidoP,User.apellidoPaterno)
        values.put(ApellidoM,User.apellidoMaterno)
        values.put(Genero,User.genero)
        values.put(Delegacion,User.delegacion)
        values.put(Direccion,User.direccion)

        values.put(EstadoCivil,User.estadoCivil)
        values.put(Correo,User.correo)
        values.put(Password,User.password)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_USERS,null,values)
    }

    fun usersGetOne(idUser: Int ):EntityUser{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, Nombre, ApellidoP, ApellidoM, Genero, Delegacion ,
            Direccion , EstadoCivil , Correo , Password )
        var selection = "Id=?"
        var args = arrayOf(idUser.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_USERS,fields,selection,args,null,null,null)
        var user = EntityUser()
        if(cursor.moveToFirst()){
            var id = cursor.getInt(0)
            var nombre = cursor.getString(1)
            var apellidoP = cursor.getString(2)
            var apellidoM = cursor.getString(3)
            var genero = cursor.getInt(4)
            var delegacion = cursor.getInt(5)
            var direccion = cursor.getString(6)
            var estadoCivil = cursor.getInt(7)
            var correo = cursor.getString(8)
            var passwd = cursor.getString(9)
            user = EntityUser(id.toInt(),"${nombre}","$apellidoP","$apellidoM",genero.toInt(),delegacion.toInt(),"$direccion",estadoCivil,"$correo","$passwd")
            Log.d("USERudelp","${id.toString()},${nombre},$apellidoP,$apellidoM,$genero,$delegacion,$direccion,$estadoCivil,$correo,$passwd")
         //   Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}  ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")
        }

        return user
    }

    fun usersGetAll() {
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(
            ID, Nombre, ApellidoP, ApellidoM, Genero, Delegacion,
            Direccion, EstadoCivil, Correo, Password
        )
        val cursor = sqliteDataBase.query(
            ConnectionDb.TABLE_NAME_USERS,
            fields,
            null,
            null,
            null,
            null,
            null
        )
        //var user = EntityUser()
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var nombre = cursor.getString(1)
                var apellidoP = cursor.getString(2)
                var apellidoM = cursor.getString(3)
                var genero = cursor.getInt(4)
                var delegacion = cursor.getInt(5)
                var direccion = cursor.getString(6)
                var estadoCivil = cursor.getInt(7)
                var correo = cursor.getString(8)
                var passwd = cursor.getString(9)
                // user = EntityUser(id,"${nombre}","$apellidoP","$apellidoM",genero,delegacion,"$direccion",estadoCivil,"$correo","$passwd")
                Log.d(
                    "USER_ALL",
                    "${id.toString()},${nombre},$apellidoP,$apellidoM,$genero,$delegacion,$direccion,$estadoCivil,$correo,$passwd"
                )
                //   Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}  ${cursor.getString(5)} ${cursor.getString(6)} ${cursor.getString(7)} ${cursor.getString(8)} ${cursor.getString(9)}")

            } while (cursor.moveToNext())
        }
    }


         fun loginUserdb(login: EntityUser): Int {
            var encontrado =- 1
             sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
             val fields = arrayOf(ID, Nombre, Password )
             val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_USERS,fields,null,null,null,null,null)
             if(cursor.moveToFirst()){
                 do{
                     if(cursor.getString(1)== login.nombre && cursor.getString(2)==login.password){
                         encontrado = cursor.getInt(0)
                         Log.d("LOGIN_UDELP","Usuario encontrado en la base de datos")
                     }
                 }while(cursor.moveToNext())
             }

            return encontrado

        }




    companion object {
        const val ID = "Id"
        const val Nombre = "Nombre"
        const val ApellidoP = "ApellidoP"
        const val ApellidoM = "ApellidoM"
        const val Genero = "Genero"
        const val Delegacion = "Delegacion"
        const val Direccion ="Direccion"
        const val EstadoCivil  ="EstadoCivil"
        const val Correo = "Correo"
        const val Password  = "Password"
        //Lista para el list view
        private  val listString = arrayListOf<String>()
        //lista ids
       // val listStringIDS = arrayListOf<String>()
        var usuario_registrado = EntityUser()
    }
}