pipeline {
    agent any
    stages {
        stage("Checkout sources from GitHub") {
            steps {
                git branch: "BF-207", url: 'https://github.com/korzdan/bookify.git'
            }
        }
        stage("Compile") {
            steps {
                withGradle {
                    sh "./gradlew classes testClasses --no-daemon"
                }
            }
        }
        stage("Test") {
            steps {
                withGradle {
                    sh "./gradlew test --no-daemon"
                }
            }
        }
        stage("Build app") {
            steps {
                withGradle {
                    sh "./gradlew build -x test --parallel --no-daemon"
                }
            }
        }
        stage("Build docker image") {
            steps {
                script {
                    docker.build("bookify", "-f Dockerfile .")
                }
            }
        }
        stage("Run image") {
            steps {
                script {
                    sh "docker stop bookify-con || true"
                    sh "docker rm bookify-con || true"
                    docker.image("bookify").run("--name bookify-con -p 8081:8081")
                }
            }
        }
    }
}