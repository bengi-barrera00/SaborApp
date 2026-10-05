package pe.edu.senati.barrera.saborapp.data

import android.content.ContentValues
import android.content.Context

class MesaDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    data class Mesa(val id: Int, val numero: Int, val capacidad: Int, val estado: String)

    fun insertar(numero: Int, capacidad: Int): Long {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put("numero", numero)
            put("capacidad", capacidad)
            put("estado", "LIBRE")
        }
        val id = db.insert("mesa", null, valores)
        db.close()
        return id
    }

    fun listarTodas(): List<Mesa> {
        val lista = mutableListOf<Mesa>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM mesa ORDER BY numero", null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Mesa(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        numero = cursor.getInt(cursor.getColumnIndexOrThrow("numero")),
                        capacidad = cursor.getInt(cursor.getColumnIndexOrThrow("capacidad")),
                        estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return lista
    }
}