plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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
    const val compose_boom = "2023.03.00"
    const  val activity_ktx = "1.7.2"
    const val fragment_ktx = "1.6.1"
    const val datastore_preferences = "1.0.0"
    const val core_ktx = "1.10.1"
    const val lifecycle_runtime_ktx = "2.6.2"
    const val activity_compose = "1.7.2"
    const val hilt = "2.44"
    const val navigation = "2.5.3"
    const val maps = "2.2.1"
    const val playServicesMaps = "18.1.0"
}

dependencies {
    // Android
    implementation("androidx.activity:activity-ktx:${V.activity_ktx}")
    implementation("androidx.fragment:fragment-ktx:${V.fragment_ktx}")
    implementation("androidx.datastore:datastore-preferences:${V.datastore_preferences}")
    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:${V.core_ktx}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${V.lifecycle_runtime_ktx}")
    implementation("androidx.activity:activity-compose:${V.activity_compose}")

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:${V.compose_boom}"))
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:${V.navigation}")
    implementation("com.google.maps.android:maps-compose:${V.maps}")
    implementation("com.google.android.gms:play-services-maps:${V.playServicesMaps}")

    // Dagger Hilt for dependency injection
    implementation("com.google.dagger:hilt-android:${V.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
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