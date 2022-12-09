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
        /*stage("SonarQube Testing and Scan") {
          steps {
            script {
                gv.sonarScan()
            }
          }
        }*/

        stage("Push JAR to Nexus"){
            steps {
                script {
                    gv.pushToNexus()
                }
            }
        }

    }

}
