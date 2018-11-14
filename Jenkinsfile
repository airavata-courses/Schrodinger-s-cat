pipeline {
    agent {
     label 'masterNode'
    }
    stages {
        stage('Build') {
            steps {
		sh 'sudo usermod -a -G docker $USER || true'
                sh 'sudo docker swarm init || true'
                sh 'sudo docker stack rm reactserver || true'
            	sh 'sudo docker rmi scatreactserver:latest || true'
		}
        }
        stage('Image Build') {
            steps {
                sh 'sudo docker build -t scatreactserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh 'sudo docker stack deploy -c docker-compose.yml reactserver'
                // withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                //     sh "docker-compose up -d"
                // }
            }
            }
        }
    }
}
