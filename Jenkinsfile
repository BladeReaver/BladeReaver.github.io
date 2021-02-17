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
        sh 'mvn -U clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report'
        echo 'mvn sonar:sonar'
        sleep 10
        echo 'Quality gate approved'
      }
    }

    stage('Create deployable') {
      steps {
        echo 'mvn release:clean clean'
        echo 'mvn -B release:prepare'
        echo 'mvn -B release:perform'
      }
    }

    stage('Deploy on TST') {
      steps {
        echo 'Deploy on TST'
        echo 'Deployed!'
      }
    }

    stage('Automated tests on TST') {
      steps {
        echo 'Run automated tests against deployed application on TST'
      }
    }

    stage('Deploy on ACC') {
      steps {
        echo 'Deploy on ACC'
        echo 'Deployed!'
      }
    }

    stage('Automated regression tests on ACC') {
      steps {
        echo 'Run automated tests against deployed applicaiton on ACC'
      }
    }

    stage('Create PRD change') {
      steps {
        echo 'Change created'
      }
    }

    stage('Manual gate') {
      steps {
        input 'Approved for PRD?'
      }
    }

    stage('Deploy on PRD') {
      steps {
        echo 'Deployed on PRD'
        echo 'Deployed!'
      }
    }

  }
}