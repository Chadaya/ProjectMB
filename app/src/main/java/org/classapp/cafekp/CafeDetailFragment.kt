package org.classapp.cafekp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class CafeDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v:View = inflater.inflate(R.layout.fragment_cafe_detail, container, false)
//        Toast.makeText(context,id,Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = (context as CafeActivity).getId()
        Toast.makeText(context,id,Toast.LENGTH_LONG).show()
    }

    companion object {

        fun newInstance():CafeDetailFragment{
            return CafeDetailFragment()
        }
    }

}