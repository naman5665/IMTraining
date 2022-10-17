package com.example.project01.UI

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project01.BaseFragment.BaseFragment
import com.example.project01.R
import com.example.project01.data.Contact
import com.example.project01.data.ContactDatabase
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.view.*
import kotlinx.android.synthetic.main.update_fragment.*
import kotlinx.android.synthetic.main.update_fragment.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpdateFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.update_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bitmap = arguments?.getParcelable<Bitmap>("bitmap")
        image_updateImage.setImageBitmap(bitmap)

        launch{
            context?.let {
                val contact = ContactDatabase(it).contactDao().getContact()
                view.name_updateText.setText(contact[0].name)
                view.email_updateText.setText(contact[0].email)
                view.phone_updateText.setText(contact[0].phone)
            }
        }

        view.update_btn.setOnClickListener {

            val nameEditTextUpdated = name_updateText.text.toString().trim()
            val emailEditTextUpdated = email_updateText.text.toString().trim()
            val phoneEditTextUpdated = phone_updateText.text.toString().trim()


            launch {
                val updatedContact = Contact(1,nameEditTextUpdated,emailEditTextUpdated,phoneEditTextUpdated)
                context?.let {
                    ContactDatabase(it).contactDao().updateContact(updatedContact)
                    Toast.makeText(context,"Successfully Updated Data", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}