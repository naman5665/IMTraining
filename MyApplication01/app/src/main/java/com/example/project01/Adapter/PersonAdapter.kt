package com.example.project01.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project01.R
import com.example.project01.testingPurpose.UsersJson

class PersonAdapter(val context: FragmentActivity, val users: List<UsersJson>):
    RecyclerView.Adapter<PersonAdapter.UserViewHolder>(){

    internal var number: String? = ""

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name_textView)
        val email_id = itemView.findViewById<TextView>(R.id.email_textView)
        val phone_number = itemView.findViewById<TextView>(R.id.phoneNumber_textView)
        val image_card = itemView.findViewById<ImageView>(R.id.image_background)
        val button_card = itemView.findViewById<Button>(R.id.button_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_items,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.firstName
        holder.email_id.text = user.email
        holder.phone_number.text = user.phone
        Glide.with(context).load(user.image).into(holder.image_card)

        holder.button_card.setOnClickListener(){

            number = holder.phone_number.text.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
            it.context.startActivity(intent)
            Toast.makeText(context,"Dialer Opened ",Toast.LENGTH_SHORT).show()

        }

    }

    override fun getItemCount(): Int {
        return users.size
    }
}