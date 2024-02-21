package com.example.pruebamosters.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebamosters.R
import com.example.pruebamosters.data.local.Global.Companion.monsterSelect
import com.example.pruebamosters.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // region declaracion de variables
    /**
     * Variable para instanciar la View usando bindig
     */
    private lateinit var binding: ActivityDetailBinding

    /**
     * Variable para instanciar el adaptador
     */
    private lateinit var adapterCarouselImagen: AdapterCarouselImagen

    /**
     * Variable para instanciar la lista de imagenes.
     */
    private lateinit var imageMonsters: List<String?>

    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initEvent()
    }

    // region funcion para inicializar llenar elementos visuales con lo que esta guardadon en la variable global.

    private fun initUi() {
        try {

            converterListString()

            binding.recylcerViewImgMonster.setHasFixedSize(true)
            binding.recylcerViewImgMonster.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false
            )
            adapterCarouselImagen =
                AdapterCarouselImagen(imageMonsters)
            binding.recylcerViewImgMonster.adapter = adapterCarouselImagen

        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
        if (monsterSelect?.def == null) {
            binding.texteViewdefending.text = String.format(" %s", "0")
        } else {
            binding.texteViewdefending.text = monsterSelect?.def.toString()
        }
        if (monsterSelect?.atk == null) {
            binding.texteViewStroke.text = String.format(" %s", "0")
        } else {
            binding.texteViewStroke.text = monsterSelect?.atk.toString()
        }

        if (monsterSelect?.level.toString() == "null") {
            binding.textViewLevel.text = String.format("Level: %s", "0")
        } else {
            binding.textViewLevel.text = String.format("Level: %s", monsterSelect?.level.toString())
        }
        if (monsterSelect?.banlist_info?.ban_ocg.equals("Limited") || monsterSelect?.banlist_info?.ban_tcg.equals(
                "Limited"
            )
        ) {
            binding.linearLayoutLimited.visibility = View.VISIBLE
            binding.textViewLimited.text = String.format("%s", "Carta Limitada")
        }
        binding.textViewId.text = monsterSelect?.id.toString()
        binding.textViewName.text = monsterSelect?.name
        binding.textViewRace.text = monsterSelect?.race
        if (monsterSelect?.archetype == "" || monsterSelect?.archetype == null) {
            binding.texteViewArchetype.text = String.format("No designado")
        } else {
            binding.texteViewArchetype.text = monsterSelect?.archetype
        }
        binding.textViewDescription.text = monsterSelect?.desc


        when (monsterSelect?.archetype) {
            "Alien" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_aliens)

            }
            "Infernoble Arms" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_infernoble)

            }
            "Noble Knight" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_noble)

            }
            "Melodious" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_melodious)

            }
            "Crystron" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_crystron)

            }
            "Destiny HERO" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_destiny)

            }
            "Six Samurai" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_samurai)

            }
            "" -> {
                binding.linearLayoutContainerInfo.setBackgroundResource(R.drawable.fondo_default)
            }

        }

    }

    // endregion

    // region funcion de eventos boton regresar.

    private fun initEvent() {
        binding.btnBack.setOnClickListener { onBackPressed() }

    }

    // endregion

    // region funcion para convertir la lista de imagenes en una lista de String, para poder mostrar todas las imagenes
    private fun converterListString() {
        monsterSelect?.card_images?.map {
            val imgSmall = it.image_url_small
            val img = it.image_url
            val imgCroppe = it.image_url_cropped

            imageMonsters = listOf(imgSmall, img, imgCroppe)

            Log.i("Imagenes", imageMonsters.toString())
        }
    }
    //endregion
}