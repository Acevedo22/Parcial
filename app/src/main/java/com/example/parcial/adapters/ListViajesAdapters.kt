package com.example.parcial.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.ShowActivity
import com.example.parcial.entidades.Viajes
import java.util.ArrayList
import java.util.stream.Collectors

class ListViajesAdapters(private val listaViajes: ArrayList<Viajes>) : RecyclerView.Adapter<ListViajesAdapters.ViajesViewHolder>() {

    // Mantiene una copia original de la lista de viajes
    private val listaOriginal: ArrayList<Viajes> = ArrayList(listaViajes)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajesViewHolder {
        // Inflar el layout del ítem de la lista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_verb, parent, false)
        return ViajesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViajesViewHolder, position: Int) {
        // Asignar el destino al TextView
        val viaje = listaViajes[position]
        holder.viewViaje.text = viaje.Destino
    }

    // Filtrar los viajes por texto ingresado
    fun filtrado(txtBuscar: String) {
        if (txtBuscar.isEmpty()) {
            // Restaurar la lista original si el campo de búsqueda está vacío
            listaViajes.clear()
            listaViajes.addAll(listaOriginal)
        } else {
            // Filtrar la lista de viajes
            val collection: MutableList<Viajes>? = listaOriginal.stream()
                .filter { i -> i.Destino!!.toLowerCase().contains(txtBuscar.toLowerCase()) }
                .collect(Collectors.toList())

            listaViajes.clear()
            if (collection != null) {
                listaViajes.addAll(collection)
            }
        }
        notifyDataSetChanged() // Notificar al adaptador que la lista ha cambiado
    }

    override fun getItemCount(): Int {
        return listaViajes.size // Retornar el tamaño actual de la lista
    }

    inner class ViajesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencia al TextView que muestra el destino
        val viewViaje: TextView = itemView.findViewById(R.id.viewViaje)

        init {
            // Configuración del click listener para abrir la actividad `ShowActivity`
            itemView.setOnClickListener {
                val context: Context = itemView.context
                val intent = Intent(context, ShowActivity::class.java)
                intent.putExtra("ID", listaViajes[adapterPosition].id) // Pasar el ID del viaje
                context.startActivity(intent) // Iniciar la actividad
            }
        }
    }
}
