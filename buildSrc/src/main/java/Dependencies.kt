import org.gradle.api.JavaVersion

private object Versions {


    const val supportLib = "1.0.2"
    const val archLifecycle = "2.0.1"
    const val constraintLayout = "2.0.0-beta2"
    const val androidKtx = "1.0.1"
    const val pagingVersion = "2.1.0"

    const val kotlin = "1.3.41"
    const val coroutinesCore = "1.3.0"
    const val coroutinesAndroid = "1.1.1"
    const val coroutinesTest = "1.3.3"

    const val gradle = "5.2.1"
    const val gradleBuildTools = "3.5.0"

    const val timber = "4.7.1"
    const val timberKtx = "0.1.0"
    const val koin = "2.0.0"

    const val junit = "4.12"
    const val kotlinTest = "3.3.0"
    const val espresso = "3.0.2"
    const val spoon = "2.0.0-SNAPSHOT"
    const val uiAutomator = "2.2.0"
    const val supportTest = "1.1.0"
    const val mockk = "1.9"
    const val mokitoKotlin =  "2.2.0"
    const val archCore = "2.0.0-rc01"

    const val fab = "1.6.4"

    // eMan Deps
    const val kaal = "0.6.0"

    // Retrofit
    const val retrofit = "2.6.0"

    // GSON
    const val gson = "2.8.5"

    //oKHttp
    const val okHttp = "3.12.0"
    const val okHttpLogginginterceptor = "3.11.0"

    // Navigation components
    const val navigationComponent = "2.0.0"
    const val safeArgs = "1.0.0"

    const val glide = "4.9.0"
}

/* =============================  ANDROID ============================= */

object Android {
    const val applicationId = "com.example.notes"

    const val minSdk = 21
    const val targetSdk = 28
    const val compileSdk = 28

    const val versionCode = 1
    const val versionName = "1"

    const val testInstrumentRunner = "android.support.test.runner.AndroidJUnitRunner"
    val sourceCompatibilityJava = JavaVersion.VERSION_1_8
    val targetCompatibilityJava = JavaVersion.VERSION_1_8
}

/* =============================  BUILD-PLUGINS ======================= */

object GradlePlugins {
    const val gradle = Versions.gradle

    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradleBuildTools}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val safeArgsPlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Dependencies {
    /* =============================  KOTLIN ============================== */

    object Kotlin {
        const val kotlinStbLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesAndroid}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    }

    /* =============================  LIBS ================================ */

    object Libs {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.supportLib}"
        const val supportFragment = "androidx.fragment:fragment:${Versions.supportLib}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.archLifecycle}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
        const val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"
        const val paging =  "androidx.paging:paging-runtime:${Versions.pagingVersion}"
        const val room = "androidx.room:room-runtime:2.2.3"
        const val roomCompiler = "androidx.room:room-compiler:2.2.3"
        const val roomKtx = "androidx.room:room-ktx:2.2.3"


        const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}" // Koin Android Scope feature
        const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
        const val koinCore = "org.koin:koin-core:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}" // Koin Android ViewModel feature

        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val timberKtx = "cz.eman.logger:timber-ktx:${Versions.timberKtx}"

        // eMan Deps
        const val kaalDomain = "cz.eman.kaal:kaal-domain:${Versions.kaal}"
        const val kaalPresentation = "cz.eman.kaal:kaal-presentation:${Versions.kaal}"
        const val kaalInfrastructure = "cz.eman.kaal:kaal-infrastructure:${Versions.kaal}"
        const val kaalCore = "cz.eman.kaal:kaal-core:${Versions.kaal}"

        // GSON
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        //Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitCoroutineConverterFactory = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

        //Okhttp3
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogginginterceptor}"

        // Glide
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

        //floating action library
        const val floatingActionButton = "com.github.clans:fab:${Versions.fab}"

    }

    /* =============================  TEST-LIBS =========================== */

    object TestLibs {
        const val junit = "junit:junit:${Versions.junit}"
        const val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
        const val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlinTest}"
        const val mockkUnit = "io.mockk:mockk:${Versions.mockk}"
        const val mockkInstrument = "io.mockk:mockk-android:${Versions.mockk}"
        const val spoonClient = "com.squareup.spoon:spoon-client:${Versions.spoon}"
        const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.uiAutomator}"
        const val testRunner = "androidx.test:runner:${Versions.supportTest}"
        const val testRules = "androidx.test:rules:${Versions.supportTest}"
        const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCore}"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
        const val mokitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mokitoKotlin}"
    }
}