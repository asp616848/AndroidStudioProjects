package com.example.expandablelistadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.TextView

class expandableListAdapter(
    private val context: Context,
    private val expandableListTitle: List<String>,
    private val expandableListDetail: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableListDetail[expandableListTitle[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableListTitle[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableListDetail[expandableListTitle[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
         return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertViewLocal = convertView
        val listTitle = getGroup(groupPosition) as String
        if(convertViewLocal == null) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            //val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertViewLocal = layoutInflater.inflate(R.layout.title, parent, false)
        }
        val title = convertViewLocal?.findViewById<TextView>(R.id.title_text)
        title?.text = listTitle
        return convertViewLocal!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
          var convertViewLocal = convertView
          val listTitle = getChild(groupPosition, childPosition) as String
          if(convertViewLocal == null) {
                val layoutInflater = LayoutInflater.from(parent?.context)
                convertViewLocal = layoutInflater.inflate(R.layout.expanded_list, parent, false)
          }
          val title = convertViewLocal?.findViewById<TextView>(R.id.ExpandedTitle)
          title?.text = listTitle
          return convertViewLocal!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}