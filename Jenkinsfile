pipeline {
    agent any
    stages {
        stage('Remove old Docker containers') {
            steps {
                sh 'sudo docker rm -f login_authenticaion_container || true'
                sh 'sudo docker rmi nawazkh/maven:3.5.4 || true'
            }
        }
        stage(' Login Authentication Up '){
            steps {
                sh 'run_docker.sh'
            }
        }
        stage(' Login Authentication Deployed '){
            steps {
                sh 'echo "DONE"'
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
