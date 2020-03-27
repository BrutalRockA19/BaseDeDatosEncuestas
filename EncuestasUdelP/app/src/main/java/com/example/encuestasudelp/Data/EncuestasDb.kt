package com.example.encuestasudelp.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.encuestasudelp.util.EntityEncuesta
import com.example.encuestasudelp.util.EntityUser
import com.example.encuestasudelp.Data.UsersDb.Companion.usuario_registrado

class EncuestasDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    fun  encuestasAdd(Encuesta: EntityEncuesta): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NombreE,Encuesta.nombre)
        values.put(ApellidoP_E,Encuesta.ApellidoPaterno)
        values.put(ApellidoM_E,Encuesta.ApellidoMaterno)
        values.put(Correo_E,Encuesta.correo)
        values.put(Genero_E,Encuesta.genero)
        values.put(Viajado_E,Encuesta.viajado)
        values.put(Frecuencia_E,Encuesta.frecuencia)
        values.put(Experiencia_E,Encuesta.experiencia)
        values.put(Ejecutiva_E,Encuesta.ejecutiva)
        values.put(Economica_E,Encuesta.economica)
        values.put(Promo_E,Encuesta.promo)
        values.put(Servicio_E,Encuesta.servicio)
        values.put(Mejora_E,Encuesta.mejora)
        values.put(Ofertas_E,Encuesta.ofertas)
        values.put(UserId_E,Encuesta.user)
        values.put(Date_E,Encuesta.date)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_SURVEYS,null,values)
    }

    fun  encuestasEdit(Encuesta: EntityEncuesta): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NombreE,Encuesta.nombre)
        values.put(ApellidoP_E,Encuesta.ApellidoPaterno)
        values.put(ApellidoM_E,Encuesta.ApellidoMaterno)
        values.put(Correo_E,Encuesta.correo)
        values.put(Genero_E,Encuesta.genero)
        values.put(Viajado_E,Encuesta.viajado)
        values.put(Frecuencia_E,Encuesta.frecuencia)
        values.put(Experiencia_E,Encuesta.experiencia)
        values.put(Ejecutiva_E,Encuesta.ejecutiva)
        values.put(Economica_E,Encuesta.economica)
        values.put(Promo_E,Encuesta.promo)
        values.put(Servicio_E,Encuesta.servicio)
        values.put(Mejora_E,Encuesta.mejora)
        values.put(Ofertas_E,Encuesta.ofertas)
        values.put(UserId_E,Encuesta.user)
        values.put(Date_E,Encuesta.date)
        var selection = "Id=?"
        var args = arrayOf(Encuesta.id.toString())

        return sqliteDataBase.update(ConnectionDb.TABLE_NAME_SURVEYS,values,selection,args)

    }
    fun  encuestasDelete(idEncuesta:Int): Int {

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)
        var selection = "Id=?"
        var args = arrayOf(idEncuesta.toString())
        sqliteDataBase.delete(ConnectionDb.TABLE_NAME_SURVEYS,selection,args)
        return sqliteDataBase.delete(ConnectionDb.TABLE_NAME_SURVEYS,selection,args)

    }
    fun encuestasGetAll(){

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NombreE,ApellidoP_E, ApellidoM_E,
            Correo_E,Genero_E,Viajado_E,Frecuencia_E,
            Experiencia_E, Ejecutiva_E, Economica_E, Promo_E, Servicio_E, Mejora_E, Ofertas_E,
            UserId_E, Date_E)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_SURVEYS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {

                Log.d("UDELP_ALLENCUESTAS","${cursor.getInt(0)} " +
                        "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                        "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                        "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                        "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                        "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")
                     Log.d("MEJORAS",cursor.getString(13))
            }while (cursor.moveToNext())

        }
    }

    fun encuestasGetOne(idencuesta:Int):EntityEncuesta{

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NombreE,ApellidoP_E, ApellidoM_E,
            Correo_E,Genero_E,Viajado_E,Frecuencia_E,
            Experiencia_E, Ejecutiva_E, Economica_E, Promo_E, Servicio_E, Mejora_E, Ofertas_E,
            UserId_E, Date_E)
        var selection = "Id=?"
        var args = arrayOf(idencuesta.toString())
        //val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_SURVEYS,fields,null,null,null,null,null)
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_SURVEYS,fields,selection,args,null,null,null)
        var encuesta = EntityEncuesta()
//        var  flag = false
        if(cursor.moveToFirst()){
            var ejecutiva = cursor.getInt(9)
            var ejecutivaB = ( ejecutiva == 1)?:true
            var economica = cursor.getInt(10)
            var economicaB = ( economica == 1)?:true
            var promo = cursor.getInt(11)
            var promoB = ( promo == 1)?:true
            var ofertas = cursor.getInt(14)
            var ofertasB = ( ofertas == 1)?:true
          encuesta= EntityEncuesta( cursor.getInt(0),cursor.getString(1),cursor.getString(2),
              cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),
              cursor.getInt(7),cursor.getInt(8),ejecutivaB,economicaB,
              promoB,cursor.getString(12),cursor.getString(13),
              ofertasB,cursor.getInt(15),cursor.getString(16))
            Log.d("UDELP_ONEENCUESTAS","${cursor.getInt(0)} " +
                    "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                    "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                    "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                    "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                    "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")
            Log.d("MEJORAS",cursor.getString(13))

        }
        return  encuesta
    }
//----------------------------------------------------
    fun encuestasGetAllString(): Array<String>{
        listString.clear()
        listStringIDS.clear()
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NombreE,ApellidoP_E, ApellidoM_E,
            Correo_E,Genero_E,Viajado_E,Frecuencia_E,
            Experiencia_E, Ejecutiva_E, Economica_E, Promo_E, Servicio_E, Mejora_E, Ofertas_E,
            UserId_E, Date_E)

        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_SURVEYS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                if (usuario_registrado.id == cursor.getInt(15)){
                listString.add("${cursor.getString(1)} ${cursor.getString(2)}  ${cursor.getString(3)}")
                listStringIDS.add("${cursor.getInt(0)}")
                Log.d("UDELP","${cursor.getInt(0)} " +
                        "${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getString(3)} " +
                        "${cursor.getString(4)}  ${cursor.getInt(5)} ${cursor.getInt(6)} " +
                        "${cursor.getInt(7)} ${cursor.getInt(8)} ${cursor.getInt(9)} ${cursor.getInt(10)} " +
                        "${cursor.getInt(11)}  ${cursor.getString(12)}  ${cursor.getString(13)} " +
                        "${cursor.getInt(14)}  ${cursor.getInt(15)} ${cursor.getString(16)}")

                }
            }while (cursor.moveToNext())

        }
            val elems = listString
            return elems.toTypedArray()
    }




//------------------------------------------------------
    companion object {
        const val ID = "Id"
        const val NombreE = "Nombre"
        const val ApellidoP_E = "ApellidoP"
        const val ApellidoM_E = "ApellidoM"
        const val Correo_E ="Correo"
        const val Genero_E = "Genero"
        const val Viajado_E = "Viajado"
        const val Frecuencia_E ="Frecuencia"
        const val Experiencia_E   ="Experiencia"
        const val Ejecutiva_E = "Ejecutiva"
        const val Economica_E   = "Economica"
        const val Promo_E = "Promo"
        const val  Servicio_E ="Servicio"
        const val  Mejora_E = "Mejora"
        const val  Ofertas_E = "Ofertas"
        const val  UserId_E = "UserId"
        const val  Date_E = "Date"
        //Lista para el list view
        private  val listString = arrayListOf<String>()
        //lista ids
        val listStringIDS = arrayListOf<String>()

    }
}