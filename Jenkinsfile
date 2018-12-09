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
                sh 'sudo docker stack rm reactserver || true'
            	sh 'sudo docker rmi nawazkh/frontend:latest || true'
		}
        }
        stage('Image Build') {
            steps {
                sh 'sudo docker build -t nawazkh/frontend:latest .'
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
