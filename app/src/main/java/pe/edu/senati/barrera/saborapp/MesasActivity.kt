package pe.edu.senati.barrera.saborapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.senati.barrera.saborapp.data.MesaDAO
import pe.edu.senati.barrera.saborapp.databinding.ActivityMesasBinding

class MesasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMesasBinding
    private lateinit var mesaDAO: MesaDAO
    private lateinit var adapter: MesaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMesasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mesaDAO = MesaDAO(this)

        adapter = MesaAdapter(emptyList())
        binding.rvMesas.layoutManager = LinearLayoutManager(this)
        binding.rvMesas.adapter = adapter
        binding.btnVolver.setOnClickListener { finish() }

        cargarMesas()

        binding.btnGuardarMesa.setOnClickListener {
            guardarMesa()
        }
    }

    private fun guardarMesa() {
        val numero = binding.etNumeroMesa.text.toString().toIntOrNull()
        val capacidad = binding.etCapacidad.text.toString().toIntOrNull()

        if (numero == null || capacidad == null) {
            Toast.makeText(this, "Ingresa número y capacidad válidos", Toast.LENGTH_SHORT).show()
            return
        }

        mesaDAO.insertar(numero, capacidad)
        Toast.makeText(this, "Mesa guardada", Toast.LENGTH_SHORT).show()

        binding.etNumeroMesa.setText("")
        binding.etCapacidad.setText("")

        cargarMesas()
    }

    private fun cargarMesas() {
        adapter.actualizarLista(mesaDAO.listarTodas())
    }
}