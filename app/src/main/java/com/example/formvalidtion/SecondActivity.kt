package com.example.formvalidtion

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formvalidtion.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySecondBinding
    private lateinit var secondSharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        secondSharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE)
        val name = secondSharedPref.getString("name","")
        val email = secondSharedPref.getString("email","")
        val password = secondSharedPref.getString("password","")


        binding.tvName.text = name
        binding.tvEmail.text = email
        binding.tvPassword.text = password
        //if data is not found in sharedPreferences get it from intent
        /*if (name.isNullOrEmpty() || email.isNullOrEmpty() ||password.isNullOrEmpty()){
            binding.tvName.text = intent.getStringExtra("name")
            binding.tvEmail.text = intent.getStringExtra("email")
            binding.tvPassword.text = intent.getStringExtra("password")
        }else{
            binding.tvName.text = name
            binding.tvEmail.text = email
            binding.tvPassword.text = password

        }*/
    }
}