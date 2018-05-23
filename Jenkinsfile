pipeline {
  agent any


  stages {
    stage('build'){
      agent {
        label 'apache'
      }
      steps {
        sh 'ant -f build.xml -v'
      }
    }

  }

}
