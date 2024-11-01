package com.example.registration

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.registration.databinding.FragmentChangeBinding

class ChangeFragment : Fragment() {

    private var _binding: FragmentChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.editTextTextC.setText(user?.name ?: "null")
            binding.editTextText2C.setText(user?.password ?: "null")
            binding.editTextText3C.setText(user?.number ?: "null")
            binding.editTextText4C.setText(user?.calendar ?: "null")
        })
        _binding = FragmentChangeBinding.inflate(inflater, container, false)
        binding.imageButtonC.setOnClickListener {
            binding.imageButtonC.visibility = View.GONE
            binding.imageButton2C.visibility = View.VISIBLE
            binding.editTextText2C.inputType = InputType.TYPE_CLASS_TEXT
        }

        binding.imageButton2C.setOnClickListener {
            binding.imageButtonC.visibility = View.VISIBLE
            binding.imageButton2C.visibility = View.GONE
            binding.editTextText2C.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.button2C.setOnClickListener{
            val user = User(
                name = binding.editTextTextC.text.toString(),
                password = binding.editTextText2C.text.toString(),
                number = binding.editTextText3C.text.toString(),
                calendar = binding.editTextText4C.text.toString()
            )
            val userViewModels = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
            userViewModels.setUser(user)
            val navController = findNavController()
            navController.popBackStack(R.id.mainFragment, false)
        }
        binding.buttonC.setOnClickListener{
            val navController = findNavController()
            navController.popBackStack(R.id.mainFragment, false)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
