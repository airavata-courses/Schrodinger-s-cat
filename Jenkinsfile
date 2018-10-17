pipeline {
    agent any
    stages {
      stage('Stop Old Containers'){
        steps {
            sh 'sudo docker stop login_authenticaion_container || true && sudo docker rm -f login_authenticaion_container || true'
            sh 'sudo docker stop mysql_container || true && sudo docker rm -f mysql_container || true'
        }
     }
      stage(' Setup Java'){
        steps {
            sh 'sudo apt-get install oracle-java8-installer'
            sh 'export JAVA_HOME=$(/usr/lib/jvm/java-8-openjdk-amd64/)'
            sh 'export PATH=$JAVA_HOME/bin:$PATH'
        }
      }
      stage(' Setup MySql '){
        steps {
            sh 'sudo docker run -d --network host -p 3306 -e MYSQL_ROOT_PASSWORD=root --name mysql_container mysql:8.0.12 || true'
            sh 'sudo chmod +x database.sql'
            sh 'sudo docker exec -i mysql_container /usr/bin/mysql -uroot -proot < ./database.sql || true'
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
