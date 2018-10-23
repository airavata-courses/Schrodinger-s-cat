pipeline {
    agent any
    stages {
      stage('Stop Old Containers'){
        steps {
        sh 'sudo docker stack rm login_authenticaion_service || true'
        sh 'sudo docker stack rm post || true'
            //sh 'sudo docker stop login_authenticaion_container || true && sudo docker rm -f login_authenticaion_container || true'
            //sh 'sudo docker stop post || true && sudo docker rm -f post || true''
        }
     }
     stage('Start Postgres network'){
        steps{
            sh 'sudo docker swarm init'
            sh 'sudo docker stack deploy -c docker-stack-postgresql.yml post'

            }
     }
     stage(' Setup Java'){
       steps {
           sh 'sudo apt update || true'
           sh 'sudo apt install openjdk-8-jdk || true'
           sh 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/ || true'
           sh 'export PATH=$JAVA_HOME/bin:$PATH || true'
       }
     }
     stage ('Install Postgres'){
        steps{
            sh 'sudo apt update || true'
            sh 'sudo apt install postgresql postgresql-contrib || true'
            sh 'sudo -u postgres createuser -s postgres || true'
            sh 'sudo docker network ls || true'
            }
     }
     //stage(' Login Authentication Up '){
        //steps {
        //    sh 'sudo bash ./run_docker.sh'
        //    }
        //}
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
