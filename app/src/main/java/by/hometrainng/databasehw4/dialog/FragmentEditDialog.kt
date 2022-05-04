package by.hometrainng.databasehw4.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.hometrainng.databasehw4.appDatabase
import by.hometrainng.databasehw4.databinding.FragmentEditDialogBinding
import by.hometrainng.databasehw4.extentions.getTextOrSetError

class FragmentEditDialog private constructor(): DialogFragment() {

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
        val id = requireArguments().getLong(KEY)
        val user = userDao.getUserById(id)
        return FragmentEditDialogBinding.inflate(inflater, container, false)
            .also { binding ->
                with(binding) {
                    editButton.setOnClickListener {
                        val firstName = firstNameContainer.getTextOrSetError()
                        val lastName = lastNameContainer.getTextOrSetError()

                        if (firstName == null || lastName == null) return@setOnClickListener

                        user.firstName = firstName
                        user.lastName = lastName

                        userDao.update(user)

                        firstNameContainer.error = null
                        lastNameContainer.error = null

                        editFirstName.text?.clear()
                        editLastName.text?.clear()

                        dismiss()
                    }
                }
            }.root
    }

    companion object {
        private const val KEY = "key"

        fun getInstance(key: Long) = FragmentEditDialog().apply {
            arguments = bundleOf(KEY to key)

        }
    }
}