package com.m224.showerthoughts

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.m224.showerthoughts.adapter.ThoughtsAdapter
import com.m224.showerthoughts.entity.Thought
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCustomActionbar()
        setRefresherListener()
        setMockThoughts()

    }

    private fun setCustomActionbar() {
        val actionBar = this.supportActionBar
        actionBar!!.setDisplayShowCustomEnabled(true)
        this.title = ""
        val inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflator.inflate(R.layout.appbar, null)
        actionBar.customView = v
    }

    private fun setRefresherListener() {
        findViewById<SwipeRefreshLayout>(R.id.swiperefresh).setOnRefreshListener {
            Log.i("Debug", "onRefresh called from SwipeRefreshLayout")
            findViewById<SwipeRefreshLayout>(R.id.swiperefresh).isRefreshing = false
        }
    }

    private fun setMockThoughts() {
        val dataSet: Array<Thought> = arrayOf(
            Thought("The phrase “ Don’t do drugs and stay in school” is commonly used, yet when you are sick your parents tell you “ Take these drugs and don’t go to school”"),
            Thought("In the purge, there is technically no law to stop the police from enforcing the laws anyway"),
            Thought("Our lives would be a lot shittier if we didn’t have the butthole-clenching reflex"),
            Thought("Egypt built the pyramids, Greece created myths, the Trojan war occurred, Rome rose, conquered the Mediterranean and fell, the Vikings attacked Britain and France, Charlemagne ruled Europe, the Black Death nearly wiped out Western Europe, and the whole time they never knew that pumpkins existed."),
            Thought("Just noticed that -4º looks like somebody’s pooping"),
            Thought("Humans invented machines to skip efforts and then invented gyms to make up for the efforts they skipped"),
            Thought("“All you can eat” buffets are technically “all we can supply at this time” buffets."),
            Thought("It’s more fun being wrong as a pessimist than being wrong as an optimist."),
            Thought("One reason it’s difficult to communicate properly via text is that you interpret their messages with your current attitude")
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = ThoughtsAdapter(dataSet)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


}
