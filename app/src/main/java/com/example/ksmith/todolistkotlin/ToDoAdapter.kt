package com.example.ksmith.todolistkotlin

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.my_text_view.view.*
import kotlin.collections.ArrayList

/**
 * Created by ksmith on 21/03/2018.
 */

class ToDoAdapter : Adapter<ToDoAdapter.MyViewHolder>() {

    var data = kotlin.collections.ArrayList<ToDoItem>()

    fun updatedWith(updatedList: List<ToDoItem>) {
        this.data.clear()
        this.data.addAll(updatedList)
        Log.i("Adapter", "Updated list ${data.toString()}")
        notifyDataSetChanged()
    }

    fun addItem(item: ToDoItem) {
        val newList = ArrayList(data)
        newList.add(item)
        Log.i("Adapter", "AddedItem: ${item.item} to $newList")

        updatedWith(newList)
    }


    fun deleteAt(position: Int) {
        val newList = ArrayList(data)
        newList.removeAt(position)
        updatedWith(newList)
    }

    fun sortById() {
        val newList = ArrayList<ToDoItem>(data)

        updatedWith(newList.sortedWith(compareBy({ it.id })))
    }

    fun sortByItem() {
        val newList = ArrayList(data)

        updatedWith(newList.sortedWith(compareBy({ it.item })))
    }

    override fun onBindViewHolder(holder: ToDoAdapter.MyViewHolder, position: Int) {
        holder.bindToDoItem(data[position])

        holder.itemView.delete_button.setOnClickListener({ view ->
            deleteAt(position)
            Toast.makeText(view.context, "Removed at $position", Toast.LENGTH_LONG).show()
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.my_text_view, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(viewType: View?) : RecyclerView.ViewHolder(viewType) {
        fun bindToDoItem(toDoItem: ToDoItem) {
            itemView.id_text_view.text = toDoItem.id.toString()
            itemView.item_text_view.text = toDoItem.item

            itemView.is_complete_toggle.setOnClickListener({ view ->
                val itemString: String = itemView.item_text_view.text.toString()

                toDoItem.isComplete = !toDoItem.isComplete

                val toastMessage = if (toDoItem.isComplete) "Item Complete" else "Item incomplete"

                Toast.makeText(view.context, toastMessage, Toast.LENGTH_LONG).show()
            })
        }
    }
}
