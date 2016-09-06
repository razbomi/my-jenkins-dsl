#!/usr/bin/bash

##
# What happened
# $ ssh -p 8022 mitko@localhost who-am-i
# $ mkdir jenkins
# $ cd jenkins
# $ git init
# $ git add remote origin ssh://mitko@localhost:8022/workflowLibs.git
# $ git push --set-upstream origin master
# $ git branch --set-upstream-to=origin/master master
# $ export PATH=/Development/Tools/nvm-1.1.1/v6.3.1:$PATH
# $ npm install --save-dev nodemon
# $  nodemon -V nodemon.sh -e groovy
git add --all
git commit -am "Nodemon checking"
git push
