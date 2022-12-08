def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    } 
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
            stage("Unit Test"){
            steps {
                script {
                    sh 'mvn clean test'
                }
            }
        }
         stage("SonarQube Testing and Scan") {
            environment {
                CI = 'true'
               scannerHome = tool 'sonarqube'
            }
            agent any
              steps {
                script {
                   //sh 'mvn clean install -Dmaven.test.skip=true'
                    gv.sonarScan()
                }
              } 
        } 
        stage("Push JAR to Nexus"){
            steps {
                script {
                    gv.pushToNexus()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
  

    }
}
