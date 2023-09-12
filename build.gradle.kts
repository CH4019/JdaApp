buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.0-alpha03")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.23.0")
}
true // Needed to make the Suppress annotation work for the plugins block

// detektGenerateConfig
// detektBaseline
detekt {
    parallel = true
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom("$projectDir/config/detekt/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
    basePath = projectDir.absolutePath
}
dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    detektPlugins("ru.kode:detekt-rules-compose:1.2.2")
    detektPlugins("com.twitter.compose.rules:detekt:0.0.26")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.0")
}
// gradle detektProjectBaseline
val detektProjectBaseline by tasks.registering(io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$projectDir/config/detekt/detekt.yml"))
    baseline.set(file("$projectDir/config/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektReports") {
    description = "detekt current reports"
    reports {
        // Enable/Disable XML report (default: true)
        xml.required.set(true)
        xml.outputLocation.set(file("build/reports/detekt.xml"))
        // Enable/Disable HTML report (default: true)
        html.required.set(true)
        html.outputLocation.set(file("build/reports/detekt.html"))
        // Enable/Disable TXT report (default: true)
        txt.required.set(true)
        txt.outputLocation.set(file("build/reports/detekt.txt"))
        // Enable/Disable SARIF report (default: false)
        sarif.required.set(true)
        sarif.outputLocation.set(file("build/reports/detekt.sarif"))
        // Enable/Disable MD report (default: false)
        md.required.set(true)
        md.outputLocation.set(file("build/reports/detekt.md"))
    }
}
