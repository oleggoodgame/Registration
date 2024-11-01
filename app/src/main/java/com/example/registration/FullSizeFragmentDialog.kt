package com.example.registration

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.registration.databinding.FragmentFullSizeDialogBinding
import com.google.android.material.snackbar.Snackbar

class FullSizeFragmentDialog : DialogFragment() {
    private var _binding: FragmentFullSizeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullSizeDialogBinding.inflate(inflater, container, false)

        binding.buttonAg.setOnClickListener {
            if (binding.checkBox.isChecked) {
                // Показуємо Toast
                Toast.makeText(requireContext(), "CheckBox активовано", Toast.LENGTH_SHORT).show()
            }
            if (binding.switch1.isChecked) {
                // Показуємо SnackBar з дією
                Snackbar.make(requireActivity().findViewById(android.R.id.content), "Switch активовано", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        // Дія при натисканні на кнопку "Undo" в SnackBar
                        binding.switch1.isChecked = false
                    }
                    .show()
            }
            dismiss()
        }
        binding.buttonDi.setOnClickListener{
            dismiss()
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialog)
        return dialog
    }

    override fun onStart() {
    super.onStart()

    // Розмір на весь екран
    dialog?.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
