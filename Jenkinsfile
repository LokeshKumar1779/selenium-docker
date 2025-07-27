pipeline{
    agent any
    stages{
        stage('Build maven project'){
           steps {
                sh "mvn clean package -DskipTests"
           }
        }
        stage('Build docker image'){
            steps{
                sh "docker build -t kumarlokesh57/selenium ."
            }
        }
        stage('Push docker image'){
            steps{
                sh "docker push kumarlokesh57/selenium"
            }
        }
    }

}