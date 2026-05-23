pipeline {
agent {
docker {
image 'abhishekf5/maven-abhishek-docker-agent:v1'
args '--user root -v /var/run/docker.sock:/var/run/docker.sock'
}
}

environment {
    SONAR_URL = "http://3.84.208.154:9000"
    DOCKER_IMAGE = "dineshiiiipandian/aws-devops-cicd:${BUILD_NUMBER}"
}

stages {

    stage('Checkout') {
        steps {
            echo 'Code Checkout Successful'
        }
    }

    stage('Build and Test') {
        steps {
            sh 'ls -ltr'
            sh 'mvn clean package'
        }
    }

    stage('Static Code Analysis') {
        steps {
            withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_AUTH_TOKEN')]) {
                sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=aws-devops-cicd \
                    -Dsonar.host.url=$SONAR_URL \
                    -Dsonar.login=$SONAR_AUTH_TOKEN
                """
            }
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
