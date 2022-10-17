package com.example.project01.UI

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.project01.BaseFragment.BaseFragment
import com.example.project01.data.Contact
import com.example.project01.data.ContactDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment() {

    private val contactData: List<Contact>? =
        context?.let { ContactDatabase(it).contactDao().selectAllContacts() }

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        image_editImage.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(DelicateCoroutinesApi::class)
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

        GlobalScope.launch {
            if(ContactDatabase(requireActivity().applicationContext).contactDao()
                    .selectAllContacts().isNotEmpty()
            ){

                disableEditText(name_editText)
                disableEditText(email_editText)
                disableEditText(phone_editText)

                //ToastUtils.show(context,"User Already Logged In")
            }
        }


        val login_image = requireView().findViewById<View>(com.example.project01.R.id.image_editImage) as ImageView
        login_image.setOnClickListener {
            contract.launch("image/*")
        }

        view.login_btn.setOnClickListener {

            val bundle = Bundle()
            val frag_profile = ProfileFragment()
            val frag_update = UpdateFragment()
            val bm = (image_editImage.drawable as BitmapDrawable).bitmap
            bundle.putParcelable("bitmap", bm)
            frag_profile.arguments = bundle
            frag_update.arguments = bundle


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

            fragmentChange(frag_profile)

        }

    }

    private fun disableEditText(editText: TextInputEditText) {
        editText.isClickable = false;
        editText.isFocusable = false
        editText.isEnabled = false
        editText.isCursorVisible = false
        editText.keyListener = null
        editText.setBackgroundColor(Color.TRANSPARENT)
    }


    private fun fragmentChange(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(com.example.project01.R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val view =  inflater.inflate(com.example.project01.R.layout.login_fragment, container, false)
        return view
    }
}

object ToastUtils {
    var toast: Toast? = null
    fun show(context: Context?, text: String?) {
        try {
            if (toast != null) {
                toast!!.setText(text)
            } else {
                toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
            }
            toast!!.show()
        } catch (e: Exception) {
            //Solve the exception handling of calling Toast in the child thread
            Looper.prepare()
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            Looper.loop()
        }
    }
}