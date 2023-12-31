package com.example.bitsandpizzas

import android.app.Fragment
import android.app.ListFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter

class StoreFrag : ListFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter:ArrayAdapter<String> = ArrayAdapter( inflater.context,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.store_locations))
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}