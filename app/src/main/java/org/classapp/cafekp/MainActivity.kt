package org.classapp.cafekp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BtnLogin = findViewById<ImageButton>(R.id.btn_login)
        BtnLogin.setOnClickListener(){startActivity(Intent(this,LoginActivity::class.java))}

        val BtnReg = findViewById<ImageButton>(R.id.btn_reg)
        BtnReg.setOnClickListener(){startActivity(Intent(this, RegActivity::class.java))}
    }

    fun openActivity2(view: View?) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}