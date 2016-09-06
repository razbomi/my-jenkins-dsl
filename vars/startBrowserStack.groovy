def call(user, localIdentifier) {
  def downloadUrl = 'https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip'
  def workspaceDir = pwd()

  echo "Starting browser stack for ${user} in ${workspaceDir}"
  withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                    credentialsId   : user,
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'TOKEN'
                   ]]) {
    sh """
        curl -sS ${downloadUrl} > '${workspaceDir}/BrowserStackLocal.zip'
        unzip -o '${workspaceDir}/BrowserStackLocal.zip' -d '${workspaceDir}''
        chmod +x '${workspaceDir}/BrowserStackLocal'
        nohup '${workspaceDir}/BrowserStackLocal' -v \
            -onlyAutomate \
            -localIdentifier ${localIdentifier} \
            -forcelocal \
            -force \
            ${env.TOKEN} > '${workspaceDir}/browserstack.log' 2>&1 & echo \\\$! > '${workspaceDir}/browserstack.pid'
    """
  }
}