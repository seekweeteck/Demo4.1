package my.edu.tarc.demo41

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UserListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer {
            // Update the cached copy of the users in the adapter
                users -> users?.let { adapter.setUsers(it) }
        })

        fab.setOnClickListener {
            val intent: Intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.let{
                val user = User(it.getStringExtra(AddActivity.EXTRA_NAME), it.getStringExtra(AddActivity.EXTRA_CONTACT))
                userViewModel.insertUser(user)
                Snackbar.make(findViewById(R.id.layout_main), R.string.record_saved, Snackbar.LENGTH_SHORT).show()
            }
        }else{
            Snackbar.make(findViewById(R.id.layout_main), R.string.error_record_not_saved, Snackbar.LENGTH_SHORT).show()
        }
    }
}
