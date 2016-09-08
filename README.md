# Common pipeline steps

## Using the workflow-cps-global-lib-plugin

$ ssh -p 8022 mitko@localhost who-am-i

$ git remote add jenkins ssh://mitko@localhost:8022/workflowLibs.git

$ npm init

$ npm install --save-dev nodemon

$ nodemon -V nodemon.sh -e groovy

## Seting up from scratch on My windows

$ mkdir jenkins

$ cd jenkins

$ git init

$ git add remote origin ssh://mitko@localhost:8022/workflowLibs.git

$ git push --set-upstream origin master

$ git branch --set-upstream-to=origin/master master

$ export PATH=/Development/Tools/nvm-1.1.1/v6.3.1:$PATH