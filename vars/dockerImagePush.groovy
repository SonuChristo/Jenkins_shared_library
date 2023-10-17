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
        secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        // Get the AWS ECR authorization token
        def ecr = AmazonECRClientBuilder.standard().withRegion(region).build()
        def authData = ecr.getAuthorizationToken().authorizationData.first()
        def token = authData.authorizationToken.decodeBase64().toString()
        def registry = authData.proxyEndpoint
        
        // Use the AWS CLI to authenticate Docker to your ECR registry
        sh "aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${registry}"
        
        // Tag and push the Docker image to ECR
        sh "docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest"
    }
}
