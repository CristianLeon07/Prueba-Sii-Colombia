package com.example.pruebamosters.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamosters.R
import com.example.pruebamosters.data.local.Monster

class AdapterMonsters(var monster: List<Monster?>, var homeActivity: Activity) :
    RecyclerView.Adapter<ViewHolderMonsters>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMonsters {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderMonsters(
            layoutInflater.inflate(
                R.layout.cardview_monster,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderMonsters, position: Int) {
        val item = monster[position]
        if (item != null) {
            holder.render(item, homeActivity)
        }
    }

    override fun getItemCount(): Int {
        return monster.size
    }

}
