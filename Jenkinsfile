pipeline {
    agent any

    tools {
        // This MUST match the name exactly as it appears in your screenshot
        maven 'Maven-3.9.12' 
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Using standard 'mvn' command
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
    }
}
