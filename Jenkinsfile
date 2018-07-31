pipeline {

	//Donde se va a ejecutar el Pipeline  
	agent {
		label 'Slave_Induccion'
	}  
	
	//Opciones específicas de Pipeline dentro del Pipeline  
	
	options {
		//Mantener artefactos y salida de consola para el # específico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '3'))
		
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()
	}


    //A section defining tools to auto-install and put on the PATH
    tools {
        jdk 'JDK8_Centos'
        gradle 'Gradle4.5_Centos'
    }

	stages{

        stage('Checkout') {
            steps{
                echo "------------>Checkout<------------"
                checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_cesadbe', url:'https://github.com/cesadbe/estacionamiento']]])
            }
        }

        stage('Unit Tests') {
            steps{
                echo "------------>Unit Tests<------------"
                sh 'gradle test'
                junit '**/build/test-results/test/*.xml' //aggregate test results - JUnit
                jacoco classPattern: '**/build/classes/java', execPattern: '**/build/jacoco/test.exec', sourcePattern: '**/src/main/java'
            }
        }

        stage('Build') {
            steps {
                echo "------------>Build<------------"
                sh 'gradle build'
            }
        }

        stage('Static Code Analysis') {
            steps{
                echo '------------>Analisis de codigo estatico<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
                }
            }
        }

    	stage('Parallel Stage') {
    	    parallel {
    	        stage('Branch A') {
    	            steps {
    	                echo "------------>On Branch A<------------"
    	                sh 'echo "Este Nodo es: Centos =)"'
    	            }
    	       }
    	       stage('Branch B') {
    	           steps {
    	               echo "------------>On Branch Be<------------"
    	           }
    	       }
    	   }
    	}

	}

    post {
        always {
          echo 'This will always run'
          publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'buildSrc/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - buildSrc'
          ]
        publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'services/webservice/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - services/webservice'
          ]
        publishHTML target: [
            allowMissing: true,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'shared/build/reports/tests/test',
            reportFiles: 'index.html',
            reportName: 'Tests Report - shared'
          ]
        }
        success {
          echo 'This will run only if successful'
          junit '**/build/test-results/test/*.xml'
        }
        failure {
          echo 'This will run only if failed'
          //send notifications about a Pipeline to an email
          mail (to: 'cesar.beltran@ceiba.com.co',
               subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
               body: "Something is wrong with ${env.BUILD_URL}")
        }
        unstable {
          echo 'This will run only if the run was marked as unstable'
        }
        changed {
          echo 'This will run only if the state of the Pipeline has changed'
          echo 'For example, if the Pipeline was previously failing but is now successful'
          //send notifications about a Pipeline to an email
          mail (to: 'cesar.beltran@ceiba.com.co',
               subject: "Changed State Pipeline: ${currentBuild.fullDisplayName}",
               body: "The state of the Pipeline has changed. See ${env.BUILD_URL}")
        }
    }
}
