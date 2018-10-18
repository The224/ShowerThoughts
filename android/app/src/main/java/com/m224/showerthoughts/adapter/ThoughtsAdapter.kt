package com.m224.showerthoughts.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import com.m224.showerthoughts.R
import com.m224.showerthoughts.domaine.CardViewHolder
import com.m224.showerthoughts.entity.Thought


class ThoughtsAdapter(private val dataset: Array<Thought>) :
    RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return CardViewHolder(v)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.itemTitle.text = dataset[position].text

        if (dataset[position].save) {
            holder.saveButton.setImageResource(R.drawable.heart)
        } else {
            holder.saveButton.setImageResource(R.drawable.heart_outline)
        }

        holder.saveButton.setOnClickListener {
            dataset[position].save = !dataset[position].save
            changeSaveImage(holder.saveButton, dataset[position].save)
        }

        holder.copyButton.setOnClickListener {
            Log.d("Debug", "Click on this copy !!!")
            setClipboard(holder.copyButton.context, holder.itemTitle.text.toString())
            Toast.makeText(holder.copyButton.context, "Content copy!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("Copied Text", text)
        clipboard.primaryClip = clip
    }

    private fun changeSaveImage(saveButton: ImageButton, saveState: Boolean) {
        val outAnimation = AnimationUtils.loadAnimation(saveButton.context, R.anim.fadeout)
        val inAnimation = AnimationUtils.loadAnimation(saveButton.context, R.anim.fadein)
        outAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                if (saveState) {
                    saveButton.setImageResource(R.drawable.heart)
                } else {
                    saveButton.setImageResource(R.drawable.heart_outline)
                }
                saveButton.startAnimation(inAnimation)
            }
        })
        saveButton.startAnimation(outAnimation)
    }
}