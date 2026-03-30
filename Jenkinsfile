pipeline {
    agent any

    environment {
        // Using your exact Maven and settings paths for the proxy
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
                // The -DskipTests flag tells Maven to compile the .jar but ignore the tests
                bat "\"%MVN_CMD%\" -s \"%MVN_SETTINGS%\" clean install -DskipTests -U"
            }
        }
        
        // Note: The Test stage has been completely removed as requested.
    }

    post {
        always {
            // This will save the built .jar file in Jenkins so you can download it
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: Spring Boot application built successfully (Tests skipped)!'
        }
        failure {
            echo 'FAILED: Check Console Output for errors.'
        }
    }
}
