package by.hometrainng.databasehw4.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.hometrainng.databasehw4.adapter.ItemAdapter
import by.hometrainng.databasehw4.appDatabase
import by.hometrainng.databasehw4.database.UserDao
import by.hometrainng.databasehw4.databinding.FragmentListBinding
import by.hometrainng.databasehw4.dialog.FragmentEditDialog

class FragmentList: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        ItemAdapter(requireContext(), {
            showEditCustomDialog(it.id)
        }) {
            showDeleteDialog(it.id)
        }
    }

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
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        with(binding) {
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), 1))
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.submitList(userDao.getUsers())

            swipeLayout.setOnRefreshListener {
                adapter.submitList(userDao.getUsers())
                swipeLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showEditCustomDialog(id: Long) {
        FragmentEditDialog.getInstance(id)
            .show(childFragmentManager, null)
        adapter.submitList(userDao.getUsers())
    }

    private fun showDeleteDialog(id: Long) {
        AlertDialog.Builder(requireContext())
            .setTitle(DELETE_DIALOG_TITLE)
            .setMessage(DELETE_DIALOG_MESSAGE)
            .setPositiveButton(YES_BUTTON) { _, _ ->
                val user = userDao.getUserById(id)
                userDao.delete(user)
                adapter.submitList(userDao.getUsers())
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    companion object {
        const val DELETE_DIALOG_TITLE = "Delete User"
        const val DELETE_DIALOG_MESSAGE = "Do you really want to delete this User?"
        const val YES_BUTTON = "YES"


    }
}
