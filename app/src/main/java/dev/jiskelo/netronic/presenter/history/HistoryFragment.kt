package dev.jiskelo.netronic.presenter.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.jiskelo.netronic.R
import dev.jiskelo.netronic.adapters.TypeAdapter
import dev.jiskelo.netronic.adapters.UsersAdapter
import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.databinding.FragmentHistoryBinding
import dev.jiskelo.netronic.util.Constants

@AndroidEntryPoint
class HistoryFragment :Fragment(), UsersAdapter.ClickListener {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HistoryViewModel>()
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UsersAdapter(this, TypeAdapter.UserEntityAdapter)

        lifecycleScope.launchWhenStarted {
            viewModel.users.collect(::initRecyclerUsers)
        }
    }

    private fun initRecyclerUsers(users :List<UserEntity>) {
        binding.recycler.adapter = adapter
        adapter.updateUserEntity(users)
    }

    override fun clickItem(id: String) {
        findNavController().navigate(
            R.id.action_historyFragment_to_userInfoFragment,
            bundleOf(Constants.KEY_ID_USER to id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}