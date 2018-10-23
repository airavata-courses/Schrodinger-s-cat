pipeline {
    agent any
    stages {
      stage('Stop Old Containers'){
        steps {
        sh 'docker stack rm login_authenticaion_service || true'
        sh 'docker stack rm post || true'
            //sh 'sudo docker stop login_authenticaion_container || true && sudo docker rm -f login_authenticaion_container || true'
            //sh 'sudo docker stop post || true && sudo docker rm -f post || true''
        }
     }
     stage(' Setup Java'){
       steps {
           sh 'sudo apt update'
           sh 'sudo apt install openjdk-8-jdk'
           sh 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/'
           sh 'export PATH=$JAVA_HOME/bin:$PATH'
       }
     }
     stage ('Install Postgres'){
        steps{
            sh 'sudo apt update'
            sh 'sudo apt install postgresql postgresql-contrib'
            sh 'sudo -u postgres createuser -s postgres;'
            }
     }
     stage(' Login Authentication Up '){
        steps {
            sh 'sudo bash ./run_docker.sh'
            }
        }
    }
    post {
        always {
            echo 'Building pipeline...'
        }
        success {
            echo 'Login Module up'
        }
        failure {
            echo 'Login Module Could not be deployed'
        }
        unstable {
            echo 'Run is marked as unstable'
        }
        changed {
            echo 'Change in the pipeline detected'
        }
    }
}
