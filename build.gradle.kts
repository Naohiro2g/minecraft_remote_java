plugins {
    id("java")
    application
}

group = "club.code2create"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("club.code2create.Main")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

// 実行可能なJARファイルを作成するための設定
tasks.jar {
    manifest {
        attributes["Main-Class"] = "club.code2create.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}