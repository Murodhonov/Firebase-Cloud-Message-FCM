package uz.ilhomjon.notificationfirebase5.rretrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import uz.ilhomjon.notificationfirebase5.models.MyResponse
import uz.ilhomjon.notificationfirebase5.models.Sender

interface ApiService {
    @Headers(
        "Content-type:application/json",
        "Authorization:key=AAAAWBTGETE:APA91bFPQA69cBSJzyUWg79FOK9Q4gEMPb58bGGG1hi4dOsUJVs8M1csQTVfv7N7Jekh3D60fjeggVqDs_PJRgZFUwINYUBYCnqiaPwrPWrRAAuVOkcaLQ0KiSpIszClDx1LDxTz8o0r"
    )
    @POST("fcm/send")
    fun sendNotification(@Body sender: Sender): Call<MyResponse>
}