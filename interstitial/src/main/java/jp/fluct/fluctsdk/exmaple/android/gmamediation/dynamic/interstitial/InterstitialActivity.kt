package jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.interstitial

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.IInterstitialAdLogic
import jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.MainActivity
import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.primaryConstructor

class InterstitialActivity : AppCompatActivity() {

    companion object {

        private const val ADS_MODULE = "ads"

    }

    private lateinit var mgr: SplitInstallManager

    private val show by lazy { findViewById<Button>(R.id.loadToShow) }

    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.INSTALLED -> kickAdLogic()
            else -> TODO()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interstitial_activity)

        mgr = SplitInstallManagerFactory.create(this)
        mgr.registerListener(listener)

        show.setOnClickListener {
            if (isInstalled(ADS_MODULE)) {
                kickAdLogic()
            } else {
                requestAdsModule()
            }
        }
    }

    private fun kickAdLogic() {
        (Class.forName("jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.ads.MyInterstitialAdLogic")
            .kotlin.objectInstance as IInterstitialAdLogic).loadToShow(this)
    }

    private fun isInstalled(module: String): Boolean {
        return mgr.installedModules.contains(module)
    }

    private fun requestAdsModule() {
        mgr.startInstall(
            SplitInstallRequest.newBuilder()
                .addModule(ADS_MODULE)
                .build()
        )
    }

}
