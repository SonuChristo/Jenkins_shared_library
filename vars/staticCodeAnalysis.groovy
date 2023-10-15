def call(credentialsId) {
    withSonarQubeEnv(credentialsId: 'sonarqube-api') {
        sh 'mvn clean package sonar:sonar'
    }
}