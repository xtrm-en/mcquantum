plugins {
    kotlin("jvm") version "1.6.10" apply false
}

subprojects {
    apply(plugin = "kotlin")

    group = "me.xtrm.mcquantum"

    repositories {
        mavenCentral()
    }
}
