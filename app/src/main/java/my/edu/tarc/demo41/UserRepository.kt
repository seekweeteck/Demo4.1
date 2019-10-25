package my.edu.tarc.demo41

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    //Room execute all queries on a seperate thread.

    val allUsers: LiveData<List<User>> = userDao.getAllUser()

    suspend fun insert(user: User){
        userDao.insert(user)
    }
}