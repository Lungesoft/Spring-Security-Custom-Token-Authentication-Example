##Spring Boot 1.5.10

###Custom token authentication Example.

1. Check security is enabled.

    `Request:` 

    ```
    GET: http://localhost:8080/city
    ```
    
    `Response:` 
    ```
    {
        "timestamp": 1536476203754,
        "status": 403,
        "error": "Forbidden",
        "message": "Access Denied",
        "path": "/city"
    }
    ```
    Fine. It works!
    
2. Registration 

    `Request:` 

    ```
    POST: http://localhost:8080/registration
    Content-Type: application/json
    Body: 
    {
        "login" : "user1",
        "password" : "password1"
    }
    ```    
    `Response:` 
    ```
    {
        "id": 1,
        "login": "user1",
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ0b2tlbl9leHBpcmF0aW9uX2RhdGUiOjQ2OTIxNTA5NjMwNzAsInRva2VuX2NyZWF0ZV9kYXRlIjoxNTM2NDc3MzYzMDcwfQ.5flLrZO8TRiMd0QzgbGyoCczlYLN8WwB5p6XSIanDWvMG-gS1FYPiejDZpPhwwqsyVlE37sZ1SNpqaQbkCSK3w"
    }
    ```
    
    1. Or now we can Login 
    
        `Request:` 
        ```
        POST: http://localhost:8080/login
        Content-Type: application/json
        Body: 
        {
            "login" : "user1",
            "password" : "password1"
        }
        ```    
        `Response:` 
        ```
        {
            "id": 1,
            "login": "user1",
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ0b2tlbl9leHBpcmF0aW9uX2RhdGUiOjQ2OTIxNTEyNjQ5NjEsInRva2VuX2NyZWF0ZV9kYXRlIjoxNTM2NDc3NjY0OTYxfQ.vc8sxqy73nD5MjZpg-9VnYZZ2SOJ257fJUc85rkFshPIj-Yy_PEvTjpz5D4dX8MKAVz7G4k9d6Wnc_BT8pmIUg"
        }
        ```
        
3. Retrieve security resource 
   
    `Request:` 

    ```
    GET: http://localhost:8080/city
    Authorization: eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ0b2tlbl9leHBpcmF0aW9uX2RhdGUiOjQ2OTIxNTEyNjQ5NjEsInRva2VuX2NyZWF0ZV9kYXRlIjoxNTM2NDc3NjY0OTYxfQ.vc8sxqy73nD5MjZpg-9VnYZZ2SOJ257fJUc85rkFshPIj-Yy_PEvTjpz5D4dX8MKAVz7G4k9d6Wnc_BT8pmIUg
    ```
    
    `Response:` 
    ```
    [
        {
            "id": 1,
            "title": "Auckland"
        },
        {
            "id": 2,
            "title": "Wellington"
        },
    ...
        {
            "id": 13,
            "title": "Invercargill"
        },
        {
            "id": 14,
            "title": "Whanganui"
        },
        {
            "id": 15,
            "title": "Gisborne"
        }
    ]
    ```
    Fine. It works!
