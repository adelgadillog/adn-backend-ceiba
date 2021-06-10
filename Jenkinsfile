pipeline {
  
  agent {
    label 'Slave4_Induccion'
  }

 
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	disableConcurrentBuilds()
  }
	environment {
        PROJECT_PATH_BACK = './microservicio/'
	}
  
  tools {
    jdk 'JDK8_Centos'
	gradle 'Gradle5.0_Centos' 
  }

  stages{
    stage('Checkout') {
      steps{
        echo '------------>Checkout<------------'
		checkout([
			$class: 'GitSCM', 
			branches: [[name: '*/master']], 
			doGenerateSubmoduleConfigurations: false, 
			extensions: [], 
			gitTool: 'Default', 
			submoduleCfg: [], 
			userRemoteConfigs: [[
			credentialsId: 'GitHub_adelgadillog', 
			url:'https://github.com/adelgadillog/adn-backend-ceiba.git'
			]]
		])

      }
    }
    
    


    

    stage('Build') {
      steps {
		dir("${PROJECT_PATH_BACK}")
			{
				sh 'gradle build -x test'
			}

      }
    }  
	
  }


  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
	  mail (to: 'alejandro.delgadillo@ceiba.com.co',subject: "Success Pipeline:${currentBuild.fullDisplayName}",body: "Success build ${env.BUILD_URL}")
    }
    failure {
      echo 'This will run only if failed'
		mail (to: 'alejandro.delgadillo@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")

    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
        
