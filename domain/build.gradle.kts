plugins {
    id("java-library")
    kotlin("jvm")
}
dependencies {
    implementation(Dependencies.Libs.kaalDomain)

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStbLib)
    implementation(Dependencies.Kotlin.coroutinesCore)

    // Tests
    testImplementation(Dependencies.TestLibs.junit)
    testImplementation(Dependencies.TestLibs.kotlinTest)
    testImplementation(Dependencies.TestLibs.mokitoKotlin)

}