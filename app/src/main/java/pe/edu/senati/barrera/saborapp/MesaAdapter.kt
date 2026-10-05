package pe.edu.senati.barrera.saborapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.senati.barrera.saborapp.data.MesaDAO
import pe.edu.senati.barrera.saborapp.databinding.ItemMesaBinding

class MesaAdapter(private var mesas: List<MesaDAO.Mesa>) :
    RecyclerView.Adapter<MesaAdapter.MesaViewHolder>() {

    class MesaViewHolder(val binding: ItemMesaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaViewHolder {
        val binding = ItemMesaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MesaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MesaViewHolder, position: Int) {
        val mesa = mesas[position]
        holder.binding.tvMesa.text = "Mesa ${mesa.numero} · ${mesa.capacidad} personas · ${mesa.estado}"
    }

    override fun getItemCount() = mesas.size

    fun actualizarLista(nuevaLista: List<MesaDAO.Mesa>) {
        mesas = nuevaLista
        notifyDataSetChanged()
    }
}