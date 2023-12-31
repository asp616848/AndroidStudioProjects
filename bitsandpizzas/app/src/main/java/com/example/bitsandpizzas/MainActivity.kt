package com.example.bitsandpizzas

import android.app.Activity
import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ShareActionProvider
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager


class MainActivity : Activity()    {
    private lateinit var titles : Array<String>
    private lateinit var drawerList : ListView
    private lateinit var drawerLayout: DrawerLayout
    private var shareActionProvider : ShareActionProvider? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var currentPosition = 0

    //    private class DrawerItemClick , ListView.OnItemClickListener{
//        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//        }
//    } wrong

    private inner class DrawerItemClick : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectItem(position)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerList = findViewById(R.id.drawer)
        titles = resources.getStringArray(R.array.titles)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        drawerList.adapter = adapter
        drawerList.onItemClickListener = DrawerItemClick()
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position")
            setActionBarTitle(currentPosition)
        }
        else{
            selectItem(0)
        }
        drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        ){
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                invalidateOptionsMenu()
            }

            override fun onDrawerOpened(view: View) {
                super.onDrawerOpened(view)
                invalidateOptionsMenu()
            }
        }

        drawerLayout.addDrawerListener(drawerToggle as ActionBarDrawerToggle)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        fragmentManager.addOnBackStackChangedListener {
            val fragManager = fragmentManager
            val fragment = fragManager.findFragmentByTag("visible_fragment")
            if (fragment is TopFrag) {
                currentPosition = 0
            }
            if (fragment is PizzaFrag) {
                currentPosition = 1
            }
            if (fragment is PastaFrag) {
                currentPosition = 2
            }
            if (fragment is StoreFrag) {
                currentPosition = 3
            }
            setActionBarTitle(currentPosition)
            drawerList.setItemChecked(currentPosition, true)
        }
    }
    private fun selectItem(position:Int){
        currentPosition = position
        val fragment : Fragment = when(position){
            1 -> PizzaFrag()
            2 -> PastaFrag()
            3 -> StoreFrag()
            else -> TopFrag()
        }
        val fm = fragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.content_frame, fragment, "visible_fragment")
        ft.addToBackStack(null)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
        setActionBarTitle(position)
        val drawerLay :DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLay.closeDrawer(drawerList)
    }

    override fun onPrepareOptionsMenu(menu :Menu): Boolean{

        val drawerOpen : Boolean = drawerLayout.isDrawerOpen(drawerList)
        menu.findItem(R.id.action_share).isVisible = !drawerOpen
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", currentPosition)
    }




    private fun setActionBarTitle(position: Int) {
        if (position == 0) {
            actionBar?.setTitle(R.string.app_name)
        } else {
            actionBar?.title = titles[position]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.action_share)
        shareActionProvider = if (menuItem?.actionProvider != null) {
            menuItem.actionProvider as ShareActionProvider
        } else {
            null
        }
        setIntent("This is example text") //this will set-intent for the above shareActionProvider with the input text
        return super.onCreateOptionsMenu(menu)
    }
    private fun setIntent(text :String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain" //MIME type
        intent.putExtra(Intent.EXTRA_TEXT, text)
        shareActionProvider?.setShareIntent(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle?.onOptionsItemSelected(item) == true) return true
        when(item.itemId)  {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_settings -> {
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}