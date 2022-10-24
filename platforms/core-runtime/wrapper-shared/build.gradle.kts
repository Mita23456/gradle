plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Utility code shared between the wrapper and the Gradle distribution"

gradlebuildJava.usedInWorkers()

dependencies {

    api(project(":base-annotations"))

    implementation(project(":files")) {
        because("We need org.gradle.internal.file.PathTraversalChecker")
    }

    testImplementation(project(":base-services"))
    testImplementation(project(":core-api"))
    testImplementation(project(":native"))
    testImplementation(libs.commonsCompress)

    integTestImplementation(project(":dependency-management"))
    integTestImplementation(project(":logging"))

    integTestDistributionRuntimeOnly(project(":distributions-full"))
}

dependencyAnalysis {
    issues {
        onAny {
            severity("fail")
        }
    }
}
