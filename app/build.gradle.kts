plugins {
    id("com.android.application")
    id("com.apollographql.apollo3").version("3.8.2")
    //id("com.apollographql.apollo").version("2.4.6")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //implementation("com.apollographql.apollo:apollo-runtime:2.4.6")
    //implementation("com.apollographql.apollo:apollo-coroutines-support:2.4.6")
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
}