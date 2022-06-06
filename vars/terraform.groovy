def call() {
    pipeline {
        agent any

        options {
            ansiColor('xterm')
        }

        parameters {
            string(name: 'ENV', devaultValue: '', description: 'Which Environment?')
        }

        environment {
            SSH = credentials('SSH')
        }

        stages {
            stage('Terraform INIT') {
                steps {
                    sh ''' 
                       terraform init -backend-config=env-${ENV}/backend.tfvars
                    '''
                }
            }

            satge('Terraform Apply') {
                steps {
                    sh ''' 
                       terraform init -var-file=env-${ENV}/${ENV}.tfvars
                    '''
                }
            }
        }
    }
}