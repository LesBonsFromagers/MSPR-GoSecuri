pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Clean') {
            steps {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'

                // Run Maven on a Unix agent.
                sh "mvn clean"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') {
                //    bat "mvn -Dmaven.test.failure.ignore=true clean"
                //}
                
            }
        }
        
        stage('Package') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'
                
                // Run Maven on a Unix agent.
                sh "mvn clean"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') 
                //{
                //    bat "mvn -Dmaven.test.failure.ignore=true package"
                //}
                
            }
        }
        
        stage('Install') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'

                // Run Maven on a Unix agent.
                sh "mvn clean"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') 
                //{
                //    bat "mvn -Dmaven.test.failure.ignore=true install"
                //}
                
            }
        }
        
        stage('Test') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'

                // Run Maven on a Unix agent.
                sh "mvn clean"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') 
                //{
                //    bat "mvn -Dmaven.test.failure.ignore=true test"
                //}
                
            }
        }
    }
}
