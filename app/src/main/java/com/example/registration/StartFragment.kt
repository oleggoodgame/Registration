package com.example.registration

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.registration.databinding.FragmentStartBinding
import java.util.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    //ViewModelProvider(requireActivity()).get(UserViewModel::class.java) і by activityViewModels() обидва варіанти мають схожі цілі
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        // Отримуємо поточну дату
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //calendar — об'єкт класу Calendar, який містить поточний час і дату.
        //get(Calendar.YEAR) — отримує поточний рік.
        //get(Calendar.MONTH) — отримує поточний місяць (нумерація починається з 0, тому січень — це 0, лютий — 1 тощо).
        //get(Calendar.DAY_OF_MONTH) — отримує поточний день місяця
        binding.editTextText4.setOnClickListener {

            // Відкриваємо DatePickerDialog
            val datePickerDialog = DatePickerDialog(requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val date = "$selectedDay.${selectedMonth + 1}.$selectedYear"
                    binding.editTextText4.setText(date)
                }, year, month, day)
//            DatePickerDialog — це діалогове вікно, яке дозволяє користувачу вибрати дату.
//            requireContext() — передається контекст фрагмента, необхідний для відображення діалогу.
//            Другий параметр — це функція-обробник, яка спрацьовує після вибору дати.
//            selectedYear — обраний рік.
//            selectedMonth — обраний місяць (від 0 до 11, тому до нього додається 1, щоб відобразити правильно).
//            selectedDay — обраний день місяця.
            datePickerDialog.show()
        }
        binding.button.setOnClickListener{
            if(binding.editTextText.text.isEmpty())
                Toast.makeText(requireContext(), "Ім'я заповніть", Toast.LENGTH_SHORT).show()
            else if(binding.editTextText2.text.isEmpty())
                Toast.makeText(requireContext(), "Пароль заповніть", Toast.LENGTH_SHORT).show()
            else if(binding.editTextText3.text.isEmpty())
            Toast.makeText(requireContext(), "Номер телефону заповніть", Toast.LENGTH_SHORT).show()
            else if(binding.editTextText4.text.isEmpty())
                Toast.makeText(requireContext(), "Календар заповніть", Toast.LENGTH_SHORT).show()
            else{
                val user = User(
                    name = binding.editTextText.text.toString(),
                    password = binding.editTextText2.text.toString(),
                    number = binding.editTextText3.text.toString(),
                    calendar = binding.editTextText4.text.toString()
                )

// Отримуємо ViewModel і зберігаємо дані
                val userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
                userViewModel.setUser(user)
//                //Ось тут нижче далі
                val navController = findNavController()
                navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
