/**
 * https://www.browserstack.com/local-testing
 *
 * darwin-x64
 * linux-x64
 * linux-ia32
 * win32
 */
def call(
  user, 
  localIdentifier, 
  installFolder = '.',
  downloadUrl = 'https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip'
) {

  echo "Starting browser stack ${localIdentifier} for ${user} in ${installFolder}"

  def variables = ["BS_DOWNLOAD_URL=${downloadUrl}", "BS_WORK_SPACE=${installFolder}", "BS_LOCAL_IDENTIFIER=${localIdentifier}"]
  def credentials = 

  withCredentials([credentials]) { withEnv(variables) { installScript() } }
}

def buildCredentials() {
  return [$class         : 'UsernamePasswordMultiBinding', 
    credentialsId : user, 
    usernameVariable: 'BS_USERNAME', 
    passwordVariable: 'BS_TOKEN' ]
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