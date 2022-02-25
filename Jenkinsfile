pipeline {
    agent any

    stages {
        stage('Pull from GitHub') {
            steps {
                echo "Pulling from GitHub"
                git url: 'https://github.com/JSteveDev/SmartTraining.git'
            }
        }       
                     
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat "./gradlew sonarqube"
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
    }
}
