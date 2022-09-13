package com.cloudin.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudin.myapplication.R
import com.cloudin.myapplication.model.Definition

class DefinitionsAdapter() : RecyclerView.Adapter<DefinitionsAdapter.ViewHolder>(){

    lateinit var context: Context
    private var list : List<Definition> = ArrayList()

    constructor(context: Context, list :List<Definition>) : this() {
        this.context = context
        this.list = list
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = itemView.findViewById(R.id.tv_description)
        var layoutExample: LinearLayout = itemView.findViewById(R.id.layout_example)
        var tvExample:TextView = itemView.findViewById(R.id.tv_example)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_definitions,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = "\u2022 "+list[position].definition
        if(!list[position].example.isNullOrEmpty()){
            holder.layoutExample.visibility = View.VISIBLE
            holder.tvExample.text = " ${list[position].example}"
        }else{
            holder.layoutExample.visibility = View.GONE
        }
    }
}