package com.m224.showerthoughts.domaine

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.m224.showerthoughts.R

class CardView(itemView: View) {
    var itemTitle: TextView
//    var saveButton: ImageButton
//    var copyButton: ImageButton

    init {
        itemTitle = itemView.findViewById(R.id.title)
//        saveButton = itemView.findViewById(R.id.save_button)
//        copyButton = itemView.findViewById(R.id.copy)
    }
}