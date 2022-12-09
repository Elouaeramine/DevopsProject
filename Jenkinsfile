#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("SonarQube Testing and Scan") {
            environment {
                CI = 'true'
                scannerHome = tool 'sonarqube'
            }
            agent{ docker { image 'maven'}  }
              steps {
                withSonarQubeEnv(installationName: 'sonarqube', credentialsId: 'Sonarqube Token') {
                    sh 'mvn clean package sonar:sonar'
                }
              }
        }

    }

}
