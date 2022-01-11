package com.example.navigationview

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.example.navigationview.models.User
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        updateNavMenu(navView, intent.getSerializableExtra("user") as User)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext, "Click Home", Toast.LENGTH_SHORT).show()
                R.id.nav_exit -> goToMainActivity()
            }
            true
        }
    }

    fun goToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun updateNavMenu(nav: NavigationView, userData: User){
        val viewHeader = nav.getHeaderView(0)
        val imgView = viewHeader.findViewById<ImageView>(R.id.user_img)

        viewHeader.findViewById<TextView>(R.id.user_text_name).text = userData.name
        Picasso.get().load(userData.img).into(imgView)

        if(userData.role.equals("user")) {
            nav.menu.findItem(R.id.nav_peoples).isVisible = false
            viewHeader.setBackgroundColor(Color.parseColor("#7DCEA0"))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}