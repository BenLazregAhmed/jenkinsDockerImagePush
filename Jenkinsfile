node {
    withCredentials([string(credentialsId: 'RENDER_DEPLOY_WEBHOOK_URL', variable: 'RENDER_DEPLOY_WEBHOOK_URL')]) {

        stage('Checkout') {
            checkout scm
        }

        stage('Maven package') {
            sh 'mvn clean package'
        }

        stage('Docker Image Build') {
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

        stage('Deploy to Render') {
            sh """
                curl -X POST $RENDER_DEPLOY_WEBHOOK_URL \
                -H "Content-Type: application/json"
            """
        }
    }
}
