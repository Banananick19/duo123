package banana.duo.duoKotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import banana.duo.duoKotlin.extensions.default
import com.example.clientduoeliteversion.viewmodels.ClientState

class ClientViewModel(): ViewModel() {
    val clientState: MutableLiveData<ClientState> = MutableLiveData<ClientState>().default(initialValue = ClientState.DefaultClientState())
}