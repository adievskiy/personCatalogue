package com.example.personcatalogue

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MyDialog {
    companion object {
        fun createDialog(context: Context, adapter: ArrayAdapter<User>) =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val item = adapter.getItem(position)
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Внимание!")
                    .setMessage("Вы действительно хотите удалить $item?")
                    .setCancelable(true)
                    .setPositiveButton("Да") { dialog, which ->
                        adapter.remove(item)
                        Toast.makeText(context, "$item удален из списка", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("Нет") { dialog, which ->
                        dialog.cancel()
                    }.create()
                builder.show()
            }
    }

}