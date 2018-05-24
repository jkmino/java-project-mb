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
        sh "mkdir /var/www/html/rectangle/all/${env.BRANCH_NAME}"
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangle/all/${env.BRANCH_NAME}/"
      }
      post {
        always {
          archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
        }
      }
    }

    stage('Test On DEV-SERVER'){
      agent {
        label 'production'
      }
      steps {
        sh "wget http://jcamino1.mylabserver.com/rectangle/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
      }
    }
    stage('Test On Debian'){
      agent {
        docker 'openjdk:8u121-jre'
      }
      steps {
        sh "hostname"
        sh "wget http://jcamino1.mylabserver.com/rectangle/all/${env.BRANCH_NAME}/rectangle_${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 3 4"
      }
    }
    stage('Promote to production path') {
      agent {
        label 'development'
      }
      when {
        branch 'master'
      }
      steps{
        sh "cp /var/www/html/rectangle/all/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangle/green/rectangle_${env.BUILD_NUMBER}.jar "
      }
    }
    stage ('Promote Development Branch to master'){
      agent {
        label 'development'
      }
      when {
        branch 'development'
      }
      steps{
        echo "Staching Any local Changes"
        sh "git stash"
        echo "checking Out Development branch"
        sh "git checkout master"
        echo "merge developmet into master branch"
        sh "git merge development"
        echo "Pushing to Origin Master"
        sh "git push origin master"
      }
    }

  }
}
