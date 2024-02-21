package com.example.pruebamosters.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamosters.R

class AdapterCarouselImagen(var monsterImg: List<String?>) :
    RecyclerView.Adapter<ViewHolderCarouselIamgen>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCarouselIamgen {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderCarouselIamgen(
            layoutInflater.inflate(
                R.layout.cardview_banner_monster,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderCarouselIamgen, position: Int) {
        Log.i("Lista", monsterImg.toString())
        val listaMosters = monsterImg.filterNotNull().filter { it.isNotEmpty() }
        Log.i("ListaMosters", listaMosters.toString())
        monsterImg = listaMosters
        holder.render(listaMosters)
    }

    override fun getItemCount(): Int {

        return monsterImg.size
    }

}
