def call() {
    doError = '0'
    AWS_ACCOUNT_ID = 271251951598
    AWS_REGION = "us-east-2"
    DOCKER_REPO_BASE_URL = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
    DOCKER_REPO_NAME = """${sh(
                returnStdout: true,
                script: 'basename=$(basename $GIT_URL) && echo ${basename%.*}'
            ).trim()}"""
    HELM_CHART_GIT_REPO_URL = "https://gitlab.com/sq-ia/ref/msa-app/helm.git"
    HELM_CHART_GIT_BRANCH = "qa"
    GIT_USER_EMAIL = "pipelines@squareops.com"
    GIT_USER_NAME = "squareops"  
    DEPLOYMENT_STAGE = """${sh(
                returnStdout: true,
                script: 'echo ${GIT_BRANCH#origin/}'
            ).trim()}"""
    last_started_build_stage = ""   
    IMAGE_NAME="${DOCKER_REPO_BASE_URL}/${DOCKER_REPO_NAME}/stg"
    def BUILD_DATE = sh(script: "echo `date +%d_%m_%Y`", returnStdout: true).trim();
    def scannerHome = tool 'SonarqubeScanner';
}
