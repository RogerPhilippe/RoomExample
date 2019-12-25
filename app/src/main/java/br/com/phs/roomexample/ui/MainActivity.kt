package br.com.phs.roomexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.com.phs.roomexample.R
import br.com.phs.roomexample.data.AppDatabase
import br.com.phs.roomexample.data.User
import br.com.phs.roomexample.data.UserDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var adapter: UserAdapter
    private lateinit var users: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        users = mutableListOf()
        adapter = UserAdapter(users, this)
        namesRecyclerView.layoutManager = LinearLayoutManager(this)
        namesRecyclerView.adapter = adapter

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "kroom.db")
            .build()
        userDao = db.userDao()

        loadItems()

        addBtn.setOnClickListener { buttonClickEvent() }

    }

    private fun buttonClickEvent() {
        if (nameTextView.text.isNotEmpty() && lastNameTextView.text.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                userDao.insert(User(
                    0, nameTextView.text.toString(), lastNameTextView.text.toString()
                ))
            }
            GlobalScope.launch(Dispatchers.Main) { loadItems() }
        }
    }

    private fun loadItems() {
        userDao.getAll().observe(this, Observer {
            users.clear()
            users.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

}
