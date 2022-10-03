package com.example.project01.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.project01.R
import com.example.project01.UI.ProfileFragment
import com.example.project01.UI.ContactFragment
import com.example.project01.UI.LoginFragment
import com.example.project01.UI.UpdateFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var button: Button
    lateinit var txt: TextView
    lateinit var loginbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginbtn = findViewById(R.id.login_button_activity_1)
        loginbtn.setOnClickListener {
            changeFragment(LoginFragment())
        }


        txt = findViewById(R.id.textView)
        button = findViewById(R.id.button_activity1)
        button.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

        setupViews()
        val menuDrawer = findViewById<NavigationView>(R.id.menu_drawer)
        menuDrawer.setNavigationItemSelectedListener(this)

    }

    fun setupViews(){
        setUpDrawerLayout()
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.appBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(this,findViewById(R.id.mainDrawer),
            R.string.app_name, R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val mainDrawer = findViewById<DrawerLayout>(R.id.mainDrawer)
        mainDrawer.closeDrawer(GravityCompat.START)

        when(item.itemId){
            R.id.frag_1 ->{
                changeFragment(ProfileFragment())
            }
            R.id.frag_2 ->{
                val snackBar = Snackbar.make(findViewById(R.id.frag_2),"Tap on Phone to Call", Snackbar.LENGTH_SHORT)
                snackBar.show()
                changeFragment(ContactFragment())
            }
            R.id.frag_3 ->{
                changeFragment(LoginFragment())
            }
            R.id.frag_4 ->{
                changeFragment(UpdateFragment())
            }
        }

        return true
    }

    fun changeFragment(frag: Fragment){

        button.isVisible = false
        txt.isVisible = false
        loginbtn.isVisible = false
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }
}