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
        var uname = username
        val regex = Regex("^(.+)@(.+)\$")
        if (username.matches(regex).not()){
            uname = "$username@test.com"
        }
        viewModelScope.launch {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(uname, password)
                .addOnCompleteListener {
                    if (it.isComplete){
                        if (FirebaseAuth.getInstance().currentUser != null)
                            loginResponse.postValue(LoginResponse(true, "Test User Register"))
                        else
                            loginResponse.postValue(LoginResponse(false, "Something went wrong"))
                    }
                    else{
                        loginResponse.postValue(LoginResponse(false, "Something went wrong"))
                    }
                }
        }
    }
    fun signInUser(username: String, password: String) {
        var uname = username
        val regex = Regex("^(.+)@(.+)\$")
        if (username.matches(regex).not()){
            uname = "$username@test.com"
        }
        viewModelScope.launch {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(uname, password)
                .addOnCompleteListener {
                    if (it.isComplete){
                        if (FirebaseAuth.getInstance().currentUser != null)
                            loginResponse.postValue(LoginResponse(true, "Test User Logged In"))
                        else
                            loginResponse.postValue(LoginResponse(false, "Something went wrong"))
                    }
                    else{
                        loginResponse.postValue(LoginResponse(false, "Something went wrong"))
                    }
                }
        }
    }


}