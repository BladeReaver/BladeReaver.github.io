def call(userName, userEmail, branchName) {
    script {
        checkout scm
        result = sh (script: "git log -1 | grep '.*\\[maven-release-plugin\\].*'", returnStatus: true)
        if (result == 0) {
            echo ("'maven-release-plugin' spotted in git commit. Aborting.")
            skipBuild = 'true'
        }
        sh "git checkout ${branchName}"

        sh "git config user.name \"${userName}\""
        sh "git config user.email \"${userEmail}\""
        sh "git config push.default simple"

        sh "git stash"
        sh "git pull"
        gitUrl = sh(returnStdout: true, script: 'git config remote.origin.url').trim()

        return skipBuild
    }
}
