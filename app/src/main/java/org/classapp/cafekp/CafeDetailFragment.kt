package org.classapp.cafekp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class CafeDetailFragment : Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private class EventItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_cafedetail: TextView = itemView.findViewById(R.id.name_cafedetail)
        val review_cafedetail: TextView = itemView.findViewById(R.id.review_cafedetail)
        val adress_cafedetail: TextView = itemView.findViewById(R.id.adress_cafedetail)
        val cafedetail_img: ImageView = itemView.findViewById(R.id.cafedetail_img)
        val cardView: CardView = itemView.findViewById(R.id.cafe_card)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v:View = inflater.inflate(R.layout.fragment_cafe_detail, container, false)
//        Toast.makeText(context,id,Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        mRecyclerView = v.findViewById(R.id.recycleview_shop)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = (context as CafeActivity).getId()
        Toast.makeText(context,id,Toast.LENGTH_LONG).show()

        val json = getJsonDataFromAsset(view.context,"data.json")
        if (json!=""){
            var jsonArr = JSONArray(json.toString())
            for (i in 0..jsonArr.length()-1){

                var jsonObj = JSONObject(jsonArr[i].toString())
                if (jsonObj["id"].toString() == id){
                    view.findViewById<TextView>(R.id.name_cafedetail).text = jsonObj["Name"].toString()
                    view.findViewById<TextView>(R.id.review_cafedetail).text = jsonObj["Review"].toString()
                    view.findViewById<TextView>(R.id.adress_cafedetail).text = jsonObj["Address"].toString()
                    Picasso.get().load(jsonObj["Image"].toString()).into(view.findViewById<ImageView>(R.id.cafedetail_img))
                }


            }

        }


    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
    private var eventObjects = arrayListOf<JSONObject>()
    private fun parseJsonEventV2(jsonStr:String){

        try {

            var jsonarr = JSONArray(jsonStr)
            for (i in 0..jsonarr.length()-1)
            {
                var jsonobj = jsonarr.getJSONObject(i)
                eventObjects.add(jsonobj)
            }


        }catch (e:IOException){

        }
    }

    companion object {

        fun newInstance():CafeDetailFragment{
            return CafeDetailFragment()
        }
    }

}