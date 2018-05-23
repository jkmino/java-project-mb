pipeline {
  agent CentOS


  stages {
    stage('build'){
      agent {
        label 'CentOS'
      }
        steps {
          sh 'ant -f build.xml -v'
        }
    }

  }
  
}
