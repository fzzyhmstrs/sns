
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
    id("fabric-loom")
    val kotlinVersion: String by System.getProperties()
    kotlin("jvm").version(kotlinVersion)
    id("com.modrinth.minotaur") version "2.+"
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
    /*maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }*/
    /*maven {
        name = "REI"
        url = uri("https://maven.shedaniel.me")
    }*/
    /*maven {
        name = "Progwml6 maven"
        url = uri("https://dvs1.progwml6.com/files/maven/")
    }*/
    /*maven {
        name = "Ladysnake Libs"
        url = uri("https://maven.ladysnake.org/releases")
        content {
            includeGroup("io.github.ladysnake")
            includeGroupByRegex("io\\.github\\.onyxstudios.*")
        }
    }*/
    /*maven {
        name = "Patchouli Lib"
        url = uri("https://maven.blamejared.com")
    }*/
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
    /*maven {
        name = "Jitpack"
        url = uri("https://jitpack.io")
    }*/
    /*flatDir {
        dirs("E:\\Documents\\Mod Libraries\\ac\\build\\libs")
    }*/
    /*flatDir {
        dirs("E:\\Documents\\Mod Libraries\\fc\\build\\libs")
    }*/
    /*flatDir {
        dirs("E:\\Documents\\Mod Libraries\\gc\\build\\libs")
    }*/
    /*flatDir {
        dirs("E:\\Documents\\Mod Development\\ai\\build\\libs")
    }*/
    /*flatDir {
        dirs("E:\\Documents\\Mod Libraries\\fzzy_config\\build\\libs")
    }*/
    mavenCentral()
}
dependencies {
    val minecraftVersion: String by project
    minecraft("com.mojang:minecraft:$minecraftVersion")
    val yarnMappings: String by project
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    val loaderVersion: String by project
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    val fabricVersion: String by project
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    val fabricKotlinVersion: String by project
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")

    /*val fzzyConfigVersion: String by project
    modImplementation(":fzzy_config-$fzzyConfigVersion") {
        exclude("net.fabricmc.fabric-api")
    }*/

    /*val acVersion: String by project
    modImplementation(":amethyst_core-$acVersion") {
        exclude("net.fabricmc.fabric-api")
    }*/

    /*val fcVersion: String by project
    modImplementation(":fzzy_core-$fcVersion") {
        exclude("net.fabricmc.fabric-api")
    }*/

    /*val gcVersion: String by project
    modImplementation(":gear_core-$gcVersion") {
        exclude("net.fabricmc.fabric-api")
    }*/

    /*val aiVersion: String by project
    modImplementation(":amethyst_imbuement-$aiVersion") {
        exclude("net.fabricmc.fabric-api")
    }*/

}

tasks {
    val javaVersion = JavaVersion.VERSION_17
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
        inputs.property("id", base.archivesName.get())
        inputs.property("loaderVersion", loaderVersion)
        inputs.property("fabricKotlinVersion", fabricKotlinVersion)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf(
                "version" to project.version,
                "id" to base.archivesName.get(),
                "loaderVersion" to loaderVersion,
                "fabricKotlinVersion" to fabricKotlinVersion)
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
    val modrinthSlugName: String by project
    val mcVersions: String by project
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(modrinthSlugName)
    versionNumber.set(modVersion)
    versionName.set("${base.archivesName.get()}-$modVersion")
    versionType.set("release")
    uploadFile.set(tasks.remapJar.get())
    gameVersions.addAll(mcVersions.split(","))
    loaders.addAll("fabric")
    detectLoaders.set(false)
    changelog.set("## Changelog for Symbols 'n' Stuff $modVersion \n\n" + log.readText())
    dependencies {
        required.project("fabric-api")
        required.project("fabric-language-kotlin")
        //required.project("amethyst-core")
        //required.project("fzzy-core")
        //required.project("gear-core")
        //optional.project("emi")
        //embedded.project("trinkets")
        //embedded.project("patchouli")
    }
    debugMode.set(true)
}