package pe.edu.senati.barrera.saborapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import pe.edu.senati.barrera.saborapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            validarCampos()
        }
    }

    private fun validarCampos() {
        val usuario = binding.etUsuario.text.toString().trim()
        val clave = binding.etClave.text.toString().trim()

        var camposValidos = true

        if (usuario.isEmpty()) {
            binding.tilUsuario.error = "Ingresa tu usuario"
            camposValidos = false
        } else {
            binding.tilUsuario.error = null
        }

        if (clave.isEmpty()) {
            binding.tilClave.error = "Ingresa tu contraseña"
            camposValidos = false
        } else {
            binding.tilClave.error = null
        }

        if (!camposValidos) return

        if (usuario == "admin" && clave == "1234") {
            val intento = Intent(this, MenuActivity::class.java)
            startActivity(intento)
            finish()
        } else {
            android.widget.Toast.makeText(this, "Credenciales incorrectas", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}