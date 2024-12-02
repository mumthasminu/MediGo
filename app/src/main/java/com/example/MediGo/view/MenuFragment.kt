package com.example.MediGo.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentHomeBinding
import com.example.MediGo.databinding.FragmentLoginBinding
import com.example.MediGo.model.Local.Data
import com.example.MediGo.model.Local.ServiceCategory
import com.example.MediGo.model.Local.User
import com.example.MediGo.viewModel.ServiceDetailsViewModel
import com.example.MediGo.viewModel.UserViewModel
import com.google.gson.Gson
import java.io.InputStream

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val data = parseJsonToData(requireContext(), "medigo_dataset.json")

        // Set up RecyclerView for doctors
        binding.serviceRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        //binding.serviceRecycler.adapter = DoctorAdapter(data.doctors)
        //binding.serviceRecycler.adapter = ServiceAdapter(data.service_categories)
        val ServiceAdapter = ServiceAdapter(data.service_categories) { serviceCategory ->
            onServiceCategoryClick(serviceCategory)

        }
        binding.serviceRecycler.adapter=ServiceAdapter
//        binding.serviceRecycler.adapter = ServiceAdapter(data.service_categories) { serviceCategory ->
//            // Navigate to the details page, passing the service category data
//
//        }
//
        return binding.root
    }

    private fun parseJsonToData(context: Context, fileName: String): Data {
        val jsonString = readJsonFromAssets(context, fileName)
        return Gson().fromJson(jsonString, Data::class.java)
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.medigo_dataset)
        return inputStream.bufferedReader().use { it.readText() }
    }
    private fun onServiceCategoryClick(serviceCategory: ServiceCategory) {

        val viewModel = ViewModelProvider(requireActivity()).get(ServiceDetailsViewModel::class.java)
        viewModel.setServiceCategory(serviceCategory)

        findNavController().navigate(R.id.action_homeFragment_to_serviceDetailsFragment)
    }

}
