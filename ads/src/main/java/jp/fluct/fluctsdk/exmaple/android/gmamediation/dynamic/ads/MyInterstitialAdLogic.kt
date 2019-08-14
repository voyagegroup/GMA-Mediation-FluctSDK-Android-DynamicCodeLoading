package jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.ads

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.IInterstitialAdLogic

object MyInterstitialAdLogic : IInterstitialAdLogic{

    private const val GMA_AD_UNIT_ID
            = "/62532913/a_fluct.test_1024x768_chocovayashitest.interstitial_11940"

    override fun loadToShow(activity: Activity) {
        Toast.makeText(activity, "Loading...", Toast.LENGTH_SHORT).show()
        InterstitialAd(activity)
            .apply {
                adUnitId = GMA_AD_UNIT_ID
                adListener = object : AdListener() {

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        show()
                    }

                    override fun onAdFailedToLoad(p0: Int) {
                        super.onAdFailedToLoad(p0)
                        Toast.makeText(activity, "onAdFailedToLoad", Toast.LENGTH_LONG).show()
                    }

                }
            }.let { it.loadAd(AdRequest.Builder().build()) }
    }

}
