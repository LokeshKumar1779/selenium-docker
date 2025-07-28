pipeline{
    agent any
    stages{
        stage('Build maven project'){
           steps {
                echo 'Building Maven project...'
                sh "mvn clean package -DskipTests"
           }
        }
        stage('Build docker image'){
            steps{
                echo 'Building Docker image...'
                sh "docker build -t kumarlokesh57/selenium ."
            }
        }
        stage('Push docker image'){
            environment {
                DOCKER_HUB_CREDS = credentials('dockerhub-creds')
            }
            steps{
                echo 'Logging in to Docker Hub...'
//                 sh "docker login -u ${DOCKER_HUB_CREDS_USR} -p ${DOCKER_HUB_CREDS_PSW}"
                sh "echo ${DOCKER_HUB_CREDS_PSW} | docker login -u ${DOCKER_HUB_CREDS_USR} --password-stdin"
                echo 'Pushing Docker image to Docker Hub...'
                sh "docker push kumarlokesh57/selenium"
            }
        }

    }
    post {
        always {
            echo 'Cleaning up...'
            sh 'docker logout || true'
            echo 'Removing Docker image...'
            sh 'docker rmi kumarlokesh57/selenium || true'
        }
    }

}