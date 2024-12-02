package com.example.MediGo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentDetailsBinding
import com.example.MediGo.model.Local.ServiceCategory
import com.example.MediGo.viewModel.ServiceDetailsViewModel

class ServiceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: ServiceDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ServiceDetailsViewModel::class.java)

        viewModel.serviceCategory.observe(viewLifecycleOwner) { category ->
            updateUiBasedOnCategory(category)
        }
    }

    private fun updateUiBasedOnCategory(category: ServiceCategory?) {
        if (category != null) {
            when (category.id) {
                1 -> {
                    // Show doctor details layout
                    binding.layoutDoctorDetails.visibility = View.VISIBLE
                    binding.layoutDiagnosticsDetails.visibility = View.GONE
                    binding.layoutHealthPackagesDetails.visibility = View.GONE

                    // Populate data for doctors
                    // e.g., load a list of available doctors (implement logic here)
                }
                2 -> {
                    // Show diagnostics details layout
                    binding.layoutDoctorDetails.visibility = View.GONE
                    binding.layoutDiagnosticsDetails.visibility = View.VISIBLE
                    binding.layoutHealthPackagesDetails.visibility = View.GONE

                    // Populate data for diagnostics (implement logic here)
                }
                3 -> {
                    // Show health packages details layout
                    binding.layoutDoctorDetails.visibility = View.GONE
                    binding.layoutDiagnosticsDetails.visibility = View.GONE
                    binding.layoutHealthPackagesDetails.visibility = View.VISIBLE

                    // Populate data for health packages (implement logic here)
                }
            }
        } else {
            // Handle the case where category is null (optional)
            // You could display a message or default layout
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}