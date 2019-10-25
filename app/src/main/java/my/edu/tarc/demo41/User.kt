package my.edu.tarc.demo41

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user")
data class User(@PrimaryKey val contact: String, val name: String) {

}

