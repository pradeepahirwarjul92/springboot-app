pipeline {
    agent any

    environment {
        // Using the exact paths from your working project
        MVN_CMD = 'C:\\Users\\heg\\.m2\\wrapper\\dists\\apache-maven-3.9.12\\59fe215c0ad6947fea90184bf7add084544567b927287592651fda3782e0e798\\bin\\mvn.cmd'
        MVN_SETTINGS = 'C:\\Users\\heg\\.m2\\settings.xml'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Call the specific Maven executable and pass the settings.xml
                // -U forces a check for updated releases/snapshots on remote repositories
                bat "\"%MVN_CMD%\" -s \"%MVN_SETTINGS%\" clean install -DskipTests -U"
            }
        }

        stage('Test') {
            steps {
                bat "\"%MVN_CMD%\" -s \"%MVN_SETTINGS%\" test"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: Spring Boot application built and tested successfully!'
        }
        failure {
            echo 'FAILED: Check Console Output for errors.'
        }
    }
}
