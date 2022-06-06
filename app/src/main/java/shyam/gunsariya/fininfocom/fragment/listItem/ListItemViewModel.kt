package shyam.gunsariya.fininfocom.fragment.listItem

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import shyam.gunsariya.fininfocom.model.ListResponse

class ListItemViewModel: ViewModel() {

    val listItem: MutableLiveData<ArrayList<ListResponse>> = MutableLiveData()

    fun getItemList() {
        viewModelScope.launch {
            FirebaseDatabase.getInstance().reference
                .addChildEventListener(object : ChildEventListener{
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                        val list: ArrayList<ListResponse> = ArrayList()
                        snapshot.children.forEach {
                            val response = ListResponse(
                                it.child("age").value.toString().toInt(),
                                it.child("city").value.toString(),
                                it.child("name").value.toString()
                            )
                            list.add(response)
                        }
                        listItem.postValue(list)
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?,
                    ) {
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
        }
    }

}