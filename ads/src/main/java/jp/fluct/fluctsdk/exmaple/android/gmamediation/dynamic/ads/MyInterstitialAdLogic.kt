package jp.fluct.fluctsdk.exmaple.android.gmamediation.dynamic.ads

import android.app.Activity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class MyInterstitialAdLogic {

    companion object {

        private const val GMA_AD_UNIT_ID
                = "/62532913/a_fluct.test_1024x768_chocovayashitest.interstitial_11940"

    }

    fun loadToShow(activity: Activity) {
        InterstitialAd(activity)
            .apply {
                adUnitId = GMA_AD_UNIT_ID
                adListener = object : AdListener() {

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        show()
                    }

                }
            }.let { it.loadAd(AdRequest.Builder().build()) }
    }

}
