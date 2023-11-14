plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

android {
    namespace = "eam.xagh.unilocal"
    compileSdk = 33

    defaultConfig {
        applicationId = "eam.xagh.unilocal"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Versions
object V {
    const val composeBom = "2023.03.00"
    const val activityKtx = "1.7.2"
    const val fragmentKtx = "1.6.2"
    const val datastorePreferences = "1.0.0"
    const val core_ktx = "1.10.1"
    const val lifecycle_runtime_ktx = "2.6.2"
    const val activityCompose = "1.7.2"
    const val hilt = "2.48"
    const val navigation = "2.5.3"
    const val maps = "2.2.1"
    const val playServicesMaps = "18.2.0"
    const val gson = "2.8.9"
    const val coil = "2.4.0"
    const val googleAccompanistPermissions = "0.33.0"
    const val playServicesLocation = "21.0.1"
    const val hiltNavigation = "1.0.0"
    const val firebaseBom = "32.5.0"
    const val androidBiometric = "1.1.0"
    const val appcompat = "1.4.1"
}

dependencies {
    //noinspection GradleDependency
    implementation("androidx.activity:activity-ktx:${V.activityKtx}")
    implementation(platform("com.google.firebase:firebase-bom:${V.firebaseBom}"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("androidx.fragment:fragment-ktx:${V.fragmentKtx}")
    implementation("androidx.biometric:biometric-ktx:1.2.0-alpha05")
    implementation("androidx.appcompat:appcompat:${V.appcompat}")
    implementation("androidx.datastore:datastore-preferences:${V.datastorePreferences}")
    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:${V.core_ktx}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${V.lifecycle_runtime_ktx}")
    //noinspection GradleDependency
    implementation("androidx.activity:activity-compose:${V.activityCompose}")
    implementation("com.google.code.gson:gson:${V.gson}")
    //noinspection GradleDependency
    implementation("io.coil-kt:coil-compose:${V.coil}")
    //implementation("com.google.accompanist:accompanist-permissions:${V.googleAccompanistPermissions}")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:${V.composeBom}"))
    implementation("androidx.compose.material:material-icons-extended")
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-compose:${V.navigation}")
    implementation("com.google.maps.android:maps-compose:${V.maps}")
    implementation("com.google.android.gms:play-services-maps:${V.playServicesMaps}")
    implementation("com.google.dagger:hilt-android:${V.hilt}")
    //noinspection GradleDependency
    implementation("androidx.hilt:hilt-navigation-compose:${V.hiltNavigation}")
    implementation(platform("androidx.compose:compose-bom:${V.composeBom}"))
    implementation("com.google.android.gms:play-services-location:${V.playServicesLocation}")
    androidTestImplementation(platform("androidx.compose:compose-bom:${V.composeBom}"))
    kapt("com.google.dagger:hilt-compiler:${V.hilt}")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}