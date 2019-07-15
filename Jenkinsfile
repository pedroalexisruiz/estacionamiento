pipeline{
	
		agent {
		label 'Slave_Induccion'
		}
	
        
		triggers {
        pollSCM('@daily')
		}
	
		tools {
		jdk 'JDK8_Centos' 
		gradle 'Gradle5.0_Centos' 
		}
	
		options {
			buildDiscarder(logRotator(numToKeepStr: '5'))
			disableConcurrentBuilds()
		}
		
		environment {
        PROJECT_PATH_BACK = 'Estacionamiento'
		}
		
		parameters{
			booleanParam defaultValue: false, description: 'Push a registry AWS', name: 'pushdeploy'
		}
		
		stages{
		
			stage('Checkout') {
				steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout([$class: 'GitSCM', branches: [[name: 'master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Estacionamiento']], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_pedroruiz', url: 'https://github.com/pedroalexisruiz/estacionamiento.git']]])
				}
			}
		
		
			stage('Compile'){
				parallel {
					stage('Compile backend'){
						steps{
							echo "------------>Compilación backend<------------"
							dir("${PROJECT_PATH_BACK}"){
								sh 'gradle --b ./build.gradle build -x test'
							}
						}
					
					}
				}
			}
			stage('Test Unitarios -Cobertura'){
				parallel {
					stage('Test- Cobertura backend'){
						steps {
							echo '------------>test backend<------------'
							dir("${PROJECT_PATH_BACK}"){
								sh 'gradle --b ./build.gradle test --debug'
								
							}
						}
					}
				}
			}
			
			stage('Sonar Analysis'){
				steps{
					echo '------------>Analisis de código estático<------------'
					  withSonarQubeEnv('Sonar') {
                     sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
                     }
				}
			}
		
		

		}
		post {
			failure {
				mail(to: 'pedro.ruiz@ceiba.com.co',
				body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
				subject: "ERROR CI: ${env.JOB_NAME}")
			}
		}	
			
}