##Endpoints:
`http://localhost:3000/server/git-repo/public?username={USERNAME}`: 
Get all public repositories from a user with a username

`http://localhost:3000/server/git-repo/all` with `header (token = {TOKEN}`: 
Get all public and private repositories with a token string

`http://localhost:3000/server/git-repo/public/branches?username={USERNAME}&repo={REPO_NAME}`: 
Get all branches of a public repository from a user with username 
    
`http://localhost:3000/server/git-repo/all/branches?username={USERNAME}&repo={REPO_NAME}` with `header (token = {TOKEN}`:
Get all branches of a public or private repository from a user with username and token

`Information for integrationtest classes`
Github account Testuser:
e-mail: testuser567@web.de  
password e-mail: TestUser:D1
username: Testuser5678
password: TestUser:D1

testFile is used in the test classes and should not be deleted.


