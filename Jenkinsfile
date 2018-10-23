pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker swarm init || true'
                sh 'docker stack rm pythonserver || true'
            }
        }
        stage('Test') {
            steps {
                sh 'docker build -t scatpythonserver .'
                echo 'Build Complete'
            }
        }
        stage('Deploy') {
            steps {

                script{
                    sh "docker stack deploy -c docker-compose.yml pythonserver"
                // withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                //     sh "docker-compose up -d"
                // }
            }
            }
        }
    }
}
