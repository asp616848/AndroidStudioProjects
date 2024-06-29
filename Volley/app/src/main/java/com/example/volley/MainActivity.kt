package com.example.volley

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley.ui.theme.VolleyTheme
import com.squareup.picasso.Picasso
import org.json.JSONException
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.HostnameVerifier

class MainActivity : ComponentActivity() {

    private var courseIV: ImageView? = null
    private var courseNameTV: TextView? = null
    private var courseTracksTV: TextView? = null
    private var courseBatchTV: TextView? = null
    private var loadingPB: ProgressBar? = null
    private var courseCV: CardView? = null

    val url :String = "https://www.jsonkeeper.com/b/LOK1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        courseIV = findViewById(R.id.idIVCourse)
        courseNameTV = findViewById(R.id.idTVCourseName)
        courseTracksTV = findViewById(R.id.idTVTracks)
        courseBatchTV = findViewById(R.id.idTVBatch)
        loadingPB = findViewById(R.id.idLoadingPB)
        courseCV = findViewById(R.id.idCVCourse)

        val hostnameVerifier = HostnameVerifier { _, _ -> true }
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier)

        val queue : RequestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest( Request.Method.GET, url, null, { response ->
            loadingPB?.setVisibility(View.GONE)
            courseCV?.setVisibility(View.VISIBLE)
            try {
                val courseName = response.getString("courseName")
                val courseTracks = response.getString("courseTracks")
                val courseBatch = response.getString("courseMode")
                val courseImageURL = response.getString("courseimg")
                courseNameTV?.setText(courseName)
                courseTracksTV?.setText(courseTracks)
                courseBatchTV?.setText(courseBatch)
                Picasso.get()
                    .load(courseImageURL)
                    .into(courseIV)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            Toast.makeText(this@MainActivity, "Fail to get data: ${error.message}", Toast.LENGTH_SHORT).show()
            Log.e("MainActivity", "Error: ${error.message}")
        })
        queue.add(request)
    }
}