pipeline {
  agent none

options {
  buildDiscarder (logRotator(numToKeepStr:'2',artifactNumToKeepStr: '1'))
}

  stages {
    stage ('Unit Tests'){
      agent {
        label 'development'
      }
      steps {
        sh 'ant -f test.xml -v'
        junit 'reports/result.xml'
      }
    }
    stage('build'){
      agent {
        label 'development'
      }
      steps {
        sh 'ant -f build.xml -v'
      }

    }
    stage('deploy'){
      agent {
        label 'development'
      }
      steps {
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangle/all"
      }
      post {
        always {
          archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
        }
      }
    }

    stage('Running On DEV-SERVER'){
      agent {
        label 'production'
      }
      steps {
        sh "wget http://jcamino1.mylabserver.com/rectangle/all/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
      }
    }

  }
}
