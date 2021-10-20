package com.example.sqlite_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite_database.sqlite.Memo
import com.example.sqlite_database.sqlite.RecyclerAdapter
import com.example.sqlite_database.sqlite.SqliteHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = SqliteHelper(this, DB_NAME, DB_VERSION)
        val adapter = RecyclerAdapter()

        val memo = helper.selectMemo()
        adapter.listDate.addAll(memo)

        recyclerMemo.adapter = adapter
        recyclerMemo.layoutManager = LinearLayoutManager(this)

        buttonSave.setOnClickListener{
            val content = editMemo.text.toString()
            if(content.isNotEmpty()) {
                val memo = Memo(null, content, System.currentTimeMillis())
                helper.insertMemo(memo)
                adapter.listDate.clear()
                adapter.listDate.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
            }
        }

    }
}