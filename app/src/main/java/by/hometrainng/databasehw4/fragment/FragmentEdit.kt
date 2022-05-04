package by.hometrainng.databasehw4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.hometrainng.databasehw4.appDatabase
import by.hometrainng.databasehw4.databinding.FragmentEditDatabaseBinding
import by.hometrainng.databasehw4.extentions.getTextOrSetError
import by.hometrainng.databasehw4.model.User

class FragmentEdit: Fragment() {

    private var _binding: FragmentEditDatabaseBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val userDao by lazy {
        requireContext()
            .appDatabase
            .userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditDatabaseBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            addButton.setOnClickListener {
                val firstName = firstNameContainer.getTextOrSetError()
                val lastName = lastNameContainer.getTextOrSetError()

                if (firstName == null || lastName == null) return@setOnClickListener

                userDao.insert(User(firstName = firstName, lastName = lastName))

                firstNameContainer.error = null
                lastNameContainer.error = null

                editFirstName.text?.clear()
                editLastName.text?.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}