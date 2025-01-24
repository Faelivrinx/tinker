# Authorization

## Get an access token
Using HTTPie

```
http POST :8080/token email="john@example.com" password="password"
```

Test an endpoint

```
http :8080/api/hit Authorization:"Bearer <access_token>"
```

## Todo
- [ ] Design and implement User model (ADMIN, EXPERT, USER) - prepare them in memory database
- [ ] Implement User registration endpoint
- [ ] Refresh Tokens
- [ ] Exclude the code to seperated modules for Authentication/Authorization
- [ ] Extract private and public keys to environments and prepare a script to easily generate new ones
- 