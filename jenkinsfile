pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'docker swarm init || true'
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
