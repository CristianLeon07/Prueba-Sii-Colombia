package com.example.pruebamosters.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamosters.data.local.Monster
import com.example.pruebamosters.databinding.CardviewBannerMonsterBinding
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ViewHolderCarouselIamgen(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = CardviewBannerMonsterBinding.bind(view)
    private lateinit var monster: Monster
    private var listImgMonster: MutableList<CarouselItem> = mutableListOf()

    fun render(items: List<String>) {
        try {
            listImgMonster.clear()
            items.map {
                listImgMonster.add(CarouselItem(it))
            }
            binding.caruselImgMonster.addData(listImgMonster)
        } catch (e: Exception) {

        }


    }

}