package org.classapp.cafekp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class CafeActivity : AppCompatActivity() {

    private lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)
        val CafeFragment = CafeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.FrameCafeFragment,CafeFragment)
            .commit()


    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameCafeFragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun setId(ids:String){
        this.id=ids
    }
    fun getId():String{
        return this.id
    }

}