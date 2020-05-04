plugins {
    `java-library`
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {

    implementation("org.jsoup:jsoup:1.13.1")
    implementation("org.jetbrains:annotations:16.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.mockito:mockito-core:3.3.3")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}
