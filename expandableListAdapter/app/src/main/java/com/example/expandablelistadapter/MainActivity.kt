package com.example.expandablelistadapter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.expandablelistadapter.ui.theme.ExpandableListAdapterTheme

class MainActivity : ComponentActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var listTitles: List<String>
    private lateinit var detailList: HashMap<String, List<String>>
    var courses = arrayOf<String?>("C", "Data structures",
        "Interview prep", "Algorithms",
        "DSA with java", "OS")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expandableListView = findViewById(R.id.expandableListView)
        listTitles = ArrayList(dataClass().getData().keys)
        detailList = dataClass().getData()
        expandableListAdapter = expandableListAdapter(this, listTitles, detailList)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.setOnGroupExpandListener { groupPosition ->
            val title = listTitles[groupPosition]
            Toast.makeText(applicationContext, "$title List Expanded.", Toast.LENGTH_SHORT).show()
        }
        expandableListView.setOnGroupCollapseListener { groupPosition ->
            val title = listTitles[groupPosition]
            Toast.makeText(applicationContext, "$title List Collapsed.", Toast.LENGTH_SHORT).show()
        }
        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val title = listTitles[groupPosition]
            val subTitle = detailList[listTitles[groupPosition]]!![childPosition]
            Toast.makeText(applicationContext, "$title -> $subTitle", Toast.LENGTH_SHORT).show()
            false
        }

        //SPINNER

        val spin = findViewById<Spinner>(R.id.spinner)
        spin.onItemSelectedListener = this

        // Create the instance of ArrayAdapter
        // having the list of courses
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses)

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.adapter = ad
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(applicationContext,
            courses[position],
            Toast.LENGTH_LONG)
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}