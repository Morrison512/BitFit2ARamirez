package com.example.bitfit2aramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


private const val TAG = "MainActivity/"
class MainActivity : AppCompatActivity() {
    private val bitfits = mutableListOf<BitFit>()
    lateinit var newB: Button
    lateinit var delB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager

        val bitFitFragment: Fragment = BitFitFragment()
        val dashFragment: Fragment = DashFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_home -> fragment = bitFitFragment
                R.id.nav_dashboard -> fragment = dashFragment
            }
            replaceFragment(fragment)

            val bitFit = intent.getSerializableExtra("EXTRA_ENTRY") as BitFit?

            if(bitFit != null) {
                Log.d(TAG, "got extra")
                lifecycleScope.launch(Dispatchers.IO) {
                    (application as BitFItApplication).db.articleDao().insert(
                        BitFitEntity(
                            dayText = bitFit.dayText,
                            hoursSlept = bitFit.hoursSlept
                        )
                    )
                }
            }
            true
        }
        val newB = findViewById<Button>(R.id.newButton)
        newB.setOnClickListener {
            val intent = Intent (this, DetailActivity::class.java)
            this.startActivity(intent)
            }
        val delB = findViewById<Button>(R.id.delButton)
        delB.setOnClickListener{
            lifecycleScope.launch(IO){
                (application as BitFItApplication).db.articleDao().deleteAll()
            }
        }
        bottomNavigationView.selectedItemId = R.id.nav_dashboard
    }

    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bitfit_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }
}