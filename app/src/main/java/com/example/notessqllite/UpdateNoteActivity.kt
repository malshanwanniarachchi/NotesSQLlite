package com.example.notessqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqllite.databinding.ActivityUpdateNoteBinding

// Activity for updating a note
class UpdateNoteActivity : AppCompatActivity() {

    // View binding variable
    private lateinit var binding: ActivityUpdateNoteBinding
    // Database helper variable
    private lateinit var db: NotesDatabaseHelper
    // ID of the note being updated
    private var noteId: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using view binding
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database helper
        db = NotesDatabaseHelper(this)

        // Get note ID passed from the intent
        noteId = intent.getIntExtra("note_id",-1)

        // If note ID is invalid, finish the activity
        if (noteId == -1){
            finish()
            return
        }

        // Get the note from the database using its ID and populate the EditText fields with its data
        val note = db.getNoteById(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        // Set click listener for the save button
        binding.updateSaveButton.setOnClickListener {
            // Get updated title and content from EditText fields
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            // Create updated note object
            val updatedNote = Note(noteId,newTitle,newContent)



            // Update the note in the database
            db.updateNote(updatedNote)

            // Finish the activity and show toast message
            finish()
            Toast.makeText(this,"changes saved",Toast.LENGTH_SHORT).show()

        }

    }
}