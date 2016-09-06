def call(user, localIdentifier) {
  def env.BS_DOWNLOAD_URL = 'https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip'
  def env.BS_WORK_SPACE = pwd()
  def env.BS_LOCAL_IDENTIFIER = localIdentifier


  echo "Starting browser stack for ${user} in ${env.BS_WORK_SPACE}"
  withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                    credentialsId   : user,
                    usernameVariable: 'BS_USERNAME',
                    passwordVariable: 'BS_TOKEN'
                   ]]) {
    sh '''
        curl -sS ${BS_DOWNLOAD_URL} > ${BS_WORK_SPACE}/BrowserStackLocal.zip
        unzip -o ${BS_WORK_SPACE}/BrowserStackLocal.zip -d ${BS_WORK_SPACE}
        chmod +x ${BS_WORK_SPACE}/BrowserStackLocal
        nohup ${BS_WORK_SPACE}/BrowserStackLocal -v \
            -onlyAutomate \
            -localIdentifier ${BS_LOCAL_IDENTIFIER} \
            -forcelocal \
            -force \
            ${BS_TOKEN} > ${BS_WORK_SPACE}/browserstack.log 2>&1 & echo \\\$! > ${BS_WORK_SPACE}/browserstack.pid
        echo ${BS_WORK_SPACE}/browserstack.pid
    '''
  }
}