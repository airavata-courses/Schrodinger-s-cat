pipeline {
    agent {
     //label 'masterNode'
        label 'searchslave'
    }

    stages {
        stage('Build') {
            steps {
                sh 'sudo usermod -a -G docker $USER || true'
                //sh 'sudo docker swarm init || true'
                sh 'sudo docker stack rm pythonserver || true'
                sh 'sudo docker rmi scatpythonserver:latest || true'
            }
        }
        stage('Test') {
            steps {
                sh 'sudo docker stack deploy -c docker-compose-postgres.yml postgres'
                sh 'echo "Sleeping for 3minutes"'
                sh 'sleep 180'
                sh 'sudo docker build -t scatpythonserver .'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh 'sudo docker stack deploy -c docker-compose.yml pythonserver'
                // withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                //     sh "docker-compose up -d"
                // }
            }
            }
        }
    }
}
