// def call(String project , String ImageTag , String hubUser){
    
        
//         withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//             sh "docker login -u '$USER' -p '$PASS' "
// }
//       sh " docker image push  ${hubUser}/${project}:${ImageTag}"
//       sh " docker image push ${hubUser}/${project}:latest"
    
// }

def call(String aws_account_id, String region, String ecr_repo_name) {
    // Tag the Docker image with the ECR repository URL
    sh "docker tag ${ecr_repo_name}:latest ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest"

    // Push the Docker image to the public ECR repository
    sh "docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest"
}

// Example usage:
// dockerImagePush('your_aws_account_id', 'your_aws_region', 'your_ecr_repo_name')


