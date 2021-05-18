package org.classapp.cafekp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class  CafeFragment: Fragment() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private class EventItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_cafe: TextView = itemView.findViewById(R.id.name_cafe)
        val img_cafe: ImageView = itemView.findViewById(R.id.img_cafe)
        val cardView: CardView = itemView.findViewById(R.id.cafe_card)
    }

    private class EventListAdapter(var eventObjects: ArrayList<JSONObject>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater: LayoutInflater = LayoutInflater.from(parent.context)
            val entryView: View = viewInflater.inflate(R.layout.card_cafe, parent, false)
            val entryViewHolder: EventItemViewHolder = EventItemViewHolder(entryView)
            return entryViewHolder
        }

        override fun getItemCount(): Int {
            return eventObjects.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val eventObj = eventObjects.get(position)
            val img = eventObj.getString("Image")
            val name = eventObj.getString("Name")
            val id = eventObj.getInt("id").toString()
            if (holder is EventItemViewHolder) {

                val eventViewHolder: EventItemViewHolder = holder
                eventViewHolder.cardView.setOnClickListener {
                    (holder.cardView.context as CafeActivity).setId(id)
                    (holder.cardView.context as CafeActivity).changeFragment(CafeDetailFragment.newInstance())
                }
                eventViewHolder.name_cafe.text = name
                Picasso.get().load(img).into(eventViewHolder.img_cafe)

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_cafe, container, false)
        mRecyclerView = v.findViewById(R.id.recycleview_shop)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager

        //show data
        val json = this.context?.let { getJsonDataFromAsset(it,"data.json") }
        if (json != null) {
            parseJsonEventV2(json.toString())
        }
        return v
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {

        fun newInstance():CafeFragment{
            return CafeFragment()
        }
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

            mAdapter = EventListAdapter(eventObjects)
            mRecyclerView?.adapter = mAdapter

        }catch (e:IOException){

        }
    }
}