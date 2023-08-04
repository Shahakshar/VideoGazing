package com.example.videogazing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videogazing.api.ApiService
import com.example.videogazing.api.ServiceGenerator
import com.example.videogazing.local.VideoDao
import com.example.videogazing.local.Videos
import com.example.videogazing.local.VideosDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var rvList : RecyclerView
    private lateinit var adapter: VideoRecyclerView;
    private lateinit var videoDao: VideoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()

        val queries :HashMap<String, String> = HashMap()
        queries["part"] = "id,snippet"
        queries["type"] = "video"
        queries["maxResults"] = "50"

        val apiService = ServiceGenerator.buildService(ApiService::class.java)
        videoDao = VideosDatabase.getInstance(this@MainActivity).videoDao()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.GetVideos(queries)

                if(response.isSuccessful) {
                    val list = response.body()?.items
                    if (!list?.isEmpty()!!) {
                        adapter = VideoRecyclerView(list)
                    }
                    rvList.adapter = adapter



                    list.forEach {item ->
                        Log.d("TAG", "onCreate: $item")
                        videoDao.insert(
                            Videos(
                            title = item.snippet.title.toString(),
                            videoUrl = item.snippet.thumbnails.high.url
                        ))
                    }

                } else {
                    print("Something wrong response not successful")
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("TAG", "onCreate: ${videoDao.getAllVideo()}")
        }


    }

    private fun initView() {
        rvList = findViewById(R.id.rvList);
    }

    private fun initRecyclerView() {
        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}
