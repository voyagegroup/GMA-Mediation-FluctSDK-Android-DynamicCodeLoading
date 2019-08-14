package jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {

    companion object {

        private const val INTERSTITIAL_MODULE = "interstitial"

    }

    private lateinit var mgr: SplitInstallManager

    private val listener = SplitInstallStateUpdatedListener { state ->
        refreshStatus()
    }

    private val loadInterstitial by lazy { findViewById<Button>(R.id.load_interstitial) }
    private val launchInterstitial by lazy { findViewById<Button>(R.id.launch_interstitial) }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mgr = SplitInstallManagerFactory.create(this)
        mgr.registerListener(listener)

        loadInterstitial.setOnClickListener {
            requestInterstitialModule()
        }

        launchInterstitial.setOnClickListener {
            Intent(this, InterstitialActivityAlias::class.java)
                .let { startActivity(it) }
        }

        refreshStatus()
    }

    private fun isInstalled(module: String): Boolean {
        return mgr.installedModules.contains(module)
    }

    private fun refreshStatus() {
        loadInterstitial.isEnabled = !isInstalled(INTERSTITIAL_MODULE)
        launchInterstitial.isEnabled = !loadInterstitial.isEnabled
    }

    private fun requestInterstitialModule() {
        mgr.startInstall(
            SplitInstallRequest.newBuilder()
                .addModule(INTERSTITIAL_MODULE)
                .build()
        )
    }

}
