package com.example.ksmith.todolistkotlin

/**
 * Created by ksmith on 21/03/2018.
 */
data class ToDoItem(val id: Int, val item: String, var isComplete: Boolean = false){
}