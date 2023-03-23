def call(){

              echo 'push to ecr step start'
              if ( "$high" < 500 && "$critical" < 80 ) {
                withAWS(credentials: 'jenkins-demo-aws') {  
                sh '''                                   
                /kaniko/executor --dockerfile Dockerfile  --context=`pwd` --destination=${IMAGE_NAME}:${BUILD_NUMBER}-${BUILD_DATE}
                '''               
                }   
              } 
              else {
                echo "The Image can't be pushed due to too many vulnerbilities"
                exit
              }    
}
