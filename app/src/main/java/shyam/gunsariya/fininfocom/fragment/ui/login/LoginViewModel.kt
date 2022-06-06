package shyam.gunsariya.fininfocom.fragment.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import shyam.gunsariya.fininfocom.model.LoginResponse

class LoginViewModel(): ViewModel() {

    val loginResponse : MutableLiveData<LoginResponse> = MutableLiveData()

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isComplete){
                        loginResponse.postValue(LoginResponse(true, "Test User Register"))
                    }
                    else{
                        loginResponse.postValue(LoginResponse(true, "Something went wrong"))
                    }
                }
        }
    }
    fun signInUser(username: String, password: String) {
        viewModelScope.launch {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isComplete){
                        loginResponse.postValue(LoginResponse(true, "Test User Logged In"))
                    }
                    else{
                        loginResponse.postValue(LoginResponse(true, "Something went wrong"))
                    }
                }
        }
    }


}