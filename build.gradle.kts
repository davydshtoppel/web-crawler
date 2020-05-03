
subprojects {

    group = "org.webcrawler"
    version = "0.1-SNAPSHOT"

    plugins.withType<JavaPlugin> {
        repositories {
            jcenter()
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}
