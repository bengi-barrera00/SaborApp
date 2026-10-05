package pe.edu.senati.barrera.saborapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.edu.senati.barrera.saborapp.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val rolUsuario = "ADMIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (rolUsuario != "ADMIN") {
            binding.btnReportes.visibility = android.view.View.GONE
        }

        binding.btnPlatos.setOnClickListener {
            startActivity(Intent(this, PlatosActivity::class.java))
        }
        binding.btnMesas.setOnClickListener {
            startActivity(Intent(this, MesasActivity::class.java))
        }
        binding.btnPedidos.setOnClickListener {
            startActivity(Intent(this, PedidoActivity::class.java))
        }
        binding.btnReportes.setOnClickListener {
            startActivity(Intent(this, ReportesActivity::class.java))
        }
        binding.btnSalir.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}