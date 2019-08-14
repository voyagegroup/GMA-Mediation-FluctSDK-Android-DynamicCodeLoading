# region FluctSDK動画リワード / インタースティシャル
-keep class jp.fluct.mediation.** { *; }

-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-dontwarn android.support.**

-keep class com.unity3d.ads.** { *; }

# Keep all classes in Unity Services package
-keep class com.unity3d.services.** {
   *;
}

-dontwarn com.google.ar.core.**

# For communication with AdColony's WebView
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep ADCNative class members unobfuscated
-keepclassmembers class com.adcolony.sdk.ADCNative** {
    *;
}

# For removing warnings due to lack of Multi-Window support
-dontwarn android.app.Activity
# endregion
