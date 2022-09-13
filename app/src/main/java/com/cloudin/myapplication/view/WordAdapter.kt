package com.cloudin.myapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudin.myapplication.R
import com.cloudin.myapplication.model.Meaning

class WordAdapter() : RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private lateinit var context: Context
    var list : List<Meaning> = ArrayList()

    constructor(context: Context, list :List<Meaning>) : this() {
        this.context = context
        this.list = list
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvType: TextView = itemView.findViewById(R.id.tv_type)
        var rvDefinitions: RecyclerView = itemView.findViewById(R.id.rv_definitions)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_word,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvType.text = list[position].partOfSpeech

        val definitionAdapter = DefinitionsAdapter(context, list[position].definitions)
        holder.rvDefinitions.adapter = definitionAdapter

    }
}