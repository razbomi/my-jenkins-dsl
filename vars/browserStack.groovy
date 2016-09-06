def call(user) {
  def workingDir = pwd()

  echo "Starting browser stack for ${user} in ${workingDir}"

}

def withBs(user) {
  withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                    credentialsId   : user,
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'TOKEN'
                   ]]) {

    // TODO: Parametarize this script
    sh """
        curl -sS https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip > /var/tmp/BrowserStackLocal.zip
        unzip -o /var/tmp/BrowserStackLocal.zip -d /var/tmp
        chmod +x /var/tmp/BrowserStackLocal
        nohup /var/tmp/BrowserStackLocal -onlyAutomate -localIdentifier ${env.JOB_NAME} -forcelocal -force -v ${env.TOKEN} > /var/tmp/browserstack.log 2>&1 & echo \\\$! > /var/tmp/browserstack.pid
    """
  }
}