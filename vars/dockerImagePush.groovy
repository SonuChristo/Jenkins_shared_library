// def call(String project , String ImageTag , String hubUser){
    
        
//         withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
//             sh "docker login -u '$USER' -p '$PASS' "
// }
//       sh " docker image push  ${hubUser}/${project}:${ImageTag}"
//       sh " docker image push ${hubUser}/${project}:latest"
    
// }

def call(String aws_account_id, String region, String ecr_repo_name) {
    withCredentials([[
        $class: 'AmazonWebServicesCredentialsBinding',
        accessKeyVariable: 'AWS_ACCESS_KEY_ID',
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY',
        credentialsId: 'YourAWSJenkinsCredentialID' // Replace with your Credential ID
    ]]) {
        // Authenticate Docker with AWS
        sh "aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com"

        // Tag and push the Docker image to ECR
        sh "docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest"
    }
}


// Example usage:
// dockerImagePush('your_aws_account_id', 'your_aws_region', 'your_ecr_repo_name')


