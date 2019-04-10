package com.android.sharedprefkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var etLastName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etLastName = findViewById(R.id.etLastName)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveData()
        }

        fetchData()
    }

    private fun saveData(){

        if (etName.text.isEmpty()){
            etName.error = "Please Enter First Name"
            return
        }
        if (etLastName.text.isEmpty()){
            etLastName.error = "Please Enter Last Name"
            return
        }

        val myPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val editor = myPref.edit()

        editor.putString("First_Name", etName.text.toString())
        editor.putString("Last_Name", etLastName.text.toString())

        editor.apply()
        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()

        etName.setText("")
        etLastName.setText("")
    }

    private fun fetchData(){
        val savePref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val fname = savePref.getString("First_Name", "")
        val lname = savePref.getString("Last_Name", "")

        etName.setText(fname)
        etLastName.setText(lname)

    }
}
