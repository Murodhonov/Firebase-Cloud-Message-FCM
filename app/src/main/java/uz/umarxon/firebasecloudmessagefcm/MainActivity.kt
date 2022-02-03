package uz.umarxon.firebasecloudmessagefcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.ilhomjon.notificationfirebase5.models.Data
import uz.ilhomjon.notificationfirebase5.models.MyResponse
import uz.ilhomjon.notificationfirebase5.models.Sender
import uz.ilhomjon.notificationfirebase5.rretrofit.ApiClient
import uz.ilhomjon.notificationfirebase5.rretrofit.ApiService
import uz.umarxon.firebasecloudmessagefcm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var myToken = ""

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiClient.getRetrofit("https://fcm.googleapis.com/").create(ApiService::class.java)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task->
            if (!task.isSuccessful){
                Log.d(TAG, "onCreate: token falled")
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(TAG, token ?: "")
            myToken = token!!
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })

        binding.btn.setOnClickListener {
            apiService.sendNotification(
                Sender(
                    Data(
                        "Umarxondan",
                        R.drawable.ic_launcher_foreground,
                        binding.edt.text.toString(),
                        "New Message",
                        "Umarxonga"
                    ),
                    myToken
                )
            ).enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    if (response.isSuccessful){
                        Toast.makeText(this@MainActivity, "Succsefull", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}