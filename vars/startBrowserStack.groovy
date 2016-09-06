def call(user, localIdentifier) {
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
    def url = "https://www.browserstack.com/browserstack-local/BrowserStackLocal-linux-x64.zip"
    def folder = pwd() + '/browserstack-local'
    def zip = "${folder}/BrowserStackLocal.zip"
    def bin = "${folder}/BrowserStackLocal"

    sh """
        curl -sS ${url} > ${zip}
        unzip -o ${zip} -d ${folder}
        chmod +x ${bin}
        nohup ${bin} -onlyAutomate -localIdentifier ${localIdentifier} -forcelocal -force -v ${env.TOKEN} > ${folder}/browserstack.log 2>&1 & echo \\\$! > ${folder}/browserstack.pid
    """
  }
}