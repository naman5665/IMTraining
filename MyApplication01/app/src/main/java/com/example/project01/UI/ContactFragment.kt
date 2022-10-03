package com.example.project01.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project01.Adapter.PersonAdapter
import com.example.project01.R
import com.example.project01.UserDataRecieve
import com.example.project01.dataJson
import kotlinx.android.synthetic.main.contact_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactFragment : Fragment() {

    lateinit var adapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_fragment, container, false)
    }

    private fun getUserData() {
        val user = UserDataRecieve.dataInstance.getPersonsData(1)
        user.enqueue(object: Callback<dataJson> {
            override fun onFailure(call: Call<dataJson>, t: Throwable) {
                Log.d("Project01" , "Error in fetching user's data")
            }

            override fun onResponse(call: Call<dataJson>, response: Response<dataJson>) {
                val dataJ = response.body()
                if(dataJ != null){
                    Log.d("Project01" , dataJ.toString())
                    adapter = PersonAdapter(requireActivity(),dataJ.users)
                    item_list_call.adapter = adapter
                    item_list_call.layoutManager = LinearLayoutManager(context)
                }
            }
        })
    }

}