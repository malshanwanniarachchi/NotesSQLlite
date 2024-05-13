package com.example.notessqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.notessqllite.databinding.ActivityAddNoteBinding
import com.example.notessqllite.databinding.ActivityMainBinding


class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflating layout using view binding
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing database helper
        db = NotesDatabaseHelper(this)

        // Setting click listener for save button
        binding.saveButton.setOnClickListener{
            // Getting title and content from EditText fields
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            // Validating title field
            if(title.isEmpty()){
                binding.titleEditText.error = "Title content be empty"
                return@setOnClickListener
            }
            // Validating content field
            if (content.isEmpty()){
                binding.contentEditText.error  = "Content can not be empty"
                return@setOnClickListener
            }

            // Creating Note object with entered data
            val note = Note(0,title,content)
            // Inserting note into database
            db.insertNote(note)
            // Finishing the activity
            finish()
            // Showing toast message to indicate note saved
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show()
        }


    }
}