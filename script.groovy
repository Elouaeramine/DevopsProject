
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
nexusArtifactUploader artifacts: [[artifactId: 'devops', classifier: '', file: 'target/devops-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-credentials', groupId: 'org.springframework.boot', nexusUrl: '172.23.0.3:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
}

def sonarScan() {
    echo 'Running sonarScan'
    sh 'mvn test'
    withSonarQubeEnv('sonarqube'){
      sh 'mvn clean verify sonar:sonar -D sonar.projectKey=sonarqube -D maven.test.skip=true -D sonar.login=sqa_0dcedeac04b069fcd3db74fb3c3a4169e123b434'
    }
  }

return this
