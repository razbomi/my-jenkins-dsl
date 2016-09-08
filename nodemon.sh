#!/usr/bin/bash

git add --all
git commit -am "Nodemon: Auto commit"
git push jenkins master

curl -X POST http://mitko:45325b9ba1e48d5867029ce025307d9b@localhost:8080/job/DslLibs-BrowserStack/build
