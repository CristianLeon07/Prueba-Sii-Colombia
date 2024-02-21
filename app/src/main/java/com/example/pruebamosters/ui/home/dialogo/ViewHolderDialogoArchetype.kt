package com.example.pruebamosters.ui.home.dialogo

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamosters.data.local.Monster
import com.example.pruebamosters.databinding.ViewArchetypeBinding
import com.example.pruebamosters.ui.home.HomeActivity

class ViewHolderDialogoArchetype(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ViewArchetypeBinding.bind(view)
    private lateinit var monster: Monster

    fun render(items: Monster, activity: DialgoFilterArchetype) {
        this.monster = items

        if (monster.archetype != "") {
            binding.textVieNameArchetype.text = monster.archetype
        }
        binding.itemArchetype.setOnClickListener {
            try {
                var screen = activity as DialgoFilterArchetype
                activity.saveFilterArchetype(monster)
            } catch (e: Exception) {
                Log.e("adapter emojis", e.message ?: "")
            }
        }


    }

}