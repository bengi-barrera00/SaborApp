package pe.edu.senati.barrera.saborapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.senati.barrera.saborapp.data.PlatoDAO
import pe.edu.senati.barrera.saborapp.databinding.ActivityPlatosBinding

class PlatosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlatosBinding
    private lateinit var platoDAO: PlatoDAO
    private lateinit var adapter: PlatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        platoDAO = PlatoDAO(this)

        adapter = PlatoAdapter(emptyList())
        binding.rvPlatos.layoutManager = LinearLayoutManager(this)
        binding.rvPlatos.adapter = adapter

        cargarPlatos()

        binding.btnGuardarPlato.setOnClickListener {
            guardarPlato()
        }
        binding.btnVolver.setOnClickListener { finish() }
    }

    private fun guardarPlato() {
        val nombre = binding.etNombre.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val precio = binding.etPrecio.text.toString().toDoubleOrNull()
        val disponible = binding.cbDisponible.isChecked

        if (nombre.isEmpty()) {
            binding.etNombre.error = "Ingresa el nombre"
            return
        }

        if (precio == null || precio <= 0) {
            binding.etPrecio.error = "Precio inválido"
            return
        }

        platoDAO.insertar(nombre, categoria, precio, disponible)
        Toast.makeText(this, "Plato guardado", Toast.LENGTH_SHORT).show()

        binding.etNombre.setText("")
        binding.etCategoria.setText("")
        binding.etPrecio.setText("")
        binding.cbDisponible.isChecked = true

        cargarPlatos()
    }

    private fun cargarPlatos() {
        adapter.actualizarLista(platoDAO.listarTodos())
    }
}