package pe.edu.senati.barrera.saborapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT UNIQUE,
                clave TEXT,
                rol TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE plato (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                categoria TEXT,
                precio REAL CHECK(precio > 0),
                disponible INTEGER
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE mesa (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                numero INTEGER UNIQUE,
                capacidad INTEGER,
                estado TEXT
            )
            """.trimIndent()
        )

        val valoresAdmin = ContentValues().apply {
            put("usuario", "admin")
            put("clave", "1234")
            put("rol", "ADMIN")
        }
        db.insert("usuario", null, valoresAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        const val DB_NAME = "saborapp.db"
        const val DB_VERSION = 1
    }
}