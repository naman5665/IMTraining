package com.example.project01.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact_table WHERE id = 1")
    suspend fun getContact(): List<Contact>

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact_table ORDER BY id LIMIT 1")
    fun loadLastContact(): LiveData<Contact?>?

    @Query("SELECT * FROM contact_table")
    fun selectAllContacts(): List<Contact>



}