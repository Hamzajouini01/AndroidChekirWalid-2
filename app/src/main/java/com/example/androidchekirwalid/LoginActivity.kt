package com.example.androidchekirwalid

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.util.Patterns.EMAIL_ADDRESS


const val PREF_NAME = "LOGIN_PREF_LOL"
const val FULLNAME = "FULLNAME"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBRED = "IS_REMEMBRED"

class LoginActivity : AppCompatActivity() {

    lateinit var txtFullName: TextInputEditText
    lateinit var txtLayoutFullName: TextInputLayout

    lateinit var txtEmail: TextInputEditText
    lateinit var txtLayoutEmail: TextInputLayout

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout

    lateinit var cbRememberMe: CheckBox
    lateinit var btnLogin: MaterialButton

    lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        txtFullName = findViewById(R.id.txtFullName)
        txtLayoutFullName = findViewById(R.id.txtLayoutFullName)

        txtEmail = findViewById(R.id.txtEmail)
        txtLayoutEmail = findViewById(R.id.txtLayoutEmail)

        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)

        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)

        //TODO 2 "Initialize the var of SharedPreferences"
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //TODO 3 "Test in the SharedPreferences if there's data"
        if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
            navigate()
        }

        btnLogin.setOnClickListener{
            doLogin()
        }

    }

    private fun doLogin(){
        if (validate()){
            if (cbRememberMe.isChecked){
                //TODO 4 "Edit the SharedPreferences by putting all the data"
                mSharedPref.edit().apply{
                    putBoolean(IS_REMEMBRED, true)
                    putString(FULLNAME, txtFullName.text.toString())
                    putString(EMAIL, txtEmail.text.toString())
                    putString(PASSWORD, txtPassword.text.toString())
                }.apply()

            }else{
                mSharedPref.edit().clear().apply()
            }

            navigate()
        }
    }

    private fun validate(): Boolean {
        txtFullName.error = null
        txtLayoutEmail.error = null
        txtLayoutPassword.error = null

        if (txtFullName.text!!.isEmpty()){
            txtLayoutFullName.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if(txtEmail?.text!!.isEmpty()){
            txtLayoutEmail!!.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if(!EMAIL_ADDRESS.matcher(txtEmail?.text!!).matches()){
            txtLayoutEmail!!.error = getString(R.string.checkYourEmail)
            return false
        }

        if (txtPassword.text!!.isEmpty()){
            txtLayoutPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }

    private fun navigate(){
        val mainIntent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(mainIntent)
    }
}