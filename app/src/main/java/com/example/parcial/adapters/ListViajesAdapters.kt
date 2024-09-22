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
import java.util.List
import java.util.stream.Collectors

class ListViajesAdapters(private val listaViajes: ArrayList<Viajes>) : RecyclerView.Adapter<ListViajesAdapters.VerbsViewHolder>() {

    // VideoS
    private val listaOriginal: ArrayList<Viajes> = ArrayList(listaViajes)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerbsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_verb, parent, false)
        return VerbsViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerbsViewHolder, position: Int) {
        val viaje = listaViajes[position]
        holder.viewVerbo .text = viaje.Destino
        /*       holder.viewInd_Yo.text = verb.Ind_Yo
               holder.viewInd_Tu.text = verb.Ind_Tu
               holder.viewInd_El_Ella_Usted.text = verb.Ind_El_Ella_Usted
               holder.viewInd_Nosotros.text = verb.Ind_Nosotros
               holder.viewInd_Vosotros.text = verb.Ind_Vosotros
               holder.viewInd_Ellos.text = verb.Ind_Ellos
               holder.viewImp_Tu.text = verb.Imp_Tu
               holder.viewImp_Nosotros.text = verb.Imp_Nosotros
               holder.viewImp_Ellos_Ellas_Ustedes.text = verb.Imp_Ellos_Ellas_Ustedes*/
    }

    fun filtrado(txtBuscar: String) {
        val longitud = txtBuscar.length
        if (txtBuscar.length == 0) {
            listaViajes.clear()
            listaViajes.addAll(listaOriginal)
        } else {
            val collection: MutableList<Viajes>? = listaViajes.stream()
                .filter { i -> i.Destino!!.toLowerCase().contains(txtBuscar.toLowerCase()) }
                .collect(Collectors.toList())
            listaViajes.clear()
            if (collection != null) {
                listaViajes.addAll(collection)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listaViajes.size
    }
    inner class VerbsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewVerbo: TextView = itemView.findViewById(R.id.viewVerbo)


        init {
            itemView.setOnClickListener {
                val context: Context = itemView.context
                val intent = Intent(context, ShowActivity::class.java)
                intent.putExtra("ID", listaViajes[adapterPosition].id)
                context.startActivity(intent)
            }
        }
}

}