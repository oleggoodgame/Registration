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
import com.example.registration.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.editTextTextI.setText(user?.name ?: "null")
            binding.editTextText2I.setText(user?.password ?: "null")
            binding.editTextText3I.setText(user?.number ?: "null")
            binding.editTextText4I.setText(user?.calendar ?: "null")

        })
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        binding.editTextText2I.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        binding.buttonI.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_informationFragment_to_changeFragment)
        }
        binding.button2I.setOnClickListener{
            val navController = findNavController()
            navController.popBackStack(R.id.mainFragment, false)
        }
        binding.imageButtonI.setOnClickListener {
            binding.imageButtonI.visibility = View.GONE
            binding.imageButton2I.visibility = View.VISIBLE
            binding.editTextText2I.inputType = InputType.TYPE_CLASS_TEXT
        }

        binding.imageButton2I.setOnClickListener {
            binding.imageButtonI.visibility = View.VISIBLE
            binding.imageButton2I.visibility = View.GONE
            binding.editTextText2I.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
