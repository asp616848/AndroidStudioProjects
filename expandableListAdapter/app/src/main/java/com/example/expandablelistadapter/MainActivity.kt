package com.example.expandablelistadapter

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.text.DateFormat
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.AdapterView
import android.widget.AnalogClock
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DigitalClock
import android.widget.EditText
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
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
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Calendar

class MainActivity : ComponentActivity(), AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener{

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var listTitles: List<String>
    private lateinit var detailList: HashMap<String, List<String>>
    var courses = arrayOf<String?>("C", "Data structures",
        "Interview prep", "Algorithms",
        "DSA with java", "OS")
    lateinit var textView: TextView
    lateinit var button2: Button

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
            courses
        )

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.adapter = ad


        //ALERT DIALOG CUSTOM
        val editText = EditText(this)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Input Dialog")
            builder.setMessage("Enter your message here")
            builder.setCancelable(false)
            builder.setView(editText)
            builder.setPositiveButton("OK") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    editText.text.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
            val edittextDialog = builder.create()
            edittextDialog.show()

        }
        //Using Picaso
        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.ic_launcher)
            .error(
                androidx.core.R.drawable.ic_call_decline_low
            ).centerCrop()
            .fit().into(imageView)
        //placeHolder is used to show image while loading or if it fails to load the real image and error(fallback) isn't provided either
        //.resize(width,height) can be used to resize the image but it can't be used with fit settings or crops
        //centerCrop() crops the image from center
        val digitalClock = findViewById<DigitalClock>(R.id.digitalClock)
        digitalClock.setOnClickListener {
            Toast.makeText(applicationContext, "Time is ${digitalClock.text}", Toast.LENGTH_SHORT)
                .show()
        }
        //setting a countDownTimer (Different from pulse timer) using textview
        val textClock = findViewById<TextView>(R.id.textClock)
        textClock.setOnClickListener {
            object : CountDownTimer(50000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val f: DecimalFormat = DecimalFormat("00")
                    val hour = millisUntilFinished / 3600000 % 24
                    val min = millisUntilFinished / 60000 % 60
                    val sec = millisUntilFinished / 1000 % 60
                    textClock.text = "${f.format(hour)}:${f.format(min)}:${f.format(sec)}"
                }

                override fun onFinish() {
                    textClock.text = "00:00:00"
                }
            }.start()
        }

        //now using calender with dialog to set date
        button2 = findViewById<Button>(R.id.button2)
        textView = findViewById<TextView>(R.id.textView)
        button2.setOnClickListener {
            val datePicker = DatePicker()
            datePicker.show(fragmentManager, "date picker")
        }

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
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you SURE,You want to EXIT?")
        builder.setTitle("ALERT!")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){
            dialog, which -> super.onBackPressed()
        }
        builder.setNegativeButton("No"){
                dialog, which -> dialog.cancel()
        }
        val alertdialog = builder.create()
        alertdialog.show()
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val myCalendar = Calendar.getInstance()
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val string = DateFormat.getDateInstance(DateFormat.FULL).format(myCalendar.time)
        textView.text = string
    }
}