pipeline {
    agent agent

    stages 
    {
        stage('Java and postgres') {
            steps {
                build job: 'Login Authentication 1'
            }
        }

        stage ('Python Search Branch') {
            steps {
                build job: 'Python Search Branch'
            }
        }

        stage('End') {
            steps {
                sh 'ls'
            }
        }
    }
}