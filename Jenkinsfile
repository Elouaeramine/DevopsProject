#!/usr/bin/env groovy

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
        stage("SonarQube Testing and Scan") {
            environment {
                CI = 'true'
                scannerHome = tool 'sonarqube'
            }
            agent{ docker { image 'maven'}  }
              steps {
                script {
                    gv.sonarTest()
                }
              }
        }

    }

}
