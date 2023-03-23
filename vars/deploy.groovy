def call(){
             withCredentials([gitUsernamePassword(credentialsId: 'jenkins-demo', gitToolName: 'Default')]) {
             last_started = env.STAGE_NAME
               sh '''
               git clone -b $HELM_CHART_GIT_BRANCH $HELM_CHART_GIT_REPO_URL
               git config --global user.email $GIT_USER_EMAIL
               git config --global user.name $GIT_USER_NAME
               cd helm
               yq e -i '(."'${DOCKER_REPO_NAME}'".image.repository = "'${DOCKER_REPO_BASE_URL}'/'${DOCKER_REPO_NAME}'/'${DEPLOYMENT_STAGE}'")' env/${DEPLOYMENT_STAGE}/values.yaml
               yq e -i '(."'${DOCKER_REPO_NAME}'".image.tag = "'${BUILD_NUMBER}-${BUILD_DATE}'")' env/${DEPLOYMENT_STAGE}/values.yaml
               git add .
               git commit -m 'Docker Image version Update "'$JOB_NAME'"-"'$BUILD_NUMBER-$BUILD_DATE'"'
               git push origin $HELM_CHART_GIT_BRANCH
           '''
}
