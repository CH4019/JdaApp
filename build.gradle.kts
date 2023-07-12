// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.23.0")
}
true // Needed to make the Suppress annotation work for the plugins block

// detektGenerateConfig
// buildUponDefaultConfig
// detektBaseline
detekt {
    parallel = true
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config.setFrom("$projectDir/config/detekt/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}
dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
    detektPlugins("ru.kode:detekt-rules-compose:1.2.2")
    detektPlugins("com.twitter.compose.rules:detekt:0.0.26")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.23.0")
}
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
