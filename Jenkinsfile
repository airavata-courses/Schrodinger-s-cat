pipeline {
    agent {
     //label 'masterNode'
	label 'searchslave'
    }
    stages {
        stage('Build') {
            steps {
		sh 'sudo usermod -a -G docker $USER'
                //sh 'sudo docker swarm init || true'
                sh 'sudo docker stack rm nodeserver || true'
           	sh 'sudo docker rmi scatnodeserver:latest || true'
		 }
        }
        stage('Image Build') {
            steps {
                sh 'sudo docker build -t scatnodeserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo docker stack deploy -c docker-compose.yml nodeserver'
            }
        }
    }
}
