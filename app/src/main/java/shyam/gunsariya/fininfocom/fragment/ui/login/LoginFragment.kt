package shyam.gunsariya.fininfocom.fragment.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel
import shyam.gunsariya.fininfocom.R
import shyam.gunsariya.fininfocom.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
//                loginViewModel.loginDataChanged(
//                    usernameEditText.text.toString(),
//                    passwordEditText.text.toString()
//                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                loginViewModel.login(
//                    usernameEditText.text.toString(),
//                    passwordEditText.text.toString()
//                )
            }
            false
        }

        registerButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.registerUser(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.signInUser(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        viewModel.loginResponse.observe(viewLifecycleOwner){
            if (it.status){
                updateLogin(it.message)
            }else{
                showLoginFailed(it.message)
            }
        }
    }

    private fun updateLogin(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        val user = FirebaseAuth.getInstance().currentUser
        if (user!= null){
            findNavController().navigate(R.id.listFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}