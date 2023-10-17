def call(String project , String ImageTag , String hubUser){
    
        
        withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            sh "docker login -u '$USER' -p '$PASS' "
}
      sh " docker image push  ${hubUser}/${project}:${ImageTag}"
      sh " docker image push ${hubUser}/${project}:latest"
    
}

def call(String aws_account_id , String region , String ecr_repo_name){
    sh """
        # Build your Docker image
aws ecr get-login-password --region ${aws_region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com
# Tag the image with the ECR repository URL
docker push ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${ecr_repo_name}:latest
    """
}