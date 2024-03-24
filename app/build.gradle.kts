plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodislife"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodislife"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-firestore:24.10.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("androidx.mediarouter:mediarouter:1.6.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.firebase:firebase-database:20.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.airbnb.android:lottie:6.3.0")
    implementation ("com.android.support:multidex:1.0.3")
    implementation ("com.google.android.gms:play-services-maps:17.0.0")
    implementation ("com.google.android.gms:play-services-location:18.0.0")
    implementation ("androidx.navigation:navigation-fragment:2.3.5")
    implementation ("androidx.navigation:navigation-ui:2.3.5")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
    implementation ("com.firebase:geofire-java:3.0.0")
    implementation ("com.koalap:geofirestore-android:1.2.2")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    implementation ("com.google.android.gms:play-services-location:7.+")
    implementation ("com.github.niqo01.rxplayservices:rx-play-services-location:0.4.0")
    implementation ("com.google.gms:google-services:4.4.1")
    implementation ("com.google.firebase:firebase-firestore:24.10.3")
    implementation ("com.orhanobut:dialogplus:1.11@aar")
    implementation ("com.firebaseui:firebase-ui-database:6.2.1")
    implementation ("com.google.firebase:firebase-database:19.3.1")
    implementation ("com.google.android.material:material:1.1.0")
}
