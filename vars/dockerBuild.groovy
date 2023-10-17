// def call(String project , String ImageTag , String hubUser){
//     sh """
//         docker build -t ${hubUser}/${project} .
//         docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
//         docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
//     """
// }



def call(String aws_account_id , String region , String ecr_repo_name){
    sh """
        # Build your Docker image
docker build -t ${ecr_repo_name} .

# Tag the image with the ECR repository URL
docker tag ${ecr_repo_name}:latest ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest


    """
}

//Authenticate to ECR (Assuming you've already configured AWS CLI with necessary credentials)
// aws ecr get-login-password --region ${aws_region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com

//Push the tagged image to ECR
// docker push ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${ecr_repo_name}:latest