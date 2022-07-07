pipeline {
    agent {
        docker  { image 'openjdk:11' }
    }
    stages {
        stage('build') {
            steps {
                sh './gradlew build --info --warning-mode=all -x pmdMain -x pmdTest'
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
