def call(profile) {
    script {
        def buildProfile

        if(null != profile && !"".equalsIgnoreCase(profile)) {
            buildProfile = "-P${profile}"
        } else {
            buildProfile = ""
        }

        sh "mvn ${buildProfile} -U clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"
    }
}
