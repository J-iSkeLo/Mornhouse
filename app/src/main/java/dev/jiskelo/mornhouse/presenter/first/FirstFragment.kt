package dev.jiskelo.mornhouse.presenter.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.jiskelo.mornhouse.R
import dev.jiskelo.mornhouse.adapters.NumberAdapter
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import dev.jiskelo.mornhouse.databinding.FragmentFirstBinding
import dev.jiskelo.mornhouse.util.Constants.KEY_BUNDLE_NUMBER
import dev.jiskelo.mornhouse.util.entityToJson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment(), NumberAdapter.ClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FirstViewModel>()
    private lateinit var adapter: NumberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NumberAdapter(this)
        lifecycleScope.launchWhenStarted {
            viewModel.fullNumbersEntity().collect(::initRecyclerNumbers)
        }

        binding.btnGetFacts.setOnClickListener {
            if (binding.textFieldNumber.text.isNotEmpty()) {
                val numberString = binding.textFieldNumber.text.toString()
                viewModel.getNumbersDescription(numberString) {
                    startSecondFragment(it)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.type_number), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGetRandomFacts.setOnClickListener {
            viewModel.getRandomNumbersDescription {
                startSecondFragment(it)
            }
        }
    }

    private fun initRecyclerNumbers(numbers :List<NumberEntity>) {
        binding.recyclerHistory.adapter = adapter
        adapter.updateData(numbers.reversed())
    }

    override fun clickItem(numberEntity: NumberEntity) {
        startSecondFragment(numberEntity)
    }

    private fun startSecondFragment(numberEntity: NumberEntity) {
        val bundle = bundleOf(KEY_BUNDLE_NUMBER to numberEntity.entityToJson())
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}