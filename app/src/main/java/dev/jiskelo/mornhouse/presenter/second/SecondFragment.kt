package dev.jiskelo.mornhouse.presenter.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.jiskelo.mornhouse.R
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import dev.jiskelo.mornhouse.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SecondViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberEntity = viewModel.checkArguments(arguments)

        if (numberEntity != null) {
            initDataInView(numberEntity)
        } else {
            moveBack()
        }
    }

    private fun moveBack() {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    private fun initDataInView(numberEntity: NumberEntity) {
        with(binding) {
            val numberString = getString(R.string.selected_number, numberEntity.number.toString())
            textSelectedNumber.text = numberString
            textFact.text = numberEntity.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}