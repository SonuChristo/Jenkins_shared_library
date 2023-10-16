def call(String project , String ImageTag , String hubUser){
    sh """
        
        withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
    sh "docker login -u '$USER' -p '$PASS'"
}
        docker image push  ${hubUser}/${project}:${ImageTag}
        docker image push ${hubUser}/${project}:latest
    """
}