package com.example.expandablelistadapter

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import java.util.Calendar


class DatePicker : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(activity, activity as OnDateSetListener, year, month, dayOfMonth)
    }
}