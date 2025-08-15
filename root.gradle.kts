plugins {
    id("dev.deftu.gradle.multiversion-root")
}

preprocess {
    "1.12.2-forge"(1_12_02, "srg") {
        "1.8.9-forge"(1_08_09, "srg")

    }

    strictExtraMappings.set(true)
}
