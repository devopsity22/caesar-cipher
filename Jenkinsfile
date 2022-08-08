pipeline {
    agent any

    environment {
        REPO = "devopsity22/caesar-cipher"
    }

    stages {
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('test') {
            steps {
                sh './gradlew test'
                sh 'java -jar build/libs/caesar-cipher.jar'
            }
        }
        stage('Release') {
            steps {
                sh 'printenv'
            }
        }
    }
}