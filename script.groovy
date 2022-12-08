
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t amineelouaer/spring:spring-app-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push amineelouaer/spring:spring-app-1.0'
    }
}

def pushToNexus() {
    echo "pushing the jar file to Nexus maven-snapshots repo..."
    sh 'mvn clean install -Dmaven.test.skip=true'
nexusArtifactUploader artifacts: [[artifactId: 'devops', classifier: '', file: 'target/devops-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-credentials', groupId: 'org.springframework.boot', nexusUrl: '172.17.0.3:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
}

def sonarScan() {
    echo 'Running sonarScan'
    sh 'mvn test'
    sh 'mvn sonar:sonar -D sonar.login=sqa_c963c83af8043493533514698983e626ce36d595'
  }

return this
