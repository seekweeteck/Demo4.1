package my.edu.tarc.demo41

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    //The ViewModel maintains a reference to the repository to get data
    private val repository: UserRepository

    //LiveData gives us updated words when they change.
    val allUsers: LiveData<List<User>>

    init{
        //Gets reference to UserDao from UserRoomDatabase to construct the correct UserRepository
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository((userDao))
        allUsers = repository.allUsers
    }

    //ViewModel uses a coroutine to perform long running operations
    fun insertUser(user: User) = viewModelScope.launch{
        repository.insert(user)
    }
}