package com.example.project01.data

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
)