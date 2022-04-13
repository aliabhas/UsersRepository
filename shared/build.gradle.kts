plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    packagingOptions {
        resources.excludes += "META-INF/*"
    }
}

dependencies {

    implementation(Deps.coroutine)
    implementation(Deps.kotlinCoreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    implementation(Deps.retrofit2)
    implementation(Deps.gson)
    implementation(Deps.retrofitInterceptor)


    // Local Unit Tests
    testImplementation(Deps.junit)
    testImplementation(Deps.coroutineTest)
    testImplementation(Deps.truth)

    // Instrumented Unit Tests
    androidTestImplementation(Deps.coroutineTest)
    androidTestImplementation(Deps.truth)
    androidTestImplementation(Deps.junitTest)

    implementation(project(":model"))
    implementation(project(":database"))

}