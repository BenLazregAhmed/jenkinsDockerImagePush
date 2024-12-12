node {

    stage('checkout') {
        checkout scm
    }

    stage('Maven package') {
        sh 'mvn clean package'
    }

    stage('docker image build') {
        app = docker.build("ahmedevops/mvn-docker-jenkins:${env.BUILD_ID}")
    }

    stage('Test Image') {
            app.withRun('-p 8081:8081') { c ->
                sh 'docker ps'
                sh 'sleep 30s'
                sh 'curl localhost:8081'
            }
        }

    stage('Push Image') {
           docker.withRegistry('https://index.docker.io/v1/', 'docker-hub1') {
                app.push()
                app.push('latest')
           }
       }

    stage('Deploy'){
        sh 'docker run -d --rm --name web -d -p 8081:8081 ahmedevops/mvn-docker-jenkins'
        sh 'sleep 180s'
        sh 'docker container stop web'
    }
}