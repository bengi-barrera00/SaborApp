package pe.edu.senati.barrera.saborapp.data

import android.content.ContentValues
import android.content.Context

class PlatoDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    data class Plato(
        val id: Int,
        val nombre: String,
        val categoria: String,
        val precio: Double,
        val disponible: Boolean
    )

    fun insertar(nombre: String, categoria: String, precio: Double, disponible: Boolean): Long {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("categoria", categoria)
            put("precio", precio)
            put("disponible", if (disponible) 1 else 0)
        }
        val id = db.insert("plato", null, valores)
        db.close()
        return id
    }

    fun listarTodos(): List<Plato> {
        val lista = mutableListOf<Plato>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM plato ORDER BY nombre", null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Plato(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                        categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria")),
                        precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
                        disponible = cursor.getInt(cursor.getColumnIndexOrThrow("disponible")) == 1
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
}