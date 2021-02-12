#!/bin/bash

if [ $# -ne 2 ]; then
    echo "usage: ${0} [tst|acc|prd] [Password]"
    exit 1
fi

ENV="${1}"
INPUT="${2}"

BASE_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

ENCRYPTION_JAR=${BASE_DIR}/lib/jasypt-encryption.jar
DECRYPTION_CLASS=org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI
ENCRYPTION_ALGORITHM=PBEWithHMACSHA512AndAES_256
IV_GENERATOR_CLASS=org.jasypt.iv.RandomIvGenerator


# replace ENC( .... ) to ....
if [[ "${INPUT}" == ENC* ]]; then
    INPUT="${INPUT#*\(}"
    INPUT="${INPUT%?}"
fi

function jasypt_decrypt {
    if [ ! -s "${BASE_DIR}/lib/${1}" ] ; then
        echo "The script expects a decryption password in${BASE_DIR}/lib/${1}"
        exit 1
    fi

    PASSWORD=$(cat ${BASE_DIR}/lib/${1})
    java -cp "${ENCRYPTION_JAR}" "${DECRYPTION_CLASS}" \
              input="${INPUT}" \
              password="${PASSWORD}" \
              algorithm="${ENCRYPTION_ALGORITHM}" \
              ivGeneratorClassName="${IV_GENERATOR_CLASS}" \
              verbose=false
}

case $ENV in
    [tst]* ) jasypt_decrypt "jasypt_pwd_tst.txt" "${INPUT}" ;;
    [acc]* ) jasypt_decrypt "jasypt_pwd_acc.txt" "${INPUT}" ;;
    [prd]* ) jasypt_decrypt "jasypt_pwd_prd.txt" "${INPUT}" ;;
    * )     echo "Invalid environment provided. Should be [tst|acc|prd], supplied:${ENV}"
            exit 0
            ;;
esac