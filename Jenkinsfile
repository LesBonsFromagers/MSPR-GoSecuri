pipeline {
    agent any

    //tools {
        // Install the Maven version configured as "M3" and add it to the path.
        //maven "M3"
    //}

    stages {
        stage('Git') {
            steps {
                // Get some code from a GitHub repository
                sh "pwd"
                sh "git clone 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git' temp"
                sh "mv -n temp/* ."
                sh "rm -rf temp"

                // Run Maven on a Unix agent.
                
                
                //sh "sshpass -p \"root\" ssh -p 22 -o StrictHostKeyChecking=no root@192.168.1.170"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') {
                //    bat "mvn -Dmaven.test.failure.ignore=true clean"
                //}
                
            }
        }
        
        stage('Copy') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'
                
                // Run Maven on a Unix agent.
                //sh "mvn clean"
                sh "pwd"
                sh "sshpass -v -p \"root\" scp -v -o \"StrictHostKeyChecking=no\" -r /var/lib/jenkins/workspace/MSPRFolder/Pipeline-MSPR/ root@192.168.1.170:/mnt/www/html"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') 
                //{
                //    bat "mvn -Dmaven.test.failure.ignore=true package"
                //}
                
            }
        }
        
        stage('Compilation') 
        {
            steps 
            {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/LesBonsFromagers/MSPR-GoSecuri.git'

                // Run Maven on a Unix agent.
                //sh "mvn clean"
                sh "pwd"
                sh "sshpass -v -p \"root\" ssh -v -o \"StrictHostKeyChecking=no\" root@192.168.1.170 'pwd; javac /mnt/www/html/Pipeline-MSPR/src/main/java/com/epsi/gosecuri/Main.java; java /mnt/www/html/Pipeline-MSPR/target/classes/com/epsi/gosecuri/Main.class' "
                
                //sh "rsync --rsh=\"sshpass -p \"root\" ssh -o StrictHostKeyChecking=no root@192.168.1.170 \" /root/.git/ /backup/"

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
