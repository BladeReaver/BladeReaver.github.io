/**
 * @param branchName - The GIT branch that needs to be build
 * @param profile - (OPTIONAL) Supply any build profile that you might need for releasing.
 * @param improvedVersioning - Use 'new' way of versioning. (ask BE-ING-Clear team)
 * @return
 */
def call(branchName, profile, improvedVersioning, nameDevelopBranch = "develop", majorChangeOnMaster = true as java.lang.Object) {
    sh "git symbolic-ref HEAD"
    def mvnVersion = ""
    def buildProfile
    def releaseVersion = ""

    if(null != profile && !"".equalsIgnoreCase(profile)) {
        buildProfile = "-P${profile}"
    } else {
        buildProfile = ""
    }

    if(null == nameDevelopBranch || "" == nameDevelopBranch)
    {
        nameDevelopBranch = "develop"
    }

    sh "mvn release:clean clean"

    // NOTE: We need to execute Maven to get the project version twice to fix the issue
    // regarded the first build failing at the first execution of the day.
    // At the first execution, mvnVersion variable was very, very long (more than 4000 chars) because
    // Maven is downloading a lot of dependencies.
    sh "mvn help:evaluate -Dexpression=project.version"

    if(improvedVersioning && (nameDevelopBranch.equalsIgnoreCase(branchName) || "master".equalsIgnoreCase(branchName))) {
        if (branchName.startsWith(nameDevelopBranch) || (branchName.startsWith("master") && !majorChangeOnMaster)) {
            releaseVersion = "\\\${parsedVersion.majorVersion}.\\\${parsedVersion.nextMinorVersion}.0"
        }
        if (branchName.startsWith("master") && majorChangeOnMaster) {
            releaseVersion = "\\\${parsedVersion.nextMajorVersion}.0.0"
        }

        sh "echo profile=${profile}"
        sh "echo releaseVersion=${releaseVersion}"

        echo "Update to new version to retrieve version"
        sh "mvn build-helper:parse-version versions:set -DnewVersion=${releaseVersion}"
        mvnVersion = sh returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version | grep -e \'^[^\\[]\' | cut -d\'-\' -f1 | tr -d "\\n"'
        echo "mvnVersion= ${mvnVersion}"
        echo "Undo update version because the release plugin will do that for us"
        sh "git reset --hard HEAD"
        sh "git clean -f"

        sh "mvn -B ${buildProfile} build-helper:parse-version -DreleaseVersion=${releaseVersion} -Dtag=${application}-${releaseVersion} release:prepare"
        sh "mvn -B ${buildProfile} release:perform"
    } else {
        mvnVersion = sh returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version | grep -e \'^[^\\[]\' | cut -d\'-\' -f1 | tr -d "\\n"'
        sh "mvn -B ${buildProfile} release:prepare"
        sh "mvn -B ${buildProfile} release:perform"
    }

    sh "git status"
    sh "git push origin"
    return mvnVersion
}
