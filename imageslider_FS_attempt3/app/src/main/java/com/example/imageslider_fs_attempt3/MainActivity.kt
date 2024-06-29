package com.example.imageslider_fs_attempt3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class MainActivity : ComponentActivity() {

    private lateinit var adapter: SliderAdapter
    private lateinit var sliderDataArrayList: ArrayList<SliderData>
    private lateinit var db: FirebaseFirestore
    private lateinit var sliderView: SliderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderDataArrayList = ArrayList()
        sliderView = findViewById(R.id.slider)
        db = FirebaseFirestore.getInstance()
        loadImages()
    }
    private fun loadImages() {
        db.collection("images").get()
            .addOnSuccessListener { docs ->
                for (eachUrl in docs) {
                    val sliderData:SliderData = SliderData(eachUrl.getString("imgUrl")!!)
                    Toast.makeText(this@MainActivity, eachUrl.getString("imgUrl"), Toast.LENGTH_SHORT).show()
                    sliderDataArrayList.add(sliderData)
                }

                adapter = SliderAdapter(this@MainActivity, sliderDataArrayList)
                sliderView.setSliderAdapter(adapter)
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
                sliderView.setScrollTimeInSec(3)
                sliderView.isAutoCycle = true
                sliderView.startAutoCycle()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Fail to load slider data..", Toast.LENGTH_SHORT).show()
            }
    }
}
