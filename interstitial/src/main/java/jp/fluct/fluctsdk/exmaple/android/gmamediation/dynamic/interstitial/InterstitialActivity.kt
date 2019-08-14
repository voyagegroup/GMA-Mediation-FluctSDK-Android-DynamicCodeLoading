package jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.interstitial

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat

class InterstitialActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.interstitial.R.layout.interstitial_activity)
    }

}
