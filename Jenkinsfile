pipeline {
    agent any

    environment {
        REPO = "devopsity22/caesar-cipher"
    }

    stages {
        stage('build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('test') {
            steps {
                sh './gradlew test'
                sh 'java -jar build/libs/caesar-cipher.jar'
            }
        }
        stage('Release') {
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'TOKEN')]){
                    sh '''#!/bin/bash
                        TAG=$(git describe --tags | cut -d "-" -f 1)
                        echo $TAG
                        TAG_MSG=$(git tag -l $TAG --format='%(contents)')
                        echo $TAG_MSG
                        DATA='{
                            "tag_name": "'$TAG'",
                            "target_commitish": "main",
                            "name": "'$TAG'",
                            "body": "'$TAG_MSG'",
                            "draft": false,
                            "prerelease": false
                        }'
                        curl -o release.json -X POST -d "$DATA" \
                            -H "Authorization:token $TOKEN" \
                            -H "Accept: application/vnd.github+json" \
                            "https://api.github.com/repos/$REPO/releases"
                       '''
                    sh '''#!/bin/bash
                        zip artifacts.zip build/libs/caesar-cipher.jar
                        #ID=$(grep id release.json  | head -n 1 | cut -d : -f2 | cut -d , -f1 | cut -d ' ' -f2)
                        ID=$(jq '.id' release.json)
                        re="^[0-9]+$"
                        if [[ $ID =~ $re ]] ; then
                            curl -o asset.json -X POST \
                                --data-binary @artifacts.zip \
                                -H "Authorization:token $TOKEN" \
                                -H "Content-Type: application/json" \
                                -H "Accept: application/vnd.github+json" \
                                "https://uploads.github.com/repos/$REPO/releases/$ID/assets?name=artifacts.zip"
                        fi
                       '''
                }
            }
        }
    }
}