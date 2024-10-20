package com.practicum.testcleanarchitecture.ui.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practicum.testcleanarchitecture.R
import com.practicum.testcleanarchitecture.presentation.cast.MoviesCastRVItem

class MovieCastHeaderViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_header, parent, false)
    ) {

    var headerTextView: TextView = itemView.findViewById(R.id.headerTextView)

    fun bind(item: MoviesCastRVItem.HeaderItem) {
        headerTextView.text = item.headerText
    }

}