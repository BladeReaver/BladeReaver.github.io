pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'mvn clean install'
        sh 'mvn clean install -Pit'
      }
    }

    stage('Static code analysis') {
      steps {
        sh 'mvn sonar:sonar'
        sleep 10
        sh 'check sonar quality gate'
      }
    }

    stage('Create deployable') {
      steps {
        sh '''mvn release:prepare
mvn release:perform'''
      }
    }

    stage('Deploy on TST ') {
      steps {
        sh '// Trigger deployment'
      }
    }

    stage('Automated tests against TST deployment') {
      steps {
        sh 'java -jar automated-tests.jar'
      }
    }

    stage('Deploy on ACC') {
      steps {
        sh '// Trigger ACC deployment'
      }
    }

    stage('Automated regression tests against ACC') {
      steps {
        sh 'java -jar automated-regression-tests.jar'
      }
    }

    stage('Create PRD change/release') {
      steps {
        sh '// Create change in some tool'
      }
    }

    stage('Manual gate') {
      steps {
        input 'Approved for deployment'
      }
    }

    stage('Deploy on PRD') {
      steps {
        sh '// Start production deployment'
      }
    }

  }
}