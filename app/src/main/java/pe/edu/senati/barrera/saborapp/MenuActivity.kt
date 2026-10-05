package pe.edu.senati.barrera.saborapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.edu.senati.barrera.saborapp.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rolUsuario = intent.getStringExtra("rol") ?: "MOZO"
        val nombreUsuario = intent.getStringExtra("nombreUsuario") ?: ""

        binding.tvAvatarInicial.text = nombreUsuario.firstOrNull()?.uppercase() ?: "?"
        binding.tvSaludo.text = "Hola, $nombreUsuario"
        binding.tvRol.text = "Rol: " + if (rolUsuario == "ADMIN") "Administrador" else "Mozo"

        configurarFila(binding.filaPlatos.root, "P", "Platos") {
            startActivity(Intent(this, PlatosActivity::class.java))
        }
        configurarFila(binding.filaMesas.root, "M", "Mesas") {
            startActivity(Intent(this, MesasActivity::class.java))
        }
        configurarFila(binding.filaPedidos.root, "P", "Pedidos") {
            startActivity(Intent(this, PedidoActivity::class.java))
        }
        if (rolUsuario == "ADMIN") {
            configurarFila(binding.filaReportes.root, "R", "Reportes") {
                startActivity(Intent(this, ReportesActivity::class.java))
            }
        } else {
            binding.filaReportes.root.visibility = android.view.View.GONE
        }

        binding.btnSalir.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun configurarFila(fila: android.view.View, letra: String, titulo: String, accion: () -> Unit) {
        fila.findViewById<android.widget.TextView>(R.id.tvIconoFila).text = letra
        fila.findViewById<android.widget.TextView>(R.id.tvTituloFila).text = titulo
        fila.setOnClickListener { accion() }
    }
}