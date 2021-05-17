package org.classapp.cafekp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCafe = findViewById<ImageButton>(R.id.btn_cafe)
        btnCafe.setOnClickListener(){startActivity(Intent(this,LoginActivity::class.java))}

        val btnFav = findViewById<ImageButton>(R.id.btn_fav)
        btnFav.setOnClickListener(){startActivity(Intent(this, RegActivity::class.java))}

        val btnNear = findViewById<ImageButton>(R.id.btn_near)
        btnNear.setOnClickListener(){startActivity(Intent(this, NearbyPlacesDemo::class.java))}
    }
}