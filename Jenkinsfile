pipeline {
    agent any

    tools {
        // Ensure you have JDK 8 configured in Jenkins (Manage Jenkins -> Global Tool Configuration)
        jdk 'JDK 8' 
    }

    stages {
        stage('Checkout') {
            steps {
                // Checks out the source code from the configured repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Make the Maven wrapper executable and build the application
                sh 'chmod +x mvnw'
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Run the Spring Boot tests
                sh './mvnw test'
            }
        }
    }

    post {
        always {
            // Optional: Archive the built JAR file so it can be downloaded from Jenkins
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
    }
}
