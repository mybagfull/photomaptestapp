package com.denishrynkevich.photomaptestapp.ui.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.denishrynkevich.photomaptestapp.DaggerApp
import com.denishrynkevich.photomaptestapp.R
import com.denishrynkevich.photomaptestapp.databinding.FragmentMapBinding
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelFactory
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class MapFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MapViewModel by viewModels { factory }
    private var _binding: FragmentMapBinding? = null
    private lateinit var mMap: GoogleMap
    private val listImageData: MutableList<ImageOutData> = mutableListOf()


    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as DaggerApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImageList()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.imageListLiveData.observe(viewLifecycleOwner) {
            it.map { item -> listImageData.add(item) }
        }
        setupMaps()
    }

    private fun setupMaps() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        for (i in listImageData) {
            val location = LatLng(i.lng, i.lat)
            mMap.addMarker(MarkerOptions().position(location).title(i.id.toString()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}