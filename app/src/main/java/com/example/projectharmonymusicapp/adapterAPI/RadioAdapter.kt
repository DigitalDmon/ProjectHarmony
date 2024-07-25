package com.example.projectharmonymusicapp.adapterAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projectharmonymusicapp.R
import com.example.projectharmonymusicapp.dataClasses.Radio
import com.squareup.picasso.Picasso

class RadioAdapter(private var radio: List<Radio>) : RecyclerView.Adapter<RadioAdapter.RadioViewHolder>() {

    class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioPictureMedium: ImageView = itemView.findViewById(R.id.image_view_radio_picture_medium)
        val radioTitle: TextView = itemView.findViewById(R.id.text_view_radio_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_radio, parent, false)
        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radio[position]
        holder.radioTitle.text = radio.title
        Picasso.get().load(radio.pictureBig).into(holder.radioPictureMedium)
    }

    override fun getItemCount(): Int = radio.size

    fun updateData(newData: List<Any>) {
        // Calcular las diferencias entre la lista actual y la nueva lista (puedes usar DiffUtil para esto)
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = radio.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return radio[oldItemPosition].id == newData[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return radio[oldItemPosition] == newData[newItemPosition]
            }
        })

        // Actualizar la lista
        radio = newData as List<Radio>

        // Aplicar los cambios al adaptador
        diffResult.dispatchUpdatesTo(this)
    }

}