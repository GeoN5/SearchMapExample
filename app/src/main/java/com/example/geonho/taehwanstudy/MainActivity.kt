package com.example.geonho.taehwanstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.geonho.taehwanstudy.util.getData
import com.example.geonho.taehwanstudy.util.getList
import com.example.geonho.taehwanstudy.util.saveData
import com.example.geonho.taehwanstudy.util.saveList
import com.example.geonho.taehwanstudy.util.getList
import com.example.geonho.taehwanstudy.util.saveList
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(),TextView.OnEditorActionListener, OnMapReadyCallback {

    val FINSH_INTERVAL_TIME = 2000
    var backPressedTime:Long = 0
    var searchList :MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchList.addAll(getList("list"))

        init()
        setListeners()
    }

    private fun init(){
        searchEdit.setOnEditorActionListener(this)
        val mapFragment : MapFragment = fragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        val seoul = LatLng(37.56,126.97)
        val markerOptions : MarkerOptions = MarkerOptions().position(seoul).title("서울").snippet("한국의 수도")
        map.addMarker(markerOptions)

        map.moveCamera(CameraUpdateFactory.newLatLng(seoul))
        map.animateCamera(CameraUpdateFactory.zoomTo(10F))

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
        if (v?.id == R.id.searchEdit && actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN) {
            val text = v?.text.toString()
            searchList.add(text)
        }
        return false
    }

    private fun setListeners(){
        searchEdit.setOnClickListener {
            recyclerview.visibility = View.VISIBLE
            setRecyclerView()
        }
    }

    private fun setRecyclerView(){
        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = RecyclerAdapter(searchList, applicationContext, object : OnItemClickListener {
            override fun onItemClick(items: String) {
                searchEdit.setText(items)
            }

            override fun onItemDelete(position: Int) {
                searchList.removeAt(position)
                //recyclerview.adapter.notifyItemRemoved(position)
                recyclerview.adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onBackPressed() {
        searchList.saveList(this,"list")
        if (recyclerview.visibility != View.GONE) {
            recyclerview.visibility = View.GONE
        } else {
            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime
            if (intervalTime in 0..FINSH_INTERVAL_TIME) {
                ActivityCompat.finishAffinity(this)
            } else {
                backPressedTime = tempTime;
                Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
