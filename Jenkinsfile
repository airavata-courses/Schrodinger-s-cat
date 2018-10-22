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
           sh 'sudo apt install openjdk-8-jre'
           sh 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/'
           sh 'export PATH=$JAVA_HOME/bin:$PATH'
       }
     }
     stage ('Install Postgres')
     stage{
        sh 'sudo apt update'
        sh 'sudo apt install postgresql postgresql-contrib'
        sh 'sudo -u postgres createuser -s postgres;'
     }
      //stage(' Setup MySql '){
        //steps {
          //  sh 'sudo docker run -d --network host -p 3306:3306  --name mysql_container mysql/mysql-server:8.0.12 -e MYSQL_ROOT_PASSWORD=root || true'
          //  sh 'sudo chmod +x database.sql'
            //sh 'sudo docker exec -i mysql_container /usr/bin/mysql -h 127.0.0.1 -P 3306 -uroot -proot < ./database.sql || true'
        //}
      //}
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
