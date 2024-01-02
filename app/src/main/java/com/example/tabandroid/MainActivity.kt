package com.example.tabandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.tabandroid.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabandroid.ideal_worldcup.FourthFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tabandroid.ideal_worldcup.StartActivity


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav : BottomNavigationView
    private var hasStartBeenShown = false



override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding.root)
    setContentView(binding.root)

    val fragmentToLoad = intent.getStringExtra("loadFragment")
    if (fragmentToLoad == "FourthFragment") {
        loadFragment(FourthFragment())
    } else {
        // If no specific command, load the default fragment or perform other actions
    }



    val temp = intent.getStringExtra("finished")
    if(temp == "true"){
        loadFragment(FourthFragment())
    }






    bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
    bottomNav.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.contacts -> {
                loadFragment(SecondFragment())
                true
            }
            R.id.gallery -> {
                loadFragment(ThirdFragment())
                true
            }
            R.id.newyear -> {
                loadFragment(FifthFragment())
                true
            }
            R.id.worldcup -> {
                if (!hasStartBeenShown) {
                    // If the start activity hasn't been shown yet, show it and set the flag
                    val startIntent = Intent(this, StartActivity::class.java)
                    startActivity(startIntent)
                    hasStartBeenShown = true
                } else {
                    // If it's already been shown, load the FourthFragment
                    loadFragment(FourthFragment())
                }
                true
            }
            else -> {
                // Add handling for other cases here
                false // Return false or handle the default case accordingly
            }
        }

    }
}
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
    public fun setUpRecyclerView() {

        val images = listOf<Image>(
            Image("Images 1", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 2", "https://img.freepik.com/free-photo/gray-kitty-with-monochrome-wall-her_23-2148955126.jpg?size=626&ext=jpg"),
            Image("Images 3", "https://img.freepik.com/free-photo/domestic-long-haired-cat-lights-against-black-space_181624-24890.jpg?size=626&ext=jpg"),
            Image("Images 4", "https://img.freepik.com/free-photo/vertical-closeup-shot-fat-white-cat-looking-right-dark_181624-41107.jpg?size=626&ext=jpg"),
            Image("Images 5", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 6", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 7", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 8", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 9", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 10", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 11", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 12", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 13", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 14", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 15", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),            Image("Images 4", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 16", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 17", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 18", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 19", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),
            Image("Images 20", "https://img.freepik.com/free-photo/cute-domestic-kitten-sits-window-staring-outside-generative-ai_188544-12519.jpg"),

        )




        val recyclerView = findViewById<RecyclerView>(R.id.imagesRecyclerView)
        val spanCount = 3
        val verticalSpacing = dpToPx(4) // Reduce the vertical spacing here
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, verticalSpacing, true)) // Adjust the spacing by changing dpToPx value
        recyclerView.adapter = ImageAdapter(this, images)
    }

    // Helper function to convert dp to pixels
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }


}