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
          steps {
              script {
                  gv.sonarScan()
                  sh 'echo sonarqube'
                }
            }
          }
          /*environment {
                CI = 'true'
                scannerHome = tool 'sonarqube'
            }
           // agent{ docker { image 'maven'}  }
              steps {
                script {
                   // sh 'mvn clean install -Dmaven.test.skip=true'
                    gv.sonarScan()
                }
              }*/
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
