package com.example.MediGo.view

import DoctorAdapter
import ServiceAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentDetailsBinding
import com.example.MediGo.model.Local.Data
import com.example.MediGo.model.Local.ServiceCategory
import com.example.MediGo.viewModel.ServiceDetailsViewModel
import com.google.gson.Gson
import java.io.InputStream

class MenuFragments : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: ServiceDetailsViewModel
    private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load JSON data (consider moving this to a separate class for data loading)
        data = parseJsonToData(requireContext(), "medigo_dataset.json")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ServiceDetailsViewModel::class.java)

        viewModel.serviceCategory.observe(viewLifecycleOwner) { category ->
            updateUiBasedOnCategory(category)
        }

        return binding.root
    }

    private fun updateUiBasedOnCategory(category: ServiceCategory?) {
        if (category != null) {
            when (category.id) {
                1 -> populateDoctorDetails()
                2 -> populateDiagnosticsDetails()
                3 -> populateHealthPackageDetails()
                else -> showError()
            }
        } else {
            // Handle the case where category is null (optional)
        }
    }

    private fun parseJsonToData(context: Context, fileName: String): Data {
        val jsonString = readJsonFromAssets(context, fileName)
        return Gson().fromJson(jsonString, Data::class.java)
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.medigo_dataset)
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun populateDoctorDetails() {
        binding.layoutDoctorDetails.visibility = View.VISIBLE
        binding.layoutDiagnosticsDetails.visibility = View.GONE
        binding.layoutHealthPackagesDetails.visibility = View.GONE

        // Populate doctor details (for simplicity, showing all doctors here)
        val doctors = data.doctors
        val doctorDetails = doctors.joinToString("\n") { doctor ->
            "Name: ${doctor.name}\nSpecialty: ${doctor.specialty}\nContact: ${doctor.contact}\n\n"
        }
        binding.doctorDetailsText.text = doctorDetails
    }

    private fun populateDiagnosticsDetails() {
        binding.layoutDoctorDetails.visibility = View.GONE
        binding.layoutDiagnosticsDetails.visibility = View.VISIBLE
        binding.layoutHealthPackagesDetails.visibility = View.GONE

        // Populate diagnostics details
        val diagnosticsInfo = "Diagnostics services will be added here."
        binding.diagnosticsDetailsText.text = diagnosticsInfo
    }

    private fun populateHealthPackageDetails() {
        binding.layoutDoctorDetails.visibility = View.GONE
        binding.layoutDiagnosticsDetails.visibility = View.GONE
        binding.layoutHealthPackagesDetails.visibility = View.VISIBLE

        // Populate health packages
        val healthPackages = data.health_packages
        val packageDetails = healthPackages.joinToString("\n") { packageItem ->
            "Name: ${packageItem.name}\nPrice: \$${packageItem.price}\nDetails: ${packageItem.details}\n\n"
        }
        binding.healthPackagesDetailsText.text = packageDetails
    }

    private fun showError() {
        binding.errorText.visibility = View.VISIBLE
    }
}