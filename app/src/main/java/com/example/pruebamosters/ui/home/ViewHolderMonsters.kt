package com.example.pruebamosters.ui.home

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamosters.data.local.Monster
import com.example.pruebamosters.databinding.CardviewMonsterBinding

class ViewHolderMonsters(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = CardviewMonsterBinding.bind(view)
    private lateinit var monster: Monster
    private lateinit var activity: Activity

    fun render(items: Monster, activity: Activity) {
        this.monster = items
        this.activity = activity

        Glide.with(activity).load(monster.card_images?.get(0)?.image_url_small)
            .into(binding.imagenViewMonster)

        binding.textViewNameMonster.text = monster.name

        if (monster.banlist_info?.ban_ocg.equals("Limited") || monster.banlist_info?.ban_tcg.equals(
                "Limited"
            )
        ) {
            binding.linearLayoutLimited.visibility = View.VISIBLE
            binding.textViewLimited.text = String.format("%s", "Limited")
        }
        if (monster.level.toString() == "null") {
            binding.textViewLevel.text = String.format("Level: %s", "0")
        } else {
            binding.textViewLevel.text = String.format("Level: %s", monster.level.toString())
        }



        binding.carViewMonster.setOnClickListener {
            try {
                var screen = activity as HomeActivity
                activity.saveMonster(monster)
            } catch (e: Exception) {
                Toast.makeText(activity, "Selecciona otra vez", Toast.LENGTH_SHORT).show()
            }
        }

    }

}