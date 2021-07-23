package com.celer.samplefingerprintsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.celer.sdkfingerprint.Fingerprint

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fingerprint().generateFingerprint(this, false) {id->
            Log.e("TAG", "Id do fingerPrint")
        }
    }
}
