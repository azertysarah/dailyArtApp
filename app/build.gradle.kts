plugins {
    id("com.android.application")
    id("com.apollographql.apollo3").version("3.8.2")
    //id("com.apollographql.apollo").version("2.4.6")
    id("kotlin-android")
    id("kotlin-parcelize")
}

apollo {
    service("service") {
        packageName.set("com.isep.dailyartapp")
    }
}

android {
    namespace = "com.isep.dailyartapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.isep.dailyartapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_URL", "\"https://apicollections.parismusees.paris.fr/graphql\"")
            buildConfigField("String", "AUTH_TOKEN", "\"cb3df41b-aefc-4e3a-9c39-fa315babc975\"")
        }
        release {
            buildConfigField("String", "API_URL", "\"https://apicollections.parismusees.paris.fr/graphql\"")
            buildConfigField("String", "AUTH_TOKEN", "\"cb3df41b-aefc-4e3a-9c39-fa315babc975\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-parcelize-runtime:1.9.0")
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.4")
    implementation("androidx.navigation:navigation-ui:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.picasso:picasso:2.8")
}