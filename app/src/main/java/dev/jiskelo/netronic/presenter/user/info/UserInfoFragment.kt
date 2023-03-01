package dev.jiskelo.netronic.presenter.user.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.databinding.FragmentUserInfoBinding
import dev.jiskelo.netronic.util.convertDate
import dev.jiskelo.netronic.util.toast

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::listenState)
        }

        viewModel.checkArguments(arguments)
    }

    private fun listenState(state : StateUserInfo) {
        when (state) {
            is StateUserInfo.Success -> initDataInView(state.userEntity)
            is StateUserInfo.Error -> {
                state.message.toast(requireContext())
                findNavController().navigateUp()
            }
            else -> Unit
        }
    }

    private fun initDataInView(userEntity: UserEntity) {
        with(binding) {
            Glide.with(requireContext())
                .load(userEntity.image)
                .into(image)

            name.text = userEntity.name
            gender.text = userEntity.gender
            dateOfBirthday.text = userEntity.dateOfBirthday.convertDate()
            email.text = userEntity.email
            phone.text = userEntity.phone
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}