package org.classapp.cafekp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_fav_cafe.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCafe = findViewById<ImageButton>(R.id.btn_cafe)
        btnCafe.setOnClickListener(){startActivity(Intent(this,CafeActivity::class.java))}

        val btnFav = findViewById<ImageButton>(R.id.btn_fav)
        btnFav.setOnClickListener(){startActivity(Intent(this, FavActivity::class.java))}

        val btnNear = findViewById<ImageButton>(R.id.btn_near)
        btnNear.setOnClickListener(){startActivity(Intent(this, NearbyPlacesDemo::class.java))}

        val btnMe = findViewById<ImageButton>(R.id.btn_me)
        btnMe.setOnClickListener(){startActivity(Intent(this, MeActivity::class.java))}

        val btnWn = findViewById<ImageButton>(R.id.btn_wn)
//        btnWn.setOnClickListener(){startActivity(Intent(this, NearbyPlacesDemo::class.java))}

        btnWn.setOnClickListener({
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/channel/UCC1lE2-jHSKiLqv5-hw6uxQ")
                )
                        startActivity(i)
        })
    }
}