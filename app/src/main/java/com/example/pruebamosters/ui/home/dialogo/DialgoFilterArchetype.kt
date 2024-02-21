package com.example.pruebamosters.ui.home.dialogo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebamosters.R
import com.example.pruebamosters.data.local.Monster
import com.example.pruebamosters.databinding.DialogoFilterArchetypeBinding
import com.example.pruebamosters.ui.home.HomeActivity

class DialgoFilterArchetype(activity: HomeActivity, monsters: List<Monster?>) : DialogFragment() {
    private var binding: DialogoFilterArchetypeBinding? = null
    private var activity = activity
    private lateinit var adapterDialogoArchetype: AdapterDialogoArchetype
    private var monstersLocal: List<Monster?> = monsters
    private var filterArchetype: List<Monster?> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogoFilterArchetypeBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.borders_tarjetas_trasparente)


        initUI()
        initEvent()

        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    private fun initUI() {

        binding?.recyclerViewFilterArchetype?.setHasFixedSize(true)
        binding?.recyclerViewFilterArchetype?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        try {
            adapterDialogoArchetype =
                AdapterDialogoArchetype(monstersLocal, this)
            binding?.recyclerViewFilterArchetype?.adapter = adapterDialogoArchetype
        } catch (e: Exception) {
            Log.i("ERRORR", "" + e)
        }
        showArchetypeMonster(monstersLocal)

    }


    private fun initEvent() {
        binding?.btnCancelDialogo?.setOnClickListener { dismiss() }
        binding?.searchViewArqueotipo?.setOnClickListener {
            binding?.searchViewArqueotipo?.isIconified = false
            binding?.searchViewArqueotipo?.requestFocusFromTouch()
        }

        binding?.searchViewArqueotipo?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: kotlin.String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: kotlin.String): Boolean {
                filterArchetype(newText)
                return true
            }
        })

    }

    private fun showArchetypeMonster(archetype: List<Monster?>) {
        monstersLocal.isEmpty()
        if (!archetype.isNullOrEmpty()) {
            monstersLocal = archetype.distinctBy { it?.archetype }

        }
        adapterDialogoArchetype.monster = monstersLocal ?: emptyList()
        adapterDialogoArchetype.notifyDataSetChanged()


    }

    fun saveFilterArchetype(archetype: Monster) {
        activity.saveFilterArchetype(archetype)
        dismiss()
    }

    private fun filterArchetype(query: String) {
        filterArchetype = if (query.isBlank()) {
            monstersLocal
        } else {
            monstersLocal.filter { it?.name?.toLowerCase()?.contains(query.toLowerCase()) == true }
        }
        updateArchetypeList()
    }

    private fun updateArchetypeList() {

        adapterDialogoArchetype.monster = filterArchetype
        adapterDialogoArchetype.notifyDataSetChanged()

    }


}