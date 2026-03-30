pipeline {
    agent any

    environment {
        // Path to your local Maven wrapper executable
        MVN_CMD = 'C:\\Users\\heg\\.m2\\wrapper\\dists\\apache-maven-3.9.12\\59fe215c0ad6947fea90184bf7add084544567b927287592651fda3782e0e798\\bin\\mvn.cmd'
        // Path to your Maven settings file containing the proxy configuration
        MVN_SETTINGS = 'C:\\Users\\heg\\.m2\\settings.xml'
    }

    stages {
        stage('Checkout') {
            steps {
                // Pulls the latest code from your Git repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Compiles the application into a .jar file and skips the database-dependent tests
                bat "\"%MVN_CMD%\" -s \"%MVN_SETTINGS%\" clean install -DskipTests -U"
            }
        }

        stage('Deploy (Local Windows)') {
            steps {
                script {
                    // Step 1: Kill any existing process running on port 8083
                    // The 'exit /b 0' ensures the pipeline doesn't fail if the app wasn't already running
                    bat '''
                    FOR /F "tokens=5" %%T IN ('netstat -a -n -o ^| find "LISTENING" ^| find ":8083"') DO taskkill /F /PID %%T
                    exit /b 0
                    '''
                    
                    // Step 2: Start the newly built JAR file in the background
                    // JENKINS_NODE_COOKIE prevents Jenkins from automatically killing the app when the pipeline finishes
                    bat '''
                    set JENKINS_NODE_COOKIE=dontKillMe
                    start javaw -jar target\\spring-jpa-project01-0.0.1-SNAPSHOT.jar
                    exit /b 0
                    '''
                    
                    // Give the Spring Boot application a few seconds to fully boot up
                    sleep time: 10, unit: 'SECONDS'
                }
            }
        }
    }

    post {
        always {
            // Archives the built .jar file in Jenkins so it can be downloaded later
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: Application built and DEPLOYED successfully! It should now be running on port 8083.'
        }
        failure {
            echo 'FAILED: Please check the Jenkins Console Output for errors.'
        }
    }
}
