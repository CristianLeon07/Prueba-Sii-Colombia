package com.example.pruebamosters.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebamosters.data.external.APIConecction
import com.example.pruebamosters.data.external.ApiService
import com.example.pruebamosters.data.external.endpoint
import com.example.pruebamosters.data.local.Global
import com.example.pruebamosters.data.local.Monster
import com.example.pruebamosters.databinding.ActivityHomeBinding
import com.example.pruebamosters.ui.Router
import com.example.pruebamosters.ui.home.dialogo.DialgoFilterArchetype
import com.example.pruebamosters.ui.screenChange
import com.example.pruebamosters.utility.Utility
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    // region declaracion de variables
    /**
     * Variable para instanciar la View usando bindig
     */
    private lateinit var binding: ActivityHomeBinding

    /**
     * Variable para declarar el adaptador
     */
    private lateinit var adapterMonster: AdapterMonsters

    /**
     * Variable para declarar la lista principal
     */
    private var monsters: List<Monster> = emptyList()

    /**
     * Variable para declarar la lista para hacer el filtro en busqueda
     */
    private var filterMonsters: List<Monster> = emptyList()

    /**
     * Variable para declarar la lista de monstruos quevan a filtrar los banList
     */
    private var monstersBanList: List<Monster> = emptyList()

    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initEvent()


    }

    // region funcion donde se inicializa los elementos visualez, y donde se llama la consulta de la corrutina.

    private fun initUi() {
        callMonsterServer()
        binding.recycleViewMonsters.setHasFixedSize(true)
        binding.recycleViewMonsters.layoutManager =
            GridLayoutManager(
                this,
                2
            )
        adapterMonster =
            AdapterMonsters(monsters, this)
        binding.recycleViewMonsters.adapter = adapterMonster

    }
    // endregion

    // region funcion de eventos, buscador, boton de regresar y levantar dialogo

    private fun initEvent() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
            binding.searchView.requestFocusFromTouch()
        }

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: kotlin.String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: kotlin.String): Boolean {
                filterMonsters(newText)
                return true
            }
        })

        binding.buttonSearchArchetypes.setOnClickListener {
            DialgoFilterArchetype(this, monsters).show(
                supportFragmentManager,
                "CustomDialogFragment"
            )
        }


    }

    // endregion

    // region funcion consulta al servidor
    private fun callMonsterServer() {

        var dialogo = Utility().ShowLoadingDialog(this) as AlertDialog
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val call = APIConecction().getData().create(ApiService::class.java)
                    .postApi(
                        endpoint().ENDPOINT_INFO_MONSTER
                    )
                Log.i("infoMonster", call?.body().toString())
                runOnUiThread {
                    Utility().CloseLoadingDialog(dialogo)
                    if (call?.isSuccessful == true) {
                        Log.i("infoMo", call?.body()?.data.toString())
                        monsterChange(call.body()?.data)

                    } else {

                        Log.i("error categories", call?.message().toString() ?: "")

                    }

                }
            } catch (e: Exception) {

                Log.e("error", e.message ?: "")
                runOnUiThread {
                    Utility().CloseLoadingDialog(dialogo)
                }

            }
        }
    }
    // endregion

    // region funcion que recibe la respuesta correcta del servidor, y se procede a dezerealizar los datos para llenar la lista

    private fun monsterChange(data: JsonArray?) {
        if (data != null) {
            monsters = Gson().fromJson(data, Array<Monster>::class.java).toList()

            monsters.map {
                if (it.banlist_info?.ban_ocg.equals("Banned") || it.banlist_info?.ban_ocg.equals(
                        "Banned"
                    )
                ) {
                    monstersBanList = monsters.filterNot {
                        it.banlist_info?.ban_ocg.equals(
                            "Banned",
                            ignoreCase = true
                        )
                    }
                    monsters = monstersBanList

                }
            }

            adapterMonster.monster = monsters ?: emptyList()
            adapterMonster.notifyDataSetChanged()
        }

    }
    // endregion

    //region funcion para guardar los datos de la tarjeta seleccionada y que me lleva a la vista del detalle

    fun saveMonster(monster: Monster) {
        Global.monsterSelect = monster
        Router().goToScreens(screenChange.DETAIL, this, false)

    }
    // endregion

    // region funcion para realizar el filtrado con el buscador
    private fun filterMonsters(query: String) {
        filterMonsters = if (query.isBlank()) {
            monsters
        } else {
            monsters.filter { it.name?.toLowerCase()?.contains(query.toLowerCase()) == true }
        }
        updateMonsterList()
    }
    // endregion

    //region funcion para notificar cambion en la lista a medida que se este buscando
    private fun updateMonsterList() {
        if (filterMonsters.isEmpty()) {
            binding.contenedorError.visibility = View.VISIBLE
            binding.recycleViewMonsters.visibility = View.GONE
        } else {
            binding.contenedorError.visibility = View.GONE
            binding.recycleViewMonsters.visibility = View.VISIBLE
        }
        adapterMonster.monster = filterMonsters
        adapterMonster.notifyDataSetChanged()
    }

    // endregion

    // region funcion para filtrar por arquetipos, se llama en el dialgo

    fun saveFilterArchetype(archetype: Monster) {
        filterMonsters.isEmpty()
        binding.nameArchetypeSelect.text = String.format("Arqueotipo: %s", archetype.archetype)
        filterMonsters = monsters.filter {
            it.archetype?.equals(archetype.archetype ?: "") == true
        }
        adapterMonster.monster = filterMonsters
        adapterMonster.notifyDataSetChanged()

    }
    // endregion

}