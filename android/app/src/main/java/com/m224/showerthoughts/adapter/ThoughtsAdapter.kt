package com.m224.showerthoughts.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.ImageButton
import com.m224.showerthoughts.R
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.card_layout.view.*

class ThoughtsAdapter(private val context: Context, private val dataset: Array<String>) :
    BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val temp = dataset.get(position)

        val v: View = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)

        val title: TextView
        title = v.findViewById<View>(R.id.title) as TextView
        title.text = temp



//
//        if (true) {
//            v.save_button.setImageResource(R.drawable.heart)
//        } else {
//            v.save_button.setImageResource(R.drawable.heart_outline)
//        }
//
//        v.save_button.setOnClickListener {
//            // dataset[position].save = !dataset[position].save
//            changeSaveImage(v.save_button, true)
//        }
//
//        v.copy.setOnClickListener {
//            Log.d("Debug", "Click on this copy !!!")
//            setClipboard(v.copy.context, v.title.text.toString())
//            Toast.makeText(v.copy.context, "Content copy!", Toast.LENGTH_SHORT).show()
//        }


        return v
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount() = dataset.size

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