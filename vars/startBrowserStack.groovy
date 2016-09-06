/**
 * https://www.browserstack.com/local-testing
 *
 * darwin-x64
 * linux-x64
 * linux-ia32
 * win32
 */
def call(user, localIdentifier, installFolder = '.') {

  echo "Starting browser stack ${localIdentifier} for ${user} in ${installFolder}"

  env.BS_DOWNLOAD_URL = 'https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip'
  env.BS_WORK_SPACE = installFolder 
  env.BS_LOCAL_IDENTIFIER = localIdentifier
  def 
  def credentials = [
      $class          : 'UsernamePasswordMultiBinding',
      credentialsId   : user,
      usernameVariable: 'BS_USERNAME',
      passwordVariable: 'BS_TOKEN'
  ]

  withCredentials([credentials]) { installScript() }
}


def installScript() {
  sh '''
      curl -sS ${BS_DOWNLOAD_URL} > ${BS_WORK_SPACE}/BrowserStackLocal.zip
      unzip -o ${BS_WORK_SPACE}/BrowserStackLocal.zip -d ${BS_WORK_SPACE}
      chmod +x ${BS_WORK_SPACE}/BrowserStackLocal
      nohup ${BS_WORK_SPACE}/BrowserStackLocal -v \
          -onlyAutomate \
          -localIdentifier ${BS_LOCAL_IDENTIFIER} \
          -forcelocal \
          -force \
          ${BS_TOKEN} > ${BS_WORK_SPACE}/browserstack.log 2>&1 &
      echo $! > ${BS_WORK_SPACE}/browserstack.pid
      cat ${BS_WORK_SPACE}/browserstack.pid
  '''
}