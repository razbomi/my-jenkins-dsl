#!/usr/bin/bash

git add --all
git commit -am "Nodemon checking"
git push origin jenkins

curl -X POST http://localhost:8080/job/DslLibs-BrowserStack/build
