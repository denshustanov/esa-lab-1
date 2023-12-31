# Лабораторная работа №1
## Предметная область
Приложение реализует пример сервиса обработки заказов в интернет-магазине.

Модель данных состоит из следующих сущностей: 

- Заказ, содержащий номер заказа, id клиента.

- Элемент заказа, содержащий id продукта и количество.

В рамках презентационного слоя реализован CRUD REST-API.
## Архитектура решения
![lab1_kinda_uml drawio (2)](https://github.com/denshustanov/esa-lab-1/assets/63245753/3a271c9a-b427-4bc1-bbba-6ec9359756f9)

## Функции API
### 1. Создание заказа

HTTP-метод - POST

URL - /api/v1/orders

Функция предназначена для сохранения заказа в базу данных.

#### Входной объект
```json
{
    "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
    "content": [
        {
            "productId": "13358536-f904-4387-91bd-9e206019e4f9",
            "quantity": 10
        },
        {
            "productId": "0b99b0ba-21f1-4c7a-8882-51bfe21441d5",
            "quantity": 5
        }
    ]
} 
```
#### Выходной объект
```json
{
  "content": [
    {
      "productId": "13358536-f904-4387-91bd-9e206019e4f9",
      "quantity": 10
    },
    {
      "productId": "0b99b0ba-21f1-4c7a-8882-51bfe21441d5",
      "quantity": 5
    }
  ],
  "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
  "id": "9c4ff9f4-d2db-48cb-9f4f-6c55865b7b9c",
  "number": "05-10-23-790102",
  "status": "DRAFT"
}
```

### 2. Получение информации о заказе

HTTP-метод - GET

URL - /api/v1/orders/:id

Функция предназначена для получения информации об одном заказе.

В URL запроса передается id требуемого заказа.


#### Выходной объект
```json
{
  "content": [
    {
      "productId": "13358536-f904-4387-91bd-9e206019e4f9",
      "quantity": 10
    },
    {
      "productId": "0b99b0ba-21f1-4c7a-8882-51bfe21441d5",
      "quantity": 5
    }
  ],
  "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
  "id": "9c4ff9f4-d2db-48cb-9f4f-6c55865b7b9c",
  "number": "05-10-23-790102",
  "status": "DRAFT"
}
```

### 3. Получение информации о коллекции заказов

HTTP-метод - GET

URL - /api/v1/orders?page=:page&size=:size

Функция предназначена для получения информации о нескольких  заказах.

Реализована пагинация. В querry-параметрах запроса передаются размер и номер страницы.


#### Выходной объект
```json
{
  "data": [
    {
      "content": [
        {
          "productId": "0b99b0ba-21f1-4c7a-8882-51bfe21441d5",
          "quantity": 5
        },
        {
          "productId": "13358536-f904-4387-91bd-9e206019e4f9",
          "quantity": 10
        }
      ],
      "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
      "id": "be3c7ad6-3fb4-4999-bc3b-d1835860d50f",
      "number": "05-10-23-964266",
      "status": "DRAFT"
    },
    {
      "content": [
        {
          "productId": "13358536-f904-4387-91bd-9e206019e4f9",
          "quantity": 10
        },
        {
          "productId": "0b99b0ba-21f1-4c7a-8882-51bfe21441d5",
          "quantity": 5
        }
      ],
      "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
      "id": "b20c288b-658a-4ac4-84d2-fd072bec89e7",
      "number": "05-10-23-233618",
      "status": "DRAFT"
    }
  ],
  "number": 0,
  "size": 2,
  "totalCount": 31,
  "totalPages": 16
}
```

### 4. Изменение заказа

HTTP-метод - PUT

URL - /api/v1/orders/:id

Функция предназначена для обновления заказа в базе данных.

В URL запроса передается id требуемого заказа.

#### Входной объект
```json
{
  "status": "CREATED",
  "content": [
    {
      "productId": "13358536-f904-4387-91bd-9e206019e4f9",
      "quantity": 10
    },
    {
      "productId": "13358536-f904-4387-91bd-9e206019e4f9",
      "quantity": 10
    }
  ]
}
```
#### Выходной объект
```json
{
  "id": "190486a0-5cea-42e3-a366-1fb94631a54f",
  "customerId": "13358536-f904-4387-91bd-9e206019e4f9",
  "number": "05-10-23-448997",
  "status": "CREATED",
  "content": [
    {
      "quantity": 10,
      "productId": "13358536-f904-4387-91bd-9e206019e4f9"
    },
    {
      "quantity": 10,
      "productId": "13358536-f904-4387-91bd-9e206019e4f9"
    }
  ]
}
```

### 5. Удаление заказа

HTTP-метод - DELETE

URL - /api/v1/orders/:id

Функция предназначена для удаления заказа из базы данных.

В URL запроса передается id требуемого заказа.
