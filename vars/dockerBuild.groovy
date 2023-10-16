def call(String project , String ImageTag , String hubUser){
    sh """
        docker build -t ${hubUser}/${project} .
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
    """
}



// def call(String aws_account_id , String  , String hubUser){
//     sh """
//         docker build -t ${hubUser}/${project} .
//         docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
//         docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
//     """
// }