plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        applicationId = Android.applicationId

        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode
        versionName = "${project.version}"

        testInstrumentationRunner = Android.testInstrumentRunner
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":infrastructure"))

    // Kaal library
    implementation(Dependencies.Libs.kaalPresentation)
    implementation(Dependencies.Libs.kaalDomain)

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStbLib)
    implementation(Dependencies.Kotlin.coroutinesCore)

    // Google Android Libraries
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.androidKtx)
    implementation(Dependencies.Libs.navigationFragment)
    implementation(Dependencies.Libs.navigationUi)
    implementation(Dependencies.Libs.paging)
    implementation(Dependencies.Libs.lifecycleRuntime)

    // Third Party - others
    implementation(Dependencies.Libs.koinAndroid)
    implementation(Dependencies.Libs.koinViewModel)
    implementation(Dependencies.Libs.koinScope)
    implementation(Dependencies.Libs.timber)

    // Glide
    implementation(Dependencies.Libs.glide)
    annotationProcessor(Dependencies.Libs.glideCompiler)

    // Floating action library
    implementation(Dependencies.Libs.floatingActionButton)

    // Tests
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.archCoreTest)

    testImplementation(Dependencies.TestLibs.coroutineTest)
    testImplementation(Dependencies.TestLibs.mokitoKotlin)

    androidTestImplementation(Dependencies.TestLibs.mockkInstrument)
    androidTestImplementation(Dependencies.TestLibs.uiAutomator)
    androidTestImplementation(Dependencies.TestLibs.testRules)
    androidTestImplementation(Dependencies.TestLibs.testRunner)

    androidTestImplementation(Dependencies.TestLibs.espressoCore) {
        exclude(module = "support-annotations")
    }
}
