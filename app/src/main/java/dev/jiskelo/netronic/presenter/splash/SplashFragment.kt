package dev.jiskelo.netronic.presenter.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.jiskelo.netronic.R
import dev.jiskelo.netronic.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.splashState.collect(::splashState)
        }
    }

    private fun splashState(state :SplashState) {
        when (state) {
            is SplashState.Loading ->  binding.startAnimationProgress()
            is SplashState.Success -> {
                findNavController().navigate(R.id.action_splashFragment_to_usersFragment)
            }
            else -> Unit
        }
    }

    private fun FragmentSplashBinding.startAnimationProgress() {
        progressBar.max = 100

        ObjectAnimator.ofInt(progressBar, "progress", 100)
            .setDuration(2000)
            .start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}