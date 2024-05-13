package com.example.notessqllite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// Adapter class for managing notes RecyclerView
class NotesAdapter(private var notes: List<Note>,context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    // Database helper instance
  private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)


    // ViewHolder class to hold views for each item in RecyclerView
    class NoteViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        // Views in the note item layout
        val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView : TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton : ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton : ImageView = itemView.findViewById(R.id.deleteButton)
    }

    // Create ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Inflate layout for note item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    // Return number of items in RecyclerView
    override fun getItemCount(): Int = notes.size

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Set click listener for update button
        holder.updateButton.setOnClickListener{
            // Launch UpdateNoteActivity with note ID as extra
            val intent = Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for delete button
        holder.deleteButton.setOnClickListener {
            // Delete note from database, refresh data in adapter, and show toast
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted",Toast.LENGTH_SHORT).show()

        }

    }

    // Refresh adapter data with new list of notes
    fun refreshData(newNote: List<Note>){
        notes = newNote
        notifyDataSetChanged()
    }

}