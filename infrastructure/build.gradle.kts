
plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode
        versionName = "${project.version}"

        testInstrumentationRunner = Android.testInstrumentRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        sourceCompatibility = Android.sourceCompatibilityJava
        targetCompatibility = Android.targetCompatibilityJava

    }

    lintOptions {
        setLintConfig(File("lint.xml"))
    }
}

dependencies {
    implementation (fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Libs.kaalDomain)
    implementation(Dependencies.Libs.kaalInfrastructure)
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.Kotlin.kotlinStbLib)
    implementation(Dependencies.Kotlin.coroutinesCore)
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Libs.lifecycleExtension)

    // Retrofit
    implementation(Dependencies.Libs.retrofit)
    implementation(Dependencies.Libs.retrofitConverter)
    implementation(Dependencies.Libs.okHttpLoggingInterceptor)
    implementation(Dependencies.Libs.gson)
    implementation(Dependencies.Libs.retrofitCoroutineConverterFactory)


    implementation(Dependencies.Libs.koinAndroid)
    implementation(Dependencies.Libs.timberKtx)

    //Room
    implementation(Dependencies.Libs.room)
    kapt(Dependencies.Libs.roomCompiler)
    implementation(Dependencies.Libs.roomKtx)



    // Test
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.kotlinTest)
    testImplementation(Dependencies.TestLibs.mockkUnit)
    testImplementation(Dependencies.TestLibs.mockkInstrument)
    testImplementation(Dependencies.TestLibs.testRunner)
    testImplementation(Dependencies.TestLibs.archCoreTest)
}