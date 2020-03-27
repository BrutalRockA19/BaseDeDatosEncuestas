package com.example.encuestasudelp.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConnectionDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
        db?.execSQL(CREATE_TABLE_SURVEYS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USERS)
        db?.execSQL(DROP_TABLE_SURVEYS)

    }

    fun openConnection(typeConnectionBD: Int): SQLiteDatabase {
        return when(typeConnectionBD){
            MODE_WRITE ->
                return writableDatabase
            MODE_READ ->
                return  readableDatabase
            else->
                return readableDatabase
        }
    }

    companion object{
        const val DATABASE_NAME = "ENCUESTAS"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME_USERS = "CTL_USERS"
        // const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME_STUDENTS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(20),Lastname VARCHAR(15),Gender INTEGER,Birthday DATE)"
        //MothersLastName
        const val CREATE_TABLE_USERS = "CREATE TABLE $TABLE_NAME_USERS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20),ApellidoP VARCHAR(25),ApellidoM VARCHAR(25),Genero INTEGER,Delegacion INTEGER,Direccion VARCHAR(100),EstadoCivil INTEGER,Correo VARCHAR(50),Password VARCHAR(25))"

        const val DROP_TABLE_USERS = "DROP TABLE IF EXISTS $TABLE_NAME_USERS"
        const val MODE_WRITE=1
        const val MODE_READ=2
        //----------------------------------------------------------------------------------------------------------------------------------

        const val TABLE_NAME_SURVEYS = "CTL_SURVEYS"
        // const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME_STUDENTS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(20),Lastname VARCHAR(15),Gender INTEGER,Birthday DATE)"

        const val CREATE_TABLE_SURVEYS = "CREATE TABLE $TABLE_NAME_SURVEYS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20),ApellidoP VARCHAR(25),ApellidoM VARCHAR(25),Correo VARCHAR(50),Genero INTEGER,Viajado INTEGER,Frecuencia INTEGER,Experiencia INTEGER,Ejecutiva INTEGER,Economica INTEGER,Promo INTEGER,Servicio VARCHAR(50),Mejora VARCHAR(50),Ofertas INTEGER,UserId INTEGER,Date DATE ,FOREIGN KEY (UserId) REFERENCES $TABLE_NAME_USERS (Id) )"

        const val DROP_TABLE_SURVEYS = "DROP TABLE IF EXISTS $TABLE_NAME_SURVEYS"


    }



}