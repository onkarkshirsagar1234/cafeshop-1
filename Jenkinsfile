pipeline{
  agent any
  stages{
    stage('Clone Repository') {
            steps {
                git 'https://github.com/onkarkshirsagar1234/cafeshop-1.git'
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }
  }
}
