/**
 * @param branchName - The GIT default branch ('develop' in github flow).
 */
def call(java.lang.String branchName = 'develop') {

    // First get the latest version of master branch
    sh "git fetch"
    sh "git checkout master"
    sh "git pull"

    // Get latest version of develop branch
    sh "git checkout $branchName"
    sh "git pull"

    // Merge master into develop
    sh "git merge master --no-ff"
    sh "git push"
}
