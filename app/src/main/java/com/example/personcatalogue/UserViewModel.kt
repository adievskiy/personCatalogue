package com.example.personcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val persons = MutableLiveData<List<User>>()
    val person: LiveData<List<User>> get() = persons

    init {
        persons.value = emptyList()
    }

    fun addUser(user: User) {
        val updatedList = persons.value?.toMutableList() ?: mutableListOf()
        updatedList.add(user)
        persons.value = updatedList
    }
}

data class User(private val name: String, private val age: Int) {
    override fun toString(): String {
        return "Имя $name, возраст $age"
    }
}