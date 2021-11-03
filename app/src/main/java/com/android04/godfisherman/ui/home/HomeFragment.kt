package com.android04.godfisherman.ui.home

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.android04.godfisherman.R
import com.android04.godfisherman.databinding.FragmentHomeBinding
import com.android04.godfisherman.ui.base.BaseFragment
import com.android04.godfisherman.utils.SharedPreferenceManager
import com.android04.godfisherman.utils.isGrantedLocationPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateLocation()
    }


    private fun updateLocation() {
        if (isGrantedLocationPermission(requireContext())) {
            viewModel.updateLocation()
            // demo 용 저장된 위치 표시하는 Toast
            Toast.makeText(
                requireContext(),
                SharedPreferenceManager(requireContext()).getString(SharedPreferenceManager.PREF_LOCATION),
                Toast.LENGTH_SHORT
            ).show()

        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 2
    }
}
