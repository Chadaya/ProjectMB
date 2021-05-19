package org.classapp.cafekp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavCafeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_fav_cafe, container, false)
        var mRecyclerView = v.findViewById<RecyclerView>(R.id.favList)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = FavoriteListAdapter()
        return v
    }

    //show favlist
    private class FavItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    {
        val favCafe: TextView = itemView.findViewById(android.R.id.text1)
    }

    private inner class FavoriteListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val viewInflater:LayoutInflater = LayoutInflater.from(parent.context)
            val entryView:View = viewInflater.inflate(android.R.layout.simple_list_item_1,parent,false)
            return FavItemViewHolder(entryView)
        }

        override fun getItemCount(): Int {
            var sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(activity)
            var favEventSet = sharedPref.getStringSet("favEvents",null)
            if (favEventSet == null) return 0
            else return favEventSet.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(activity)
            var favEventSet = sharedPref.getStringSet("favEvents",null)
            if (favEventSet != null) {
                var favEventList = favEventSet.toList()
                if (holder is FavItemViewHolder)
                {
                    holder.favCafe.text = favEventList.get(position)
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavCafeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FavCafeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}