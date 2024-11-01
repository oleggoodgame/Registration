package com.example.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.registration.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button2.setOnClickListener{
            val nav = findNavController()
            nav.navigate(R.id.action_mainFragment_to_informationFragment)
        }
        binding.button3.setOnClickListener{
            val nav = findNavController()
            nav.navigate(R.id.action_mainFragment_to_changeFragment)
        }
        binding.button4.setOnClickListener{
            val nav = FullSizeFragmentDialog()
            nav.show(parentFragmentManager, "FullSizeFragmentDialog")

        }
        val userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            // Оновлюємо TextView при зміні даних
            binding.textView.text = user.toString()
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
