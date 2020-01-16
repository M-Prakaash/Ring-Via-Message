package com.example.helloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class SMS_Reciever : BroadcastReceiver() {
    private var audioManager: AudioManager? = null

    override fun onReceive(context: Context, intent: Intent) {


        val extras=intent.extras

        if(extras!=null) {

            val sms = extras.get("pdus") as Array<Any>

            for (i in sms.indices){
                val format = extras.getString("format")
                val smsMessage=  if(Build.VERSION.SDK_INT  >=Build.VERSION_CODES.M){
                    SmsMessage.createFromPdu(sms[i] as ByteArray,format)
                }
                else{
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                val sms_content  = smsMessage.messageBody.toString()
                if (sms_content.contains("Ring")  ) {
                    Toast.makeText(context, "Changing to Normal Mode", Toast.LENGTH_SHORT).show()
                    audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    audioManager!!.setRingerMode(AudioManager.RINGER_MODE_NORMAL)

                }
                else if(sms_content.contains("Vibrate"))
                {
                    Toast.makeText(context, "Changing to Vibrate Mode", Toast.LENGTH_SHORT).show()
                    audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    audioManager!!.setRingerMode(AudioManager.RINGER_MODE_VIBRATE)
                }
                else if(sms_content.contains("Silent"))
                {
                    Toast.makeText(context, "Changing to Vibrate Mode", Toast.LENGTH_SHORT).show()
                    audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    audioManager!!.setRingerMode(AudioManager.RINGER_MODE_SILENT)
                }
            }
        }
    }
}
