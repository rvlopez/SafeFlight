package com.example.rviciana.safeflight.view.map

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.SafeFlightsApplication
import com.example.rviciana.safeflight.domain.bo.Airport
import com.example.rviciana.safeflight.domain.bo.Schedule
import com.example.rviciana.safeflight.extensions.getValue
import com.example.rviciana.safeflight.extensions.hide
import com.example.rviciana.safeflight.extensions.show
import com.example.rviciana.safeflight.extensions.today
import com.example.rviciana.safeflight.view.RootActivity
import com.example.rviciana.safeflight.view.map.di.FlightsModule
import com.example.rviciana.safeflight.view.oauth.OAuthActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.map_bottom_sheet.*
import kotlinx.android.synthetic.main.translucent_toolbar.*
import java.util.*
import javax.inject.Inject

class FlightsMapListActivity : RootActivity(), FlightsListContract.View, OnMapReadyCallback {

    @Inject lateinit var presenter: FlightsListContract.Presenter

    private lateinit var originAirport: Airport
    private lateinit var destinationAirport: Airport

    private val Activity.app: SafeFlightsApplication get() = application as SafeFlightsApplication
    private val component by lazy { app.component.plus(FlightsModule()) }

    companion object {

        fun getCallingIntent(context: Context, originAirport: Airport, destinationAirport: Airport): Intent {
            val intent = Intent(context, FlightsMapListActivity::class.java)
            intent.putExtra(ARG_ORG_AIRPORT, originAirport)
            intent.putExtra(ARG_DEST_AIRPORT, destinationAirport)
            return intent
        }

        private const val ARG_ORG_AIRPORT = "ARG_ORG_AIRPORT"
        private const val ARG_DEST_AIRPORT = "ARG_DEST_AIRPORT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initMaps()
        getExtras()
        initToolbar()
        getFlights()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun initViews() {
        with(bottomSheetRecyclerView) {
            adapter = FlightsListAdapter()
            val linearLayoutManager = LinearLayoutManager(this@FlightsMapListActivity)
            val dividerItemDecoration = DividerItemDecoration(
                    this@FlightsMapListActivity,
                    DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(
                    this@FlightsMapListActivity,
                    R.drawable.divider_item)!!)
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
        }
    }

    private fun initMaps() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getExtras() {
        val extras = intent.extras
        extras?.apply {
            originAirport = getParcelable(ARG_ORG_AIRPORT) as Airport
            destinationAirport = getParcelable(ARG_DEST_AIRPORT) as Airport
        }
    }

    private fun initToolbar() {
        val journeyTitle = String.format(
                getString(R.string.flights_toolbar_title),
                originAirport.airportCode,
                destinationAirport.airportCode)
        translucentToolbar.title = journeyTitle
        translucentToolbar.setNavigationIcon(R.drawable.arrow_left)
        translucentToolbar.setNavigationOnClickListener{ onBackPressed() }
    }

    private fun getFlights() {
        val date = Date()
        val sharedPreferences = app.sharedPreferences
        val accessToken = sharedPreferences.getValue(OAuthActivity.OATUH_TOKEN)

        presenter.onViewReady(accessToken!!,
                originAirport.airportCode,
                destinationAirport.airportCode,
                date.today())
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val originCoordinate = originAirport.position.coordinate
        val origin = LatLng(originCoordinate.latitude, originCoordinate.longitude)
        googleMap?.addMarker(MarkerOptions().position(origin).title(originAirport.airportCode))

        val destinationCoordinate = destinationAirport.position.coordinate
        val destination = LatLng(destinationCoordinate.latitude, originCoordinate.longitude)
        googleMap?.addMarker(MarkerOptions().position(destination).title(destinationAirport.airportCode))

        googleMap?.addPolyline(PolylineOptions()
                        .clickable(false)
                        .add(origin, destination))!!

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(origin))
    }

    override fun showFlights(scheduleList: List<Schedule>) {
        bottomSheetRecyclerView.show()
        flightsError.hide()

        val flightsListAdapter = bottomSheetRecyclerView.adapter as FlightsListAdapter
        flightsListAdapter.addFlights(scheduleList)
    }

    override fun showLoading() = flightsLoading.show()

    override fun hideLoading() = flightsLoading.hide()

    override fun showError(throwable: Throwable) {
        bottomSheetRecyclerView.hide()
        flightsError.show()
        flightsError.text = getString(R.string.flight_not_found_error)
    }

    override fun initializeInjector() {
        component.inject(this@FlightsMapListActivity)
    }

    override fun initializePresenter() {
        presenter.setView(this@FlightsMapListActivity)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_flights_list
}