pipeline {
    agent any

    environment {
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
                bat "\"%MVN_CMD%\" -s \"%MVN_SETTINGS%\" clean install -DskipTests -U"
            }
        }

        stage('Deploy (Local Windows)') {
            steps {
                script {
                    // Step 1: Force kill any existing Java process running on port 8083
                    // The '|| exit 0' ensures the pipeline doesn't fail if the app isn't currently running
                    bat '''
                    FOR /F "tokens=5" %%T IN ('netstat -a -n -o ^| find "LISTENING" ^| find ":8083"') DO (
                        taskkill /F /PID %%T || exit 0
                    )
                    '''
                    
                    // Step 2: Start the new JAR file in the background
                    // We use 'javaw' instead of 'java' so it runs silently without locking up the Jenkins build
                    bat 'start javaw -jar target\\spring-jpa-project01-0.0.1-SNAPSHOT.jar'
                    
                    // Give it a few seconds to boot up
                    sleep time: 10, unit: 'SECONDS'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: Application built and DEPLOYED to port 8083!'
        }
        failure {
            echo 'FAILED: Check Console Output for errors.'
        }
    }
}
