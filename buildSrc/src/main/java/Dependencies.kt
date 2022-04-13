/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    val kotlinCoreKtx by lazy { "androidx.core:core-ktx:1.7.0"}
    val appCompat by lazy { "androidx.appcompat:appcompat:1.4.1"}
    val material by lazy { "com.google.android.material:material:1.5.0"}
    val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:2.1.3"}
    val junit by lazy { "junit:junit:4.13.2"}
    val truth by lazy { "com.google.truth:truth:1.1.3"}
    val junitTest by lazy { "androidx.test.ext:junit:1.1.3"}
    val espresso by lazy { "androidx.test.espresso:espresso-core:3.4.0"}
    val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:2.9.0"}
    val gson by lazy { "com.squareup.retrofit2:converter-gson:2.9.0"}
    val retrofitInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"}
    val navigation by lazy { "androidx.navigation:navigation-fragment-ktx:2.3.5"}
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:2.3.5"}
    val glide by lazy { "com.github.bumptech.glide:glide:4.12.0"}
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:4.12.0"}
    val lifecycleKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha03"}
    val hilt by lazy { "com.google.dagger:hilt-android:2.38.1"}
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:2.38.1"}
    val jetPackCompose by lazy { "androidx.activity:activity-compose:1.4.0"}
    val composeMaterial by lazy { "androidx.compose.material:material:1.0.5"}
    val composeAnimation by lazy { "androidx.compose.animation:animation:1.0.5"}
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling:1.0.5"}
    val viewmodelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"}
    val composeJunit by lazy { "androidx.compose.ui:ui-test-junit4:1.0.5"}
    val composeCompiler by lazy { "androidx.compose.compiler:compiler:1.1.0-rc02"}
    val composeLiveData by lazy { "androidx.compose.runtime:runtime-livedata:1.1.0-rc01"}
    val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"}
    val coroutineTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"}
    val hamcrest by lazy { "org.hamcrest:hamcrest-all:1.3"}

    val room by lazy { "androidx.room:room-runtime:2.2.6"}
    val roomCompiler by lazy { "androidx.room:room-compiler:2.2.6"}
    val roomRxJava by lazy { "androidx.room:room-rxjava2:2.2.6"}
    val roomKtx by lazy { "androidx.room:room-ktx:2.2.6"}
    val archCore by lazy { "androidx.arch.core:core-testing:2.1.0"}
    val testCore by lazy { "androidx.test:core:1.4.0"}

    /*val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }*/

}