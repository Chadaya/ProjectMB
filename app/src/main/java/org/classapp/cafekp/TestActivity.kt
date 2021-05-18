package org.classapp.cafekp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_test.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class TestActivity : AppCompatActivity() {

    var arr = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        read_json()
    }
    fun read_json()
    {
        var json:String? = null

        try {
            val inputStream: InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(json)
            for (i in 0..jsonarr.length()-1)
            {
                var jsonobj = jsonarr.getJSONObject(i)
                arr.add(jsonobj.getString("Name"))
            }

            var adpt = ArrayAdapter(this, android.R.layout.simple_list_item_1,arr)
            cafe_list.adapter = adpt

        }
        catch (e: IOException)
        {

        }
    }
}