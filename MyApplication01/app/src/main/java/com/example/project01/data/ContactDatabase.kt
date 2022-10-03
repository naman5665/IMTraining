package com.example.project01.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 1 , exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object{

        @Volatile
        private var INSTANCE: ContactDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE?: synchronized(LOCK){
            INSTANCE?: buildtDatabase(context).also{
                INSTANCE = it
            }
        }

        fun buildtDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ContactDatabase::class.java,
            "ContactDataB"
        ).build()

        /*
        fun buildtDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ContactDatabase::class.java,"ContactDB").build()
                }

            }
            return INSTANCE!!
        }

         */

    }
}