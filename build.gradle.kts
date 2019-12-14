import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.8.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
	id("org.sonarqube").version("2.8")
}

group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("net.logstash.logback:logstash-logback-encoder:6.1")
	implementation("io.micrometer:micrometer-registry-prometheus")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compile("org.apache.commons:commons-pool2:2.5.0")
	compile("redis.clients:jedis:2.9.0")
	compile("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}