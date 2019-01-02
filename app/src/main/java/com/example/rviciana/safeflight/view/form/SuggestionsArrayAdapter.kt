package com.example.rviciana.safeflight.view.form

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.domain.bo.Airport
import com.example.rviciana.safeflight.extensions.inflate
import kotlinx.android.synthetic.main.suggestion_adapter_item.view.*

class SuggestionsArrayAdapter(activity: Context,
                              layoutResourceId: Int,
                              val airportsArray: ArrayList<Airport>,
                              val completeAirportsArray: ArrayList<Airport>
) : ArrayAdapter<Airport>(activity, layoutResourceId, airportsArray) {

    override fun getCount(): Int = airportsArray.size

    override fun getItem(position: Int): Airport? = airportsArray[position]


    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val item = getItem(position)

        view = parent.inflate(R.layout.suggestion_adapter_item)
        view.name.text = item?.names?.nameList!![0].name

        return view
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun convertResultToString(resultValue: Any?): CharSequence {
            val airport = resultValue as Airport
            return airport.names.nameList[0].name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            val airportsSuggestionArray = ArrayList<Airport>()

            if (constraint != null) {
                for (airport in airportsArray) {
                    val airportName = airport.names.nameList[0].name
                    if (airportName.toLowerCase().startsWith(
                                    constraint.toString().toLowerCase())) {
                        airportsSuggestionArray.add(airport)
                    }
                }

                filterResults.values = airportsSuggestionArray
                filterResults.count = airportsSuggestionArray.size
            }

            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            airportsArray.clear()

            if (results != null) {
                if (results.count > 0) {
                    val resultsList = results.values as List<*>
                    for (any in resultsList) {
                        airportsArray.add(any as Airport)
                    }

                    notifyDataSetChanged()

                } else if (constraint == null) {
                    airportsArray.addAll(completeAirportsArray)
                    notifyDataSetChanged()
                }
            }
        }
    }
}



