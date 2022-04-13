plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "aliabbas.com.userrepositories"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
//sadasd
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc02"
    }
    //Enable JetPack Compose
    buildFeatures {
        dataBinding = true
        // Enables Jetpack Compose for this module
        compose = true
    }
}

dependencies {

    implementation(Deps.kotlinCoreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintlayout)
    testImplementation(Deps.junit)
    testImplementation(Deps.truth)
    androidTestImplementation(Deps.truth)

    androidTestImplementation(Deps.junitTest)
    androidTestImplementation(Deps.espresso)

    //Retrofit dependencies
    implementation(Deps.retrofit2)
    implementation(Deps.gson)
    implementation(Deps.retrofitInterceptor)

    //Navigation
    implementation(Deps.navigation)
    implementation(Deps.navigationUiKtx)

    //Glide
    implementation(Deps.glide)
    kapt(Deps.glideCompiler)

    //for repeatOnLifecycle(Lifecycle.State.STARTED) for shared flow
    implementation(Deps.lifecycleKtx)

    //Hilt implementation
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    //JetPack compose dependencies
    // Integration with activities
    implementation(Deps.jetPackCompose)
    // Compose Material Design
    implementation(Deps.composeMaterial)
    // Animations
    implementation(Deps.composeAnimation)
    // Tooling support (Previews, etc.)
    implementation(Deps.composeTooling)
    // Integration with ViewModels
    implementation(Deps.viewmodelCompose)
    // UI Tests
    androidTestImplementation(Deps.composeJunit)
    implementation(Deps.composeCompiler)
    implementation(Deps.composeLiveData)


    //Adding dependencies on modules
    implementation(project(":shared"))
    implementation(project(":model"))
    implementation(project(":database"))

}