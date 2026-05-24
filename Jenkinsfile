pipeline {
agent any

environment {
    SONAR_URL = "http://3.84.208.154:9000"
    DOCKER_IMAGE = "dineshiiiipandian/aws-devops-cicd:${BUILD_NUMBER}"
}

stages {

    stage('Checkout') {
        steps {
            echo 'Code Checkout Successful'
            sh 'java -version'
            sh 'mvn -version'
        }
    }

    stage('Build and Test') {
        steps {
            sh 'mvn clean package'
        }
    }

    stage('Static Code Analysis') {
    steps {
        echo 'SonarQube temporarily skipped'
    }
}

    stage('Build Docker Image') {
        steps {
            sh 'docker build -t $DOCKER_IMAGE .'
        }
    }

    stage('Push Docker Image') {
        steps {
            script {
                docker.withRegistry('https://index.docker.io/v1/', 'docker-cred') {
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }
    }

    stage('Pipeline Complete') {
        steps {
            echo 'CI/CD Pipeline Executed Successfully'
        }
    }
}

}
