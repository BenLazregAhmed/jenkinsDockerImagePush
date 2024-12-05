node {

    stage('checkout') {
        checkout scm
    }

    stage('Maven package') {
        sh 'mvn clean package'
    }

    stage('docker image build') {
        app = docker.build("ahmedevops/MvnDockerJenkins:${env.BUILD_ID}")
    }

    stage('Test Image') {
            app.withRun('-p 8081:8081') { c ->
                sh 'docker ps'
                sh 'curl localhost:8081'
            }
        }

    stage('Push Image') {
           docker.withRegistry('https://index.docker.io/v1/', 'docker-hub1') {
                app.push()
                app.push('latest')
           }
       }

    stage('pull'){
        sh 'docker run --name web -d ahmedevops/MvnDockerJenkins'
    }
}