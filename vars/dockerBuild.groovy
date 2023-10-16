def call(string projectName , string imageTag , string hubUser){
    sh """
        docker build -t ${hubUser}/${projectName} .
        docker image tag ${hubUser}/${projectName} ${hubUser}/${projectName}:${imageTag}
        docker image tag ${hubUser}/${projectName} ${hubUser}/${projectName}:latest
    """
}