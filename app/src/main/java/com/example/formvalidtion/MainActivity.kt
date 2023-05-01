package com.example.formvalidtion

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.accessibility.AccessibilityEventCompat.ContentChangeType
import com.example.formvalidtion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding:ActivityMainBinding
    private lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val etName = binding.etName.text.toString()
            val etEmail = binding.etEmail.text.toString()
            val etPassword = binding.etPassword.text.toString()

            sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("name",etName)
            editor.putString("email",etEmail)
            editor.putString("password",etPassword)
            editor.apply()

            val intent  = Intent(this,SecondActivity::class.java)
//            intent.putExtra("name",etName)
//            intent.putExtra("email",etEmail)
//            intent.putExtra("password",etPassword)
            startActivity(intent)
        }

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = s.toString().trim()
                val nameRegex = "[a-zA-Z]+"
                if (name.isEmpty()){
                    binding.etName.error = "Name is Required"
                }else if (!name.matches(nameRegex.toRegex())){
                    binding.etName.error  = "Invalid name format"
                }else{
                    binding.etName.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()
                val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"
                if (email.isEmpty()){
                    binding.etEmail.error = "Email is required"
                }else if (!email.matches(emailRegex.toRegex())){
                    binding.etEmail.error = "Enter valid email"
                }else{
                    binding.etEmail.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                val passwordRegex = "/^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@\$!%?&])[A-Za-z\\d@\$!%?&\$/]{13,}/"
                if(password.isEmpty()){
                    binding.etPassword.error = "pls Enter your password"
                }else if (!password.matches(passwordRegex.toRegex())){
                    binding.etPassword.error = "Password must contain  8 characters,one digit , one lowercase,one upper case letter ,one special character"
                }else{
                    binding.etPassword.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val confirmPassword = s.toString()
                val password = binding.etPassword.text.toString()
                if (confirmPassword.isEmpty()){
                    binding.etConfirmPassword.error = "confirm your password"
                }else if(confirmPassword !=password){
                    binding.etConfirmPassword.error = "Password do not match"
                }else{
                    binding.etConfirmPassword.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}