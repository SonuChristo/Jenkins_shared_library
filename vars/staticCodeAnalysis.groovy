def staticCodeAnalysis(credentialsId) {
    withSonarQubeEnv(credentialsId: credentialsId) {
        sh 'mvn clean package sonar:sonar'
    }
}