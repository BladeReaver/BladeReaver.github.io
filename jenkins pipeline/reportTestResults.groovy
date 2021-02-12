def call(testType, testFormat, application, mvnversion, environment) {
    url = "http://itested.europe.intranet:8888/get/${testType}/testresults/stdout/${testFormat}/${application}/${mvnversion}/${environment}"
    echo "Test results can be found at: ${url}"

    summaryUrl = "http://itested.europe.intranet:8888/get/${testType}/summary/stdout/${application}/${mvnversion}/${environment}"
    test = sh(returnStdout:true, script: "curl --retry 4 ${summaryUrl} | grep -i total | cut -d \':\' -f3,4,5 | tr -s \' \' | cut -d\' \' -f4,6 | sed \'s/\\ /\\//g\'")

    echo "${test}"
    if ( test == "0/0\n" ) {
        echo "Tests passed, continue!"
    } else {
        error "Tests failed!"
    }
}