package com.example.pruebamosters.ui.home.dialogo


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamosters.R
import com.example.pruebamosters.data.local.Monster

class AdapterDialogoArchetype(var monster: List<Monster?>, var dialgoFilterArchetype: DialgoFilterArchetype) :
    RecyclerView.Adapter<ViewHolderDialogoArchetype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDialogoArchetype {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderDialogoArchetype(
            layoutInflater.inflate(
                R.layout.view_archetype,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderDialogoArchetype, position: Int) {
        val item = monster[position]
        if (item != null) {
            holder.render(item, dialgoFilterArchetype)
        }
    }

    override fun getItemCount(): Int {
        return monster.size
    }
}
