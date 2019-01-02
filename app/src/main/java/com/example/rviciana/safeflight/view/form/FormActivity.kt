package com.example.rviciana.safeflight.view.form

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.SafeFlightsApplication
import com.example.rviciana.safeflight.domain.bo.Airport
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.view.NavigatorImpl
import com.example.rviciana.safeflight.view.RootActivity
import com.example.rviciana.safeflight.view.form.di.FormModule
import kotlinx.android.synthetic.main.activity_form.*
import javax.inject.Inject

class FormActivity : RootActivity(), FormContract.View {

    @Inject lateinit var presenter: FormContract.Presenter

    private val Activity.app: SafeFlightsApplication get() = application as SafeFlightsApplication
    private val component by lazy { app.component.plus(FormModule()) }

    companion object {

        fun getCallingIntent(context: Context, airportsResponse: AirportsResponse): Intent {
            val intent = Intent(context, FormActivity::class.java)
            intent.putExtra(ARG_AIRPORTS, airportsResponse)
            return intent
        }

        private const val ARG_AIRPORTS = "ARG_AIRPORTS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtras()
        setListeners()
    }

    private fun getExtras() {
        val extras = intent.extras
        extras?.apply {
            val airportsResponse = getParcelable(ARG_AIRPORTS) as AirportsResponse
            val airports = airportsResponse.airportResource.airports.airport

            val arrayAdapter = SuggestionsArrayAdapter(
                    this@FormActivity,
                    R.layout.suggestion_adapter_item,
                    ArrayList(airports),
                    ArrayList(airports)
            )

            searchViewOrigin.setAdapter(arrayAdapter)
            searchViewDestination.setAdapter(arrayAdapter)
        }
    }

    private fun setListeners() {
        var originAirport: Airport? = null
        var destinationAirport: Airport? = null

        searchViewOrigin.onItemClickListener =
                AdapterView.OnItemClickListener {
                    parent, _, position, _
                    -> originAirport = parent!!.adapter.getItem(position) as Airport
                }

        searchViewDestination.onItemClickListener =
                AdapterView.OnItemClickListener {
                    parent, _, position, _
                    -> destinationAirport = parent!!.adapter.getItem(position) as Airport
                }

        searchFlights.setOnClickListener {
            if (originAirport != null && destinationAirport != null) {
                presenter.onAirportsReady(originAirport!!, destinationAirport!!)
            }
        }
    }

    override fun initializeInjector() {
        component.inject(this@FormActivity)
    }

    override fun initializePresenter() {
        presenter.setView(this@FormActivity)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_form

    override fun navigateToFlightsListActivity(originAirport: Airport, destinationAirport: Airport) {
        NavigatorImpl(this@FormActivity)
                .navigateToFlightsListActivity(originAirport, destinationAirport)
    }
}
