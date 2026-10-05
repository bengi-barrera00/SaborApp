package pe.edu.senati.barrera.saborapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.senati.barrera.saborapp.data.PlatoDAO
import pe.edu.senati.barrera.saborapp.databinding.ItemPlatoBinding

class PlatoAdapter(private var platos: List<PlatoDAO.Plato>) :
    RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder>() {

    class PlatoViewHolder(val binding: ItemPlatoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatoViewHolder {
        val binding = ItemPlatoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlatoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlatoViewHolder, position: Int) {
        val plato = platos[position]
        holder.binding.tvNombrePlato.text = plato.nombre
        holder.binding.tvDetallePlato.text = "${plato.categoria} · S/ ${"%.2f".format(plato.precio)}"

        if (plato.disponible) {
            holder.binding.tvBadge.text = "Disponible"
            holder.binding.tvBadge.setBackgroundResource(R.drawable.bg_badge_ok)
            holder.binding.tvBadge.setTextColor(
                holder.itemView.context.getColor(R.color.sabor_texto_principal)
            )
        } else {
            holder.binding.tvBadge.text = "No disponible"
            holder.binding.tvBadge.setBackgroundResource(R.drawable.bg_badge_alerta)
            holder.binding.tvBadge.setTextColor(
                holder.itemView.context.getColor(R.color.sabor_primario)
            )
        }
    }

    override fun getItemCount() = platos.size

    fun actualizarLista(nuevaLista: List<PlatoDAO.Plato>) {
        platos = nuevaLista
        notifyDataSetChanged()
    }
}