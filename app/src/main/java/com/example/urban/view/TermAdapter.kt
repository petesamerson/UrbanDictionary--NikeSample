package com.example.urban.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.R
import com.example.urban.model.Term

class TermAdapter() : RecyclerView.Adapter<TermAdapter.TermViewHolder>() {

    var terms:List<Term> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item,parent,false)
        return TermViewHolder(itemView)
    }

    override fun getItemCount() = terms.size

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        holder.tvTerm.text = terms[position].word
        holder.tvThumbsUp.text = terms[position].thumbs_up.toString()
        holder.tvThumbsDown.text = terms[position].thumbs_down.toString()
        holder.tvDefinition.text = terms[position].definition
    }

    class TermViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTerm : TextView = itemView.findViewById(R.id.tv_term)
        val tvThumbsUp : TextView = itemView.findViewById(R.id.tv_thumbs_up)
        val tvThumbsDown : TextView = itemView.findViewById(R.id.tv_thumbs_down)
        val tvDefinition : TextView =  itemView.findViewById(R.id.tv_definition)
    }
}