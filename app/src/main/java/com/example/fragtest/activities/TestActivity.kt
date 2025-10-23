package com.example.fragtest.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragtest.R
import com.example.fragtest.adapters.ListGroupAdapter
import com.example.fragtest.data.MusicGroupService
import com.example.fragtest.data.MusicGroups
import com.example.fragtest.databinding.ActivityTestBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity()
{
    /**/

    //
    lateinit var binding: ActivityTestBinding
    lateinit var adapter: ListGroupAdapter
    var originalVgList: List<MusicGroups> = emptyList()
    var filteredVgList: List<MusicGroups> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*Start of onCreate*/

        //
        adapter = ListGroupAdapter(filteredVgList) { position ->
            val videoGame = filteredVgList[position]
            /*val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.PUT_EXTRA_GAME_ID, videoGame.id)
            startActivity(intent)*/
        }
        //
        binding.idRvTest.adapter = adapter
        binding.idRvTest.layoutManager = LinearLayoutManager(this)

        getGroupList()

    }/*End of onCreate*/


    /*We create the getGameList function*/
    fun getGroupList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = MusicGroupService.getInstance()
                originalVgList = service.getAllGroups()
                filteredVgList = originalVgList
                CoroutineScope(Dispatchers.Main).launch {
                    adapter.updateItems(filteredVgList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}