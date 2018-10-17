pipeline {
    agent any
    stages {
      stage(' Setup Java'){
        steps {
            sh 'sudo apt-get install oracle-java8-installer'
            sh 'export JAVA_HOME=$(/usr/lib/jvm/java-8-openjdk-amd64/)'
            sh 'export PATH=$JAVA_HOME/bin:$PATH'
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
