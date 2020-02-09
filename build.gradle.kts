// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        
    }
    dependencies {

        classpath(GradlePlugins.kotlin)
        classpath(GradlePlugins.androidGradle)
        classpath(GradlePlugins.safeArgsPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}