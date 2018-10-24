pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker swarm init || true'
                sh 'docker stack rm nodeserver || true'
                sh 'docker network create -d overlay post_webnet || true'
            }
        }
        stage('Image Build') {
            steps {
                sh 'docker build -t scatnodeserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh "docker stack deploy -c docker-compose.yml nodeserver"
            }
            }
        }
    }
}