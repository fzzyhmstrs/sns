import org.jetbrains.kotlin.cli.common.toBooleanLenient
import java.net.URI
import com.matthewprenger.cursegradle.CurseArtifact
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import com.matthewprenger.cursegradle.Options

/*
* Copyright (c) 2024 Fzzyhmstrs
*
* This file is part of Modifier Core, a mod made for minecraft; as such it falls under the license of Modifier Core.
*
* Modifier Core is free software provided under the terms of the Timefall Development License - Modified (TDL-M).
* You should have received a copy of the TDL-M with this software.
* If you did not, see <https://github.com/fzzyhmstrs/Timefall-Development-Licence-Modified>.
* */

plugins {
    id("dev.architectury.loom")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
    id("com.modrinth.minotaur") version "2.+"
    id("com.matthewprenger.cursegradle") version "1.4.0"
}
base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
}
val log: File = file("changelog.md")
val modVersion: String by project
version = modVersion
val mavenGroup: String by project
group = mavenGroup
println("## Changelog for ${base.archivesName.get()} $modVersion \n\n" + log.readText())
println(base.archivesName.get().replace('_','-'))
repositories {

    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
    maven {
        url = URI("https://maven.neoforged.net/releases")
    }
    maven {
        url = URI("https://thedarkcolour.github.io/KotlinForForge/")
    }
    mavenCentral()
}
dependencies {
    val minecraftVersion: String by project
    minecraft("com.mojang:minecraft:$minecraftVersion")
    val yarnMappings: String by project
    val yarnMappingsPatchVersion: String by project
    mappings( loom.layered {
        mappings("net.fabricmc:yarn:$yarnMappings:v2")
        mappings("dev.architectury:yarn-mappings-patch-neoforge:$yarnMappingsPatchVersion")
    })
    val loaderVersion: String by project
    neoForge("net.neoforged:neoforge:$loaderVersion")

    val kotlinForForgeVersion: String by project
    modImplementation("thedarkcolour:kotlinforforge-neoforge:$kotlinForForgeVersion")

}

tasks {
    val javaVersion = JavaVersion.VERSION_21
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }

    }

    jar {
        from("LICENSE") { rename { "${base.archivesName.get()}_${it}" } }
    }
    jar {
        from( "credits.txt") { rename { "${base.archivesName.get()}_${it}" } }
    }

    processResources {
        val loaderVersion: String by project
        val fabricKotlinVersion: String by project
        inputs.property("version", project.version)
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(mutableMapOf(
                "version" to project.version)
            )
        }
    }
    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersion.toString())) }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }
}

modrinth {
    val uploadDebugMode: String by project
    val releaseType: String by project
    val modrinthSlugName: String by project
    val mcVersions: String by project
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(modrinthSlugName)
    versionNumber.set(modVersion)
    versionName.set("${base.archivesName.get()}-$modVersion")
    versionType.set(releaseType)
    uploadFile.set(tasks.remapJar.get())
    gameVersions.addAll(mcVersions.split(","))
    loaders.addAll("neoforged")
    detectLoaders.set(false)
    changelog.set("## Changelog for Symbols 'n' Stuff $modVersion \n\n" + log.readText())
    dependencies {
        required.project("kotlin-for-forge")
    }
    debugMode.set(uploadDebugMode.toBooleanLenient() ?: true)
}

if (System.getenv("CURSEFORGE_TOKEN") != null) {
    curseforge {
        val releaseType: String by project
        val mcVersions: String by project
        val uploadDebugMode: String by project

        apiKey = System.getenv("CURSEFORGE_TOKEN")
        project(closureOf<CurseProject> {
            id = "1091274"
            changelog = log
            changelogType = "markdown"
            this.releaseType = releaseType
            for (ver in mcVersions.split(",")) {
                addGameVersion(ver)
            }
            addGameVersion("NeoForge")
            mainArtifact(tasks.remapJar.get().archiveFile.get(), closureOf<CurseArtifact> {
                displayName = "${base.archivesName.get()}-${project.version}"
                relations(closureOf<CurseRelation> {
                    this.requiredDependency("kotlin-for-forge")
                })
            })
            relations(closureOf<CurseRelation> {
                this.requiredDependency("kotlin-for-forge")
            })
        })
        options(closureOf<Options> {
            javaIntegration = false
            forgeGradleIntegration = false
            javaVersionAutoDetect = false
            debug = uploadDebugMode.toBooleanLenient() ?: true
        })
    }
}

tasks.register("uploadAll") {
    group = "upload"
    dependsOn(tasks.modrinth.get())
    dependsOn(tasks.curseforge.get())
}