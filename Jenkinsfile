pipeline {
    agent {
     label 'masterNode'
    }

    stages {
        stage('Removing old') {
            steps {
                sh 'usermod -aG docker $USER || true'
                sh 'sudo docker swarm init || true'
                //sh 'sudo docker stack rm rabbitserver || true'
		       // sh 'sudo docker rmi rabbit:latest || true'
                sh 'sudo docker stack rm consumer || true'
            }
        }
        stage('Build') {
            steps {
                //sh 'sudo docker build -t scatrabbitserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    //sh "sudo docker stack deploy -c docker-compose.yml rabbitserver"
                // withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                //     sh "docker-compose up -d"
                // }
            }
            }
        }
        stage (' start the consumer microservice'){
        steps{
          sh 'sudo bash ./run_docker.sh'
        }
        }

    }
}
