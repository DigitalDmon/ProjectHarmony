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

    private var onItemClickListener: ((Radio) -> Unit)? = null

    inner class RadioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioPictureMedium: ImageView = view.findViewById(R.id.image_view_radio_picture_medium)
        val radioTitle: TextView = view.findViewById(R.id.text_view_radio_title)
        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(radio[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_radio, parent, false)
        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radio[position]
        holder.radioTitle.text = radio.title
        Picasso.get().load(radio.pictureMedium).into(holder.radioPictureMedium)
    }

    override fun getItemCount(): Int = radio.size

    fun updateData(newData: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = radio.size
            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return radio[oldItemPosition].id == (newData[newItemPosition] as Radio).id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return radio[oldItemPosition].id == (newData[newItemPosition] as Radio).id
            }
        })
        radio = newData as List<Radio>
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: (Radio) -> Unit) {
        onItemClickListener = listener
    }

}