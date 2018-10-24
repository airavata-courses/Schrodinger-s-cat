pipeline {
    agent any

    stages 
    {
	stage('Building network') {
            steps {
                sh 'docker network create --driver overlay post_webnet || true'
            }
        }
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

        stage('React Front End') {
            steps {
               build job: 'Front end React JS'
            }
        }

        stage('Node Branch') {
            steps {
               build job: 'Node JS Server'
            }
        }
    }
}