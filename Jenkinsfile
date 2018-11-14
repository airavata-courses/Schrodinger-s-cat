pipeline {
    agent {
     label 'searchslave'
    }
    stages {
        stage('Build') {
            steps {
		sh 'sudo usermod -a -G docker $USER'
                sh 'sudo docker swarm init || true'
                sh 'sudo docker stack rm nodeserver || true'
           	sh 'sudo docker rmi scatnodeserver:latest || true'
		 }
        }
        stage('Image Build') {
            steps {
                sh 'sudo docker build -t scatnodeserver .'
                echo 'sudo Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh "sudo docker stack deploy -c docker-compose.yml nodeserver"
            }
            }
        }
    }
}
