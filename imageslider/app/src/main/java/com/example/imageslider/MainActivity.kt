package com.example.imageslider

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class MainActivity : ComponentActivity() {
    val sliderDataArray = ArrayList<sliderData>()
    lateinit var db: FirebaseFirestore
    var sliderView: SliderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_main)
        sliderView = findViewById<SliderView>(R.id.slider)
        loadImages()
    }

    private fun loadImages() {
        db.collection("images").get().addOnSuccessListener { queryDocumentSnapshots ->
            for (documentSnapshot in queryDocumentSnapshots) {
                val imageUrl = documentSnapshot.getString("imageUrl")
                val sliderModel = sliderData(imageUrl!!)
                sliderDataArray.add(sliderModel)
            }
            // Set adapter and configure SliderView outside the loop
            val adapter = Adapter(this@MainActivity, sliderDataArray)
            sliderView?.setSliderAdapter(adapter)
            sliderView?.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            sliderView?.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            sliderView?.scrollTimeInSec = 3
            sliderView?.isAutoCycle = true
            sliderView?.startAutoCycle()
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Error getting documents: $exception", Toast.LENGTH_LONG).show()
        }
    }
}
