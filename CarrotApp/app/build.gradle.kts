plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.carrotapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.carrotapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation("com.google.android.material:material:1.11.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.recyclerview:recyclerview:1.2.1")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.base)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}