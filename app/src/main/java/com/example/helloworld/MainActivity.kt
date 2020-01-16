package com.example.helloworld

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kotlinpermissions.KotlinPermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        KotlinPermissions.with(this)
            .permissions(Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_NOTIFICATION_POLICY)
            .onAccepted { permissions ->
                Toast.makeText(applicationContext,"Send and Recieve SMS permission granted",Toast.LENGTH_SHORT).show()
            }
            .onDenied { permissions ->

            }
            .onForeverDenied { permissions ->

            }
            .ask()
    }
}
