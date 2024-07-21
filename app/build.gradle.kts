plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.foodislife"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodislife"
        minSdk = 26
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-firestore:25.0.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    implementation("androidx.mediarouter:mediarouter:1.7.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.firebase:firebase-database:21.0.0")
    implementation("androidx.activity:activity:1.9.0")
    implementation("androidx.annotation:annotation:1.8.0")
    implementation("com.google.firebase:firebase-messaging:24.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.airbnb.android:lottie:6.3.0")
    implementation ("com.android.support:multidex:1.0.3")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.firebase:geofire-java:3.0.0")
    implementation ("com.koalap:geofirestore-android:1.2.2")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")
    implementation ("com.github.niqo01.rxplayservices:rx-play-services-location:0.4.0")
    implementation ("com.google.gms:google-services:4.4.2")
    implementation ("com.google.firebase:firebase-firestore:25.0.0")
    implementation ("com.orhanobut:dialogplus:1.11@aar")
    implementation ("com.firebaseui:firebase-ui-database:7.1.1")
    implementation ("com.google.firebase:firebase-database:21.0.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.biometric:biometric:1.1.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.hbb20:ccp:2.5.0")
    implementation ("com.github.dhaval2404:imagepicker:2.1")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.firebase:firebase-firestore-ktx:25.0.0")
    implementation ("com.google.firebase:firebase-storage-ktx:21.0.0")
    implementation ("com.google.firebase:firebase-messaging-ktx:24.0.0")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")


}
