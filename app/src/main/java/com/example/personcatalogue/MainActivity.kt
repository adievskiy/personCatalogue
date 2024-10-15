package com.example.personcatalogue

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var personNameET: EditText
    private lateinit var personAgeET: EditText
    private lateinit var savePersonBTN: Button
    private lateinit var personListLV: ListView
    private val persons: MutableList<User> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Каталог пользователей"

        personNameET = findViewById(R.id.personNameET)
        personAgeET = findViewById(R.id.personAgeET)
        savePersonBTN = findViewById(R.id.savePersonBTN)

        personListLV = findViewById(R.id.personListLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, persons)
        personListLV.adapter = adapter

        savePersonBTN.setOnClickListener {
            if (personNameET.text.isEmpty() || personAgeET.text.isEmpty()) {
                Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val person = User(personNameET.text.toString(), personAgeET.text.toString().toInt())
            persons.add(person)
            adapter.notifyDataSetChanged()
            personNameET.text.clear()
            personAgeET.text.clear()
        }

        personListLV.onItemClickListener =
            MyDialog.createDialog(this, adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menumain, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenu -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

data class User(private val name: String, private val age: Int) {
    override fun toString(): String {
        return "Имя $name, возраст $age"
    }
}