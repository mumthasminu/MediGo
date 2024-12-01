package com.example.MediGo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.MediGo.R
import com.example.MediGo.databinding.FragmentLoginBinding
import com.example.MediGo.model.Local.User
import com.example.MediGo.viewModel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding // Assume you use ViewBinding or DataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnLogin.setOnClickListener {

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validate input fields
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit the click listener if fields are empty
            }

            // Proceed with login logic
            viewModel.loginUser(username, password).observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    // Navigate to home screen or show success message
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // Register new user and navigate to home
                    val newUser = User(username = username, password = password)
                    viewModel.signupUser(newUser)
                    Toast.makeText(context, "New user registered. Logging in...", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

//        binding.btnSignUp.setOnClickListener {
//            val username = binding.etUsername.text.toString()
//            val password = binding.etPassword.text.toString()
//            viewModel.addUser(username, password)
//            Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
//        }

        return binding.root
    }
}
