package com.example.project01.testingPurpose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project01.data.Persons
import com.example.project01.R

class MyAdapter(val persons: List<Persons>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_items,parent,false)
        return MyViewHolder((view))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = persons[position].name
        holder.email_id.text = persons[position].emailId
        holder.phone_number.text = persons[position].phoneNumber.toString()
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name_textView)
        val email_id = itemView.findViewById<TextView>(R.id.email_textView)
        val phone_number = itemView.findViewById<TextView>(R.id.phoneNumber_textView)
        val button_card = itemView.findViewById<Button>(R.id.button_card)
        val image_card = itemView.findViewById<ImageView>(R.id.image_background)
    }

}

