# customers-pagination

Simple CRUD project for learning Angular purposes.

### Frontend:
- Bootstrap 5 + Angular 16

### Backend
- Java 17
- Spring Boot 3
- Postgres on Docker

### Start-up

You can easily start-up this up on Docker container via docker-compose:

**_`!!! If you are using MacOs on Apple Silicon you should change images versions from v1 to v2 in customer-pagination-docker-compose.yml`_**

```
 docker compose -f .\customer-pagination-docker-compose.yml up -d
```
and then you can go to the main page on:

```
 http://localhost:4200/
```