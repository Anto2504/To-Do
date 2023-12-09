package com.example.todo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Tarea

class TareaAdapter(
    private var tareas: List<Tarea>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconoImageView: ImageView = itemView.findViewById(R.id.iconoImageView)
        val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        val fechaTextView: TextView = itemView.findViewById(R.id.fechaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]

        // Configura la interfaz de usuario con los datos de la tarea
        holder.iconoImageView.setImageResource(getIconoResId(tarea.tipo))
        holder.tituloTextView.text = tarea.titulo
        holder.fechaTextView.text = tarea.fecha

        // Configura el clic en la tarea
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    fun updateTareas(newTareas: List<Tarea>) {
        tareas = newTareas
        notifyDataSetChanged()
    }

    private fun getIconoResId(tipo: String): Int {
        // Lógica para asignar el recurso de icono según el tipo de tarea
        // Puedes implementar esta lógica según tus propios recursos
        return when (tipo) {
            "baja" -> R.drawable.low_priority
            "media" -> R.drawable.medium_priority
            "prioritaria" -> R.drawable.high_priority
            else -> R.drawable.progress
        }
    }
}
