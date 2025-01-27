package at.ac.htlleonding.oldtimer_application.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.htlleonding.oldtimer_application.data.model.OldTimer
import at.ac.htlleonding.oldtimer_application.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OldTimerViewModel : ViewModel() {

    private val _oldTimers = mutableStateListOf<OldTimer>()
    val oldTimers: List<OldTimer> get() = _oldTimers

    init {
        /*
        _oldTimers.addAll(listOf(
            OldTimer("1", "Bugatti", "Topolino", 1925),
            OldTimer("2", "Citron", "Topolino", 1922),
            OldTimer("3", "Fiat", "Topolino", 1947)
        ))*/
       getAllOldTimers()
    }

    fun addOldTimer(oldTimer: OldTimer){
        viewModelScope.launch {
            try {
                val response: OldTimer = RetrofitInstance.api.postOldTimer(oldTimer)
                Log.d(
                    "OldTimer",
                    "Name: ${response.name}, Model: ${response.model}, Year: ${response.year}"
                )
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


    fun getAllOldTimers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getOldTimers()
                    .sortedBy { it -> it.name }
                    .sortedBy { it -> it.model }
                    .sortedBy { it -> it.year }

                response.forEach { oldTimer ->
                    Log.d(
                        "OldTimer",
                        "Name: ${oldTimer.name}, Model: ${oldTimer.model}, Year: ${oldTimer.year}"
                    )
                }
                _oldTimers.addAll(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}