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
                sh "./gradlew build"
            }
        }
        stage("Test") {
            steps {
                withGradle {
                    sh "./gradlew test"
                }
            }
        }
        stage("Build") {
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