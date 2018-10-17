pipeline {
    agent any
    stages {
        stage('Remove old Docker containers') {
            steps {
                sh 'sudo docker rm -f login_authenticaion_container'
                sh 'sudo docker rmi nawazkh/maven:3.5.4'
            }
        }
        stage(' Installing Maven '){
            steps {
                sh 'echo "---------removed old containers and images if existed---------"'
                sh 'sudo docker build -f Dockerfile -t nawazkh/maven:3.5.4 .'
                sh 'echo "---------built docker image for maven ---------"'
            }
        }
        stage(' Developing the jar '){
            steps{
                sh 'cd restful-user-authentication'
                sh 'sudo docker run -it --rm -v "$PWD":/app -w /app --network host \
                -p 8888:8888 \
                nawazkh/maven:3.5.4 mvn clean install'
                sh 'echo "--------- created the jar for the project ---------"'
            }
        }
        stage(' getting the services up '){
            steps{
                sh 'docker-compose up -d'
                sh 'echo "--------- login module started ---------" '
            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}
