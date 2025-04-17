pipeline {
    agent any

    environment {
        SONARQUBE_SERVER = 'SonarQubeServers'  // The name of the SonarQube server configured in Jenkins
        SONAR_TOKEN = 'squ_2803d6e93e1cffca744a6b7632e632ef548adfdc' // Store the token securely
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'sep2_inclass_assignments_week5', url: 'https://github.com/RunzhouZHu/sep2_inclass_assignments.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    bat """
                        sonar-scanner ^
                        -Dsonar.projectKey=devops-demo ^
                        -Dsonar.sources=src ^
                        -Dsonar.projectName=DevOps-Demo ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=${env.SONAR_TOKEN} ^
                        -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }

    }
}
