package com.example.bargraphanimate

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bargraphanimate.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    // variable for our bar chart
    private lateinit var barChart: BarChart

    // variables for our bar data sets
    private lateinit var barDataSet1: BarDataSet
    private lateinit var barDataSet2: BarDataSet

    // array list for storing entries
    private lateinit var barEntries: ArrayList<BarEntry>

    // creating a string array for displaying days
    private val days = arrayOf("Sunday", "","Monday","", "Tuesday","","Wednesday", "", "Thursday","", "Friday","", "Saturday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        // initializing variable for bar chart
        barChart = findViewById(R.id.idBarChart)

        // creating a new bar data set
        barDataSet1 = BarDataSet(getBarEntriesOne(), "First Set")
        barDataSet1.color = applicationContext.resources.getColor(R.color.purple_200)
        barDataSet2 = BarDataSet(getBarEntriesTwo(), "Second Set")
        barDataSet2.color = Color.BLUE

        // adding bar data sets to the bar data
        var data = BarData(barDataSet1, barDataSet2)

        // setting data to the bar chart
        barChart.data = data

        // removing description label of the bar chart
        barChart.description.isEnabled = true


        // getting x axis of the bar chart
        val xAxis = barChart.xAxis

        // setting value formatter to x-axis
        // and adding days to the x axis
        xAxis.valueFormatter = IndexAxisValueFormatter(days)

        // setting center axis labels to the bar chart
        xAxis.setCenterAxisLabels(true)

        // setting position to the x-axis to bottom
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // setting granularity to x axis labels
        xAxis.granularity = 1f

        // enabling granularity to x axis
        xAxis.isGranularityEnabled = true

        // making bar chart draggable
        barChart.isDragEnabled = true
        // setting width of bar
        data.barWidth = 0.5f

        // setting minimum axis to the chart
        barChart.xAxis.axisMinimum = -0.5f
        xAxis.axisMaximum = 14f
        barChart.setVisibleXRangeMaximum(4f)

        // animating the chart
        barChart.animateXY(1500, 1500)

        // grouping bars and adding spacing to it
        barChart.groupBars(-0.5f, 1f,0f)
        barChart.setBackgroundColor(Color.GREEN)
        val yAxisLeft = barChart.axisLeft
        val yAxisRight = barChart.axisRight //these lines removed grid lines
        xAxis.setDrawGridLines(false)
        yAxisLeft.setDrawGridLines(false)
        yAxisRight.setDrawGridLines(false)


        // invalidating the bar chart
        // Delay invalidate() by 2000 milliseconds (2 seconds)
        barChart.postDelayed({
            barChart.invalidate()
        }, 10000) // Animate the chart to its final state after 2 seconds
    }

    override fun onResume() {

        super.onResume()
        barChart.animateXY(2000, 1500 )
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    // array list for the first set
    private fun getBarEntriesOne(): ArrayList<BarEntry> {
        barEntries = ArrayList()

        // adding new entry to the array list with bar entry and passing x and y axis value to it
        barEntries.add(BarEntry(1f, 4f))
        barEntries.add(BarEntry(2f, 6f))
        barEntries.add(BarEntry(3f, 8f))
        barEntries.add(BarEntry(4f, 2f))
        barEntries.add(BarEntry(4f, 2f))
        barEntries.add(BarEntry(5f, 4f))
        barEntries.add(BarEntry(6f, 1f))

        return barEntries
    }

    // array list for the second set
    private fun getBarEntriesTwo(): ArrayList<BarEntry> {
        barEntries = ArrayList()

        // adding new entry to the array list with bar entry and passing x and y axis value to it
        barEntries.add(BarEntry(1f, 8f))
        barEntries.add(BarEntry(2f, 12f))
        barEntries.add(BarEntry(3f, 4f))
        barEntries.add(BarEntry(4f, 6f))
        barEntries.add(BarEntry(4f, 1f))
        barEntries.add(BarEntry(5f, 7f))
        barEntries.add(BarEntry(6f, 3f))

        return barEntries
    }
}
