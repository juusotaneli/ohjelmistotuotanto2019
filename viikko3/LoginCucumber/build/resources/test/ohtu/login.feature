Feature: User can log in with valid username/password-combination

    Scenario: user can not login with incorrect password
        Given command login is selected
        When  username "esa" and password "esaesaesa1" are entered
        Then  system will respond with "wrong username or password"
    
    Scenario: nonexistent user can not login to 
        Given command login is selected
        When  username "esaesa" and password "esaesa1" are entered
        Then  system will respond with "wrong username or password"

    Scenario: user can login with correct password
        Given command login is selected
        When  username "esa" and password "esaesa1" are entered
        Then  system will respond with "logged in"
