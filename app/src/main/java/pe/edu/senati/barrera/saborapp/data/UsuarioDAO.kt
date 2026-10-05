package pe.edu.senati.barrera.saborapp.data

import android.content.Context

class UsuarioDAO(context: Context) {

    private val dbHelper = DBHelper(context)

    data class Usuario(val id: Int, val usuario: String, val rol: String)

    fun validarUsuario(usuario: String, clave: String): Usuario? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT id, usuario, rol FROM usuario WHERE usuario = ? AND clave = ?",
            arrayOf(usuario, clave)
        )

        var resultado: Usuario? = null
        if (cursor.moveToFirst()) {
            resultado = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                usuario = cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                rol = cursor.getString(cursor.getColumnIndexOrThrow("rol"))
            )
        }
        cursor.close()
        db.close()
        return resultado
    }
}