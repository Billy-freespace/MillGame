pipeline {
    agent {
        docker { image 'openjdk:11' }
    }
    stages {
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }

        stage('tests') {
            steps {
                sh './gradlew test'
            }
        }

        stage('coverage') {
            steps {
                sh './gradlew jacocoTestReport'
            }
        }
    }
}