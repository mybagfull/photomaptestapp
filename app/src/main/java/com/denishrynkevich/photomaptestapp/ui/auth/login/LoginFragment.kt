package com.denishrynkevich.photomaptestapp.ui.auth.login

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.denishrynkevich.photomaptestapp.DaggerApp
import com.denishrynkevich.photomaptestapp.MainActivity
/*
import com.denishrynkevich.photomaptestapp.DaggerApp
*/
import com.denishrynkevich.photomaptestapp.databinding.FragmentLoginBinding
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelFactory
import com.denishrynkevich.photomaptestapp.domain.model.UserTokenData
import com.denishrynkevich.photomaptestapp.ui.auth.AuthViewModel
import com.denishrynkevich.photomaptestapp.ui.token.TokenViewModel
/*
import com.denishrynkevich.photomaptestappk.di.viewModel.ViewModelFactory
*/
import com.denishrynkevich.photomaptestapp.utils.ApiResponse
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val authViewModel: AuthViewModel by viewModels { factory }
    private val tokenViewModel: TokenViewModel by viewModels { factory }
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as DaggerApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthViewModel()
        observeTokenViewModel()
        binding.buttonLogin.setOnClickListener {
            authViewModel.signIn(getUserName(), getPassword())
        }
    }

    private fun isSuccess(userTokenData: UserTokenData) {
        tokenViewModel.setTokens(userTokenData.token)
    }

    private fun observeTokenViewModel() {
        tokenViewModel.userIsAuthorizedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                navigateToMainActivity()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    private fun observeAuthViewModel() {
        authViewModel.userVerifiedLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    isSuccess(it.data.data)
                    loading(false)
                }

                is ApiResponse.Failure -> {
                    showErrorToast()
                    loading(false)
                }

                is ApiResponse.Loading -> loading(true)
            }
        }

    }

    private fun showErrorToast() {
        Toast.makeText(
            requireActivity(), "Check if your login and password are correct.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loading(isLoad: Boolean) {
        binding.loading.isVisible = isLoad
    }

    private fun getPassword() = binding.password.text.toString()

    private fun getUserName() = binding.login.text.toString()


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}