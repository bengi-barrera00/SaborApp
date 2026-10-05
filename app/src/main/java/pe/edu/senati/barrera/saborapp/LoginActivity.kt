package pe.edu.senati.barrera.saborapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import pe.edu.senati.barrera.saborapp.databinding.ActivityLoginBinding
import pe.edu.senati.barrera.saborapp.data.UsuarioDAO

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarioDAO = UsuarioDAO(this)

        binding.btnIngresar.setOnClickListener {
            validarCampos()
        }
    }

    private fun validarCampos() {
        val usuario = binding.etUsuario.text.toString().trim()
        val clave = binding.etClave.text.toString().trim()

        var camposValidos = true

        if (usuario.isEmpty()) {
            binding.etUsuario.error = "Ingresa tu usuario"
            camposValidos = false
        }

        if (clave.isEmpty()) {
            binding.tilClave.error = "Ingresa tu contraseña"
            camposValidos = false
        } else {
            binding.tilClave.error = null
        }

        if (!camposValidos) return

        val usuarioValidado = usuarioDAO.validarUsuario(usuario, clave)
        if (usuarioValidado != null) {
            val intento = Intent(this, MenuActivity::class.java)
            intento.putExtra("rol", usuarioValidado.rol)
            intento.putExtra("nombreUsuario", usuarioValidado.usuario)
            startActivity(intento)
            finish()
        } else {
            android.widget.Toast.makeText(this, "Credenciales incorrectas", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}