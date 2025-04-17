pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'java_Home'
    }

    environment {
        SONARQUBE_SERVER = 'SonarQubeServers'  // The name of the SonarQube server configured in Jenkins
        SONAR_TOKEN = 'squ_2803d6e93e1cffca744a6b7632e632ef548adfdc' // Store the token securely
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub' // Define Docker Hub credentials ID
        DOCKERHUB_REPO = 'runzhouzhu/week7_inclass_test1' // Define Docker Hub repository name
        DOCKER_IMAGE_TAG = 'sep2_inclass_assignments_week5' // Define Docker image tag
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

        stage('Build Docker Image') {
            steps {
                // Build Docker image
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                // Push Docker image to Docker Hub
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }

    }
}
