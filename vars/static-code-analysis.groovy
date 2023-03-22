def call(){
            echo "NodejsScan"
            nodejsscan --directory `pwd`
          
//          echo 'SonarQube Analysis'
//          echo '${scannerHome}'
//          '${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${DOCKER_REPO_NAME}-${DEPLOYMENT_STAGE} -Dsonar.qualitygate=my_custom_quality_gate''
//         }
// Sonarqube Quality Gate
//         echo 'SonarQube Quality gate'
//         timeout(time: 1, unit: 'HOURS') {
//         waitForQualityGate abortPipeline: true
//    }
// git-secret scan
//             echo 'git secret scanning'
//             git config --global --add safe.directory /home/jenkins/agent/workspace/${DOCKER_REPO_NAME}
//             git secrets --register-aws
//             git-secrets --scan
//             
//         }
//     }
}
