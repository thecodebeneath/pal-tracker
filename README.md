# pal-tracker
Project from PAL training, JUL 2018

Setup SSH for git remotes for a new repo:
- GitHub new repo (don't init with files)
- git init
- git commit -m "initial commit"
- git remote add thecodebeneath {thegithubrepourl}
  - eg: git add remote thecodebeneath git@github.com-thecodebeneath:thecodebeneath/pal-tracker.git
  - note: modified the github.com host to append a unique label that will be deferenced by ~/.ssh/config
- create/edit ~/.ssh/config file. The host entry must match the unique label added via the remote above
```
Host github.com-thecodebeneath
        HostName github.com
        User git
        IdentityFile ~/.ssh/id_rsa_codebeneath
```
- Create the actual ssh keys, if needed, and add the public key your github account that matches the private key identified by the IdentityFile property
- git push thecodebeneath master
