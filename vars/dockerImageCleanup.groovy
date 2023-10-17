// Jenkinsfile
// def call(String project , String ImageTag , String hubUser){
//     sh """
//         docker rmi ${hubUser}/${project}:${ImageTag}
//         docker rmi ${hubUser}/${project}:latest
//     """
// }

// Jenkinsfile-ecr

def call(String aws_account_id , String region , String ecr_repo_name){
    sh """
# Tag the image with the ECR repository URL
docker rmi ${ecr_repo_name}:latest ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest


    """
}