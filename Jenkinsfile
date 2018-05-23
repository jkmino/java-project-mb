pipeline {
  agent any


  stages {
    stage('build'){
      agent {
        label 'Linux'
      }
      steps {
        sh 'ant -f build.xml -v'
      }
    }

  }

}
