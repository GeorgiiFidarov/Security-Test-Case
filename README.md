Endpoints
1. Добавить пользователя в сессионный кэш
   Метод: POST
   URL: /api/addUser
   Запрос:
```json
{
"username": "ваш_пользователь",
"passwordOrHash": "ваш_пароль_или_хэш"
}

```



2. Аутентификация пользователя
   Метод: POST
   URL: /api/authenticate
   Запрос:


```json
{
"username": "ваш_пользователь",
"passwordOrHash": "ваш_пароль_или_хэш"
}

```
