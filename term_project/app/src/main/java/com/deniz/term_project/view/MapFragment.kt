package com.deniz.term_project.view


import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.deniz.term_project.R
import com.deniz.term_project.model.*
import com.deniz.term_project.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.*


class MapFragment : Fragment()  {

    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var viewModel: MapViewModel
    var distance_message = MutableLiveData<String>()
    var check = 0
    var match = 0 //algortihm'e istek atılınca
    var select = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress)
        progressBar.visibility = View.VISIBLE



        viewModel.getEarthquake()
        observeAlgorithmData(view)


        view.findViewById<Button>(R.id.setCompletedBtn).setOnClickListener {
            val building = BuildingDataHolder.getBuilding()
            if(building != null){
                val building_id = building.building_id.toString()
                val team_id = UserDataHolder.getUser()!!.team_id!!.toString()
                viewModel.complete(building_id,team_id)
                observeResponseData(view)
                Thread.sleep(10)
                select = 0
                view.findViewById<LinearLayout>(R.id.layout).visibility = View.GONE
                val group_id = BuildingDataHolder.getBuilding()!!.group_id
                if (group_id != null ){
                    val lat = UserDataHolder.getUser()!!.lat.toString()
                    val lng = UserDataHolder.getUser()!!.lng.toString()
                    viewModel.getBuilding(lat, lng, team_id, group_id.toString())
                    observeData(view)
                }
                else{
                    match = 0
                }


            }
        }

        val callback = OnMapReadyCallback { googleMap ->

            locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationListener = object : LocationListener{
                override fun onLocationChanged(location: Location) {
                    //lokasyon konum değişince
                    Log.d("LocationChanged", "Lat: ${location.latitude}")
                    Log.d("LocationChanged", "Long: ${location.longitude}")
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    UserDataHolder.setLatLng(location.latitude.toString(), location.longitude.toString())
                    /*viewModel.updateUser(location.latitude.toString(), location.longitude.toString(),UserDataHolder.getUser()!!.user_id.toString() )
                    observeUpdateUser(view)*/
                    val lat = location.latitude.toString()
                    val lng = location.longitude.toString()
                    val merkezlat = AlgorithmDataHolder.getData()!!.merkez_lat
                    val merkezlng = AlgorithmDataHolder.getData()!!.merkez_lng
                    val distance = AlgorithmDataHolder.getData()!!.distance

                    if (merkezlng != null && merkezlat != null && distance != null && check == 0 && UserDataHolder.getUser()!!.user_type == 1) {
                        viewModel.distance(lat, lng, merkezlat, merkezlng, distance)
                        observeResponseData(view)
                    }
                    if(check == 1 && match == 0 && UserDataHolder.getUser()!!.user_type == 1){

                        var id = UserDataHolder.getUser()!!.team_id!!
                        if(id != null ){
                            progressBar.visibility = View.VISIBLE
                            match = 1
                            viewModel.refreshData(lat , lng , id.toString())
                            observeData(view)


                        }
                    }
                    googleMap.clear()
                    googleMap.addMarker(MarkerOptions().position(currentLocation).title("current"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15f))
                    progressBar.visibility = View.GONE
                    val building = BuildingDataHolder.getBuilding()
                    if(building != null && select == 0){
                        val buildingLng = building.lng!!.toDouble()
                        val buildingLat = building.lat!!.toDouble()
                        val buildingLocation = LatLng(buildingLat, buildingLng)
                        googleMap.addMarker(MarkerOptions().position(buildingLocation).title("destination location"))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(buildingLocation,15f))
                        select = 1
                    }

                }

            }



            if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                //izin yok
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            }else{
                //izin var
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f, locationListener)

            }

        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            if(grantResults.size > 0 ){
                if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,30,20f, locationListener)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun observeData(view : View) {
        viewModel.building.observe(viewLifecycleOwner) { building ->
            view.findViewById<LinearLayout>(R.id.layout).visibility = View.VISIBLE
            BuildingDataHolder.setBuilding(building)
            Log.d("LocationChanged", "Lat: ${building}")
            view.findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
            if (building != null) {
                view.findViewById<TextView>(R.id.tvBuildingId).text = building.building_id.toString()
                view.findViewById<TextView>(R.id.tvAdress).text = building.address
                view.findViewById<TextView>(R.id.tvTime).text = building.time.toString()
                view.findViewById<TextView>(R.id.tvPersoncount).text = building.person_count.toString()
                view.findViewById<TextView>(R.id.tvCreated).text = building.created_at
                view.findViewById<TextView>(R.id.tvCount).text = building.count.toString()
                view.findViewById<TextView>(R.id.tvDestroyedRoad).text = building.road
            } else {
                Toast.makeText(requireContext(), "building is null", Toast.LENGTH_SHORT).show()
                match = 0
            }
        }
    }
    private fun observeUpdateUser(view : View)  {
        viewModel.Response.observe(viewLifecycleOwner) { response ->
            if (response != null) {

                Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(requireContext(), "hata", Toast.LENGTH_SHORT).show()

            }
        }

    }
    private fun observeResponseData(view : View)  {
        viewModel.Response.observe(viewLifecycleOwner) { response ->
            if (response != null) {

                Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                check = 1
                if(response.message.toString() == "hata"){
                    Toast.makeText(requireContext(), "hata", Toast.LENGTH_SHORT).show()
                    check = 0
                }

            } else {
                Toast.makeText(requireContext(), "hata", Toast.LENGTH_SHORT).show()


            }
        }

    }
    private fun observeAlgorithmData(view: View){
        viewModel.algorithm.observe(viewLifecycleOwner){ algorithm ->
            AlgorithmDataHolder.setData(algorithm)
            Toast.makeText(requireContext(), algorithm.merkez_lat, Toast.LENGTH_LONG).show()
        }
    }




}