def call(){
              last_started = env.STAGE_NAME
              echo 'Build start'              
              sh '''/kaniko/executor --dockerfile Dockerfile  --context=`pwd` --destination=${IMAGE_NAME}:${BUILD_NUMBER}-${BUILD_DATE} --no-push --oci-layout-path `pwd`/build/ --tarPath `pwd`/build/${DOCKER_REPO_NAME}-${BUILD_NUMBER}.tar
              '''                
              stash includes: 'build/*.tar', name: 'image'  
}
