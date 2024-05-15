package com.example.notessqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqllite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declaring variables
    private lateinit var binding: ActivityMainBinding // View binding variable
    private lateinit var db: NotesDatabaseHelper  // Database helper variable
    private lateinit var notesAdapter: NotesAdapter  // Adapter for RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflating layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing database helper
        db = NotesDatabaseHelper(this)

        // Initializing adapter with all notes from database
        notesAdapter = NotesAdapter(db.getAllNotes(),this)

        // Setting layout manager and adapter for RecyclerView
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        // Setting click listener for add button to navigate to AddNoteActivity
        binding.addButton.setOnClickListener{
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onResume() {
        super.onResume()
        // Refreshing data in adapter when activity is resumed
        notesAdapter.refreshData(db.getAllNotes())
    }
}