pipeline {
    agent {
     label 'masterNode'
    }

    stages {
        stage('Build') {
            steps {
                sh 'docker swarm init || true'
                sh 'docker stack rm rabbitserver || true'
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t scatrabbitserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh "docker stack deploy -c docker-compose.yml rabbitserver"
                // withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                //     sh "docker-compose up -d"
                // }
            }
            }
        }
    }
}
