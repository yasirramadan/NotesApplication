
plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.Libs.kaalDomain)
    implementation(Dependencies.Libs.kaalCore)

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStbLib)
    implementation(Dependencies.Kotlin.coroutinesCore)

    // Tests
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.kotlinTest)
    testImplementation(Dependencies.TestLibs.mockkUnit)
}