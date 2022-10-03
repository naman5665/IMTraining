package com.example.project01.UI

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project01.BaseFragment.BaseFragment
import com.example.project01.R
import com.example.project01.data.Contact
import com.example.project01.data.ContactDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        /*
        To delete all tables:-
        view.delete_button.setOnClickListener {
            GlobalScope.launch {
                activity?.let { it1 -> ContactDatabase(it1.applicationContext).contactDao().deleteAll() }
            }
        }
         */

        view.login_btn.setOnClickListener {

            val nameEditText = name_editText.text.toString().trim()
            val emailEditText = email_editText.text.toString().trim()
            val phoneEditText = phone_editText.text.toString().trim()


            if(nameEditText.isEmpty() || emailEditText.isEmpty() || phoneEditText.isEmpty()){
                Toast.makeText(requireContext(),"Fill All fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            launch {

                val contact = Contact(1,nameEditText,emailEditText,phoneEditText)
                context?.let {
                    ContactDatabase(it).contactDao().insertContact(contact)
                    Toast.makeText(context,"Successfully Added Data",Toast.LENGTH_SHORT).show()
                }
            }


            disableEditText(name_editText)
            disableEditText(email_editText)
            disableEditText(phone_editText)

            fragmentChange(ProfileFragment())

        }

    }

    private fun disableEditText(editText: TextInputEditText) {
        editText.isFocusable = false
        editText.isEnabled = false
        editText.isCursorVisible = false
        editText.keyListener = null
        editText.setBackgroundColor(Color.TRANSPARENT)

    }


    private fun fragmentChange(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val view =  inflater.inflate(R.layout.login_fragment, container, false)
        return view
    }

}