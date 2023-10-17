//Jenkinsfile
def call(String project , String ImageTag , String hubUser){
    sh """
        trivy image ${hubUser}/${project}:latest > scan.txt
        cat scan.txt
    """
}

//jenkinsfile-ecr

def call(String aws_account_id , String region , String ecr_repo_name){
    sh """
        trivy image ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${ecr_repo_name}:latest > scan.txt
        cat scan.txt
    """
}



// def call(String aws_account_id , String region , String ecr_repo_name){
//     sh """
//         # Build your Docker image
// docker build -t ${ecr_repo_name} .

// # Tag the image with the ECR repository URL
// docker tag ${ecr_repo_name}:latest ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${ecr_repo_name}:latest


//     """
// }