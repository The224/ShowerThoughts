package com.m224.showerthoughts

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import com.m224.showerthoughts.adapter.ThoughtsAdapter
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection

class MainActivity : AppCompatActivity() {

    private lateinit var cardStackView: CardStackView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCustomActionbar()

        val dataSet: Array<String> = arrayOf(
            "If all humans truly descended from solely Adam and Eve, it is very possible to have a “twin” that you aren’t related to due to genetics.",
            "Marijuana edibles start working the minute you begin to doubt them",
            "Even if you could travel through time, Earth wouldn't be where you left it.",
            "Regardless of personality, a good lumberjack is a good fellar.",
            "You never actually close your eyes, there's just a flap of skin over them.",
            "One man’s bolognese is another man’s chili.",
            "Your children love you for who they think you are. That's also why your exes hate you.",
            "Losing power at home gets more and more terrifying with each passing year.",
            "You can’t confront someone about lying without inadvertently teaching them how to be a better liar.",
            "The smallest cuts hurt the most"
        )

        val viewAdapter = ThoughtsAdapter(this.baseContext, dataSet)

        cardStackView = findViewById(R.id.cardStack)
        cardStackView.setAdapter(viewAdapter)






        val up = findViewById<ImageButton>(R.id.upBtn)
        val down = findViewById<ImageButton>(R.id.downBtn)

        val test = AnimatorSet()

        test.playSequentially(ObjectAnimator.ofFloat(4.9F),
        ObjectAnimator.ofFloat(4.9F),
        ObjectAnimator.ofFloat(4.9F),
        ObjectAnimator.ofFloat(4.9F))




        up.setOnClickListener {
            Toast.makeText(this.baseContext, "UP", Toast.LENGTH_LONG).show()
            cardStackView.performSwipe(SwipeDirection.Left, test, test, null)
        }

        down.setOnClickListener {
            Toast.makeText(this.baseContext, "DOWN", Toast.LENGTH_LONG).show()
            cardStackView.performSwipe(SwipeDirection.Right, test, test, null)
        }






    }

    private fun setCustomActionbar() {
        val actionBar = this.supportActionBar
        actionBar!!.setDisplayShowCustomEnabled(true)
        this.title = ""
        val inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflator.inflate(R.layout.appbar, null)
        actionBar.customView = v
    }


}
