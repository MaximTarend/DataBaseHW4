package by.hometrainng.databasehw4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.hometrainng.databasehw4.adapter.ItemAdapter
import by.hometrainng.databasehw4.databinding.FragmentListBinding
import by.hometrainng.databasehw4.model.ListElement

class FragmentList: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        ItemAdapter(requireContext())
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

            val list = List(20) {
                ListElement(1, "Maxik", "Torr")
            }
        val layoutManager = LinearLayoutManager(view.context)
        with(binding) {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.submitList(list)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
