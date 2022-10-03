package com.example.project01.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project01.BaseFragment.BaseFragment
import com.example.project01.R
import com.example.project01.data.ContactDatabase
import kotlinx.android.synthetic.main.profile_fragment.view.*
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.editBtn.setOnClickListener{
            fragmentChange(UpdateFragment())
        }

        launch{
            context?.let {
                val contact = ContactDatabase(it).contactDao().getContact()
                view.name_textView.setText(contact[0].name)
                view.email_textView.setText(contact[0].email)
                view.phoneNumber_textView.setText(contact[0].phone)
            }
        }
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
       val view = inflater.inflate(R.layout.profile_fragment, container, false)
        return view
    }


}