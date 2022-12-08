
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t sarahbenabdallah/devopspipeline:spring-app-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push sarahbenabdallah/devopspipeline:spring-app-1.0'
    }
}

def pushToNexus() {
    echo "pushing the jar file to Nexus maven-snapshots repo..."
    sh 'mvn clean install -Dmaven.test.skip=true'
nexusArtifactUploader artifacts: [[artifactId: 'devops', classifier: '', file: 'target/devops-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-credentials', groupId: 'org.springframework.boot', nexusUrl: '20.13.123.42:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
}

def sonarScan() {
        echo "Running sonarQube scan..."
        withSonarQubeEnv('sonarqube') {
           // sh "${scannerHome}/bin/sonar-scanner"
              sh "mvn clean verify sonar:sonar -Dsonar.projectKey=sonarqube -Dmaven.test.skip=true"
        }
       /* timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        } */
        
}


return this
