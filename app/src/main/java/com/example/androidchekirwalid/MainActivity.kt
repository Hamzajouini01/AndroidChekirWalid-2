package com.example.androidchekirwalid

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.androidchekirwalid.utils.AppDataBase

class MainActivity : AppCompatActivity() {

    private lateinit var btnShop: Button
    private lateinit var btnProfile: Button
    private lateinit var btnFavorite: Button
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.app_barCar)
        setSupportActionBar(toolbar)

        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnShop = findViewById(R.id.btnShop)
        btnShop!!.setOnClickListener {
            changeFragment(ShopFragment(),"")
        }

        btnFavorite = findViewById(R.id.btnHistory)
        btnFavorite!!.setOnClickListener {
            changeFragment(HistoryFragment(),"")
        }

        btnProfile = findViewById(R.id.btnProfile)
        btnProfile!!.setOnClickListener {
            val profileMeFragment = ProfileFragment.newInstance(
                mSharedPref.getString(FULLNAME, "").toString(),
                mSharedPref.getString(EMAIL, "").toString()
            )
            changeFragment(profileMeFragment,"")
        }

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,ShopFragment()).commit()
    }

    private fun changeFragment(fragment: Fragment, name: String) {

        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.logoutMenu -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("Are you sure to logout?")
                builder.setPositiveButton("Yes"){ dialogInterface, which ->
                    getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                    AppDataBase.getDatabase(this).bookDao().deleteAll()
                    finish()
                }
                builder.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                builder.create().show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}