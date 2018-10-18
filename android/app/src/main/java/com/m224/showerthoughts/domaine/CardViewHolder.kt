package com.m224.showerthoughts.domaine

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.m224.showerthoughts.R

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var itemTitle: TextView
    var saveButton: ImageButton
    var copyButton: ImageButton

    init {
        itemTitle = itemView.findViewById(R.id.title)
        saveButton = itemView.findViewById(R.id.save_button)
        copyButton = itemView.findViewById(R.id.copy)
    }
}