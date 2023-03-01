package dev.jiskelo.netronic.presenter.users

import android.os.Bundle
import android.text.InputFilter
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
import dev.jiskelo.netronic.databinding.FragmentUsersBinding
import dev.jiskelo.netronic.presenter.MainActivity
import dev.jiskelo.netronic.presenter.MainActivity.Companion.hideKeyboard
import dev.jiskelo.netronic.structs.User
import dev.jiskelo.netronic.util.Constants.KEY_ID_USER
import dev.jiskelo.netronic.util.InputFilterMinMax
import dev.jiskelo.netronic.util.toast


@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.ClickListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UsersViewModel>()
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UsersAdapter(this, TypeAdapter.UserAdapter)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::listenState)
        }

        binding.initView()
    }

    private fun FragmentUsersBinding.initView() {
        textFieldNumber.filters = arrayOf<InputFilter>(InputFilterMinMax(0, 5000))

        btnGetUsers.setOnClickListener {
            val number = textFieldNumber.text.toString()
            if (number.isNotEmpty()) {
                textFieldNumber.text.clear()
                requireActivity().hideKeyboard()
                viewModel.getUsers(number.toInt())
            }
        }

        btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_historyFragment)
        }
    }

    private fun listenState(state : StateUsers) {
        when (state) {
            is StateUsers.Success -> initRecyclerUsers(state.users)
            is StateUsers.Error -> state.message.toast(requireContext())
            else -> Unit
        }
    }

    private fun initRecyclerUsers(users :List<User>) {
        binding.recycler.adapter = adapter
        adapter.updateUser(users)
    }

    override fun clickItem(id :String) {
        viewModel.clearState()

        findNavController().navigate(
            R.id.action_usersFragment_to_userInfoFragment,
            bundleOf(KEY_ID_USER to id)
        )
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}