package com.m224.showerthoughts

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.m224.showerthoughts.adapter.ThoughtsAdapter
import com.m224.showerthoughts.entity.Thought

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customActionbar(this, R.layout.appbar)

        val dataset: Array<Thought> = arrayOf(
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
        viewAdapter = ThoughtsAdapter(dataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    fun customActionbar(appCompatActivity: AppCompatActivity, resources: Int) {
        val actionBar = appCompatActivity.supportActionBar
        actionBar!!.setDisplayShowCustomEnabled(true)
        appCompatActivity.title = ""

        val inflator = appCompatActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflator.inflate(resources, null)

        actionBar.customView = v
    }
}
