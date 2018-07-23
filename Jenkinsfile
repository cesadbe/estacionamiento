pipeline {
    //where and how to execute the Pipeline
    agent {
        label 'Slave5'
    }
    //agent none
    //agent any
    /*agent {
        node {
          label 'labelName'
        }
      }*/

    //Using environment variables
    environment {
        CC = 'clang'
    }

    //configuring Pipeline-specific options from within the Pipeline itself
    options {
        buildDiscarder(logRotator(numToKeepStr: '3')) //Persist artifacts and console output for the specific number of recent Pipeline runs.
        disableConcurrentBuilds() //Disallow concurrent executions of the Pipeline
        timestamps() //Prepend all console output generated by the Pipeline run with the time at which the line was emitted
    }

    //provides a list of parameters which a user should provide when triggering the Pipeline.
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        //booleanParam(name: 'DEBUG_BUILD', defaultValue: true, description: '')
    }

    //A section defining tools to auto-install and put on the PATH
    tools {
        jdk 'JDK8_Centos'
        gradle 'Gradle4.5_Centos'
    }

    triggers {
        pollSCM('@hourly')
        //cron('H */4 * * 1-5')
    }

	stages{

        stage('Print Enviroment   ') {
            steps{
                echo "------------>Print<------------"
                echo "Hello ${params.PERSON}, su nombre es un parámetro"
                echo "This is Enviroment: ${env.CC}"
                //execute a shell command in a Pipeline
                sh 'gradle --version'
                sh 'java -version'
                //execute a shell multiline command in a Pipeline
                sh '''
                    echo "Multiline shell steps works too prueba"
                    ls -lah
                '''
            }
        }

        stage('Checkout') {
            steps{
                echo "------------>Checkout<------------"
                git branch: 'develop', credentialsId: '7fe28495-6f45-4577-8c7b-dce727e78f14', url: 'git@git.ceiba.com.co:dllo/ceiba_prueba_concepto_PT.git'
                sh 'gradle clean'
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
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-projectDev.properties"
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

        stage('Publish') {
            steps{
                echo '------------>Publish [Artifactory]<------------'
                script{ //takes a block of Scripted Pipeline and executes that in the Declarative Pipeline
                    def server = Artifactory.server 'ar7if4c70ry@c318a'
                    def uploadSpec = '''
                        {"files": [{
                        "pattern": "**/build/libs/*.jar",
                        "target": "libs-release-local/$JOB_NAME/build/"
                        }]}'''

                    def buildInfo = server.upload(uploadSpec)
                    server.publishBuildInfo(buildInfo)
                }
            }
        }

        stage('Deploy') {
            steps {
                //Asking for human input to proceed
                //input "Deploy?"
                echo "------------>Deploying<------------"
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

        }
        failure {
          echo 'This will run only if failed'
          //send notifications about a Pipeline to an email
          mail (to: 'yuliana.canas@ceiba.com.co',
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
          mail (to: 'yuliana.canas@ceiba.com.co',
               subject: "Changed State Pipeline: ${currentBuild.fullDisplayName}",
               body: "The state of the Pipeline has changed. See ${env.BUILD_URL}")
        }
    }
}
