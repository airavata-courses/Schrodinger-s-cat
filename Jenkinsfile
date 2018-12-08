pipeline {

    stages {
        stage('Build') {
            steps {
                sh 'sudo usermod -a -G docker $USER || true'

                sh 'sudo docker rmi scatpythonserver:latest || true'
                sh 'kubectl delete deployment deployment-search-server || true'
            }
        }
        stage('Database') {
            steps {
                sh 'kubectl create -f deployment-search-db.yaml'
                sh 'kubectl create -f service-search-db.yaml'
            }
        }
        stage('Search - Service') {
             steps {
                sh 'kubectl create -f deployment-search-server.yaml'
                sh 'kubectl create -f service-search-server.yaml'
            }
        }
    }
}
