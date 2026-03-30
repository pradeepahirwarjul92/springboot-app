pipeline {
    agent any

    // Note: If you configured the 'JDK 8' tool in the previous step, 
    // you can add the 'tools { jdk "JDK 8" }' block back right here.

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Use 'bat' instead of 'sh', and call the Windows batch script 'mvnw.cmd'
                // We also don't need the 'chmod +x' command on Windows
                bat 'mvnw.cmd clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
    }
}
