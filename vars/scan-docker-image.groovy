def call() {      

              last_started = env.STAGE_NAME
              echo 'Scan with trivy'    
              unstash 'image'          
              sh '''
              apk add jq
              trivy image --ignore-unfixed -f json -o scan-report.json --input build/${DOCKER_REPO_NAME}-${BUILD_NUMBER}.tar
              '''
              echo 'archive scan report'
              archiveArtifacts artifacts: 'scan-report.json'
              echo 'Docker Image Vulnerability Scanning'
              high = sh (
                   script: 'cat scan-report.json | jq .Results[].Vulnerabilities[].Severity | grep HIGH | wc -l',
                   returnStdout: true
              ).trim()
              echo "High: ${high}"
             
             critical = sh (
                  script: 'cat scan-report.json | jq .Results[].Vulnerabilities[].Severity | grep CRITICAL | wc -l',
                   returnStdout: true
              ).trim()
              echo "Critical: ${critical}"
}
