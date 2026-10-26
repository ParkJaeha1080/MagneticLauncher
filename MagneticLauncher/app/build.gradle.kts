plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "dev.jaeha.magneticlauncher"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.jaeha.magneticlauncher"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "0.1-prototype"
    }
}
