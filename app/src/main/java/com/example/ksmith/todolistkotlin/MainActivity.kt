package com.example.ksmith.todolistkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.LayoutManager
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var adapter: ToDoAdapter
    private lateinit var layoutManager: LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ToDoAdapter()
        layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = layoutManager

        recycler_view.setHasFixedSize(true)

        add_item_button.setOnClickListener({ _ ->
            Toast.makeText(this, enter_string_edit_text.text.toString(), Toast.LENGTH_LONG).show()
            enterItemClicked()
        })

        sort_id_button.setOnClickListener({ _ ->
            adapter.sortById()
        })

        sort_item_button.setOnClickListener({ _ ->
            adapter.sortByItem()
        })

        log_button.setOnClickListener({ _ ->
            logListClicked()
        })
    }

    private fun logListClicked() {
        Log.i("List: ", adapter.data.toString())
    }


    fun enterItemClicked() {
        if (!enter_string_edit_text.text.toString().isEmpty()){
            val itemString = enter_string_edit_text.text.toString()
            val todoItem = ToDoItem(id, itemString)

            adapter.addItem(todoItem)
            id++
            enter_string_edit_text.setText("")
        }
    }
}
