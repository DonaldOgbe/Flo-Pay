# Project Design and Architecture  

--- 

## Api Endpoints

---

- ### Create payment 

    `POST` `/payments`   
    `Content-Type: application/json`

<br>

```json
   {
      "amount": 100.00,
      "currency": "USD",
      "customerId": "user123",
      "paymentMethod": "credit_card",
      "creditCardNumber": "************1234",
      "expiryMonth": "12",
      "expiryYear": "2024",
      "cvv": "123"
    }
```

<br>

#### *Response*

```text
    HTTP/1.1 201 Created
    Location: /payments/payment123

    {
      "paymentId": "payment123",
      "amount": 100.00,
      "currency": "USD",
      "customerId": "user123",
      "status": "PENDING"
    }
```

<br>
<br>

- ### Retrieve payment

    `GET` `/payments/{paymentId}`

#### *Success Response*

```text
    HTTP/1.1 200 OK

    {
      "paymentId": "payment123",
      "amount": 100.00,
      "currency": "USD",
      "customerId": "user123",
      "status": "PENDING"
    }
```

#### *Error Response*

```text
    HTTP/1.1 404 Not Found
```

<br>
<br>

- ### Process payment

    `POST` `/payments/{paymentId}/process`

#### *Success Response*

```text
    HTTP/1.1 200 OK

    {
      "paymentId": "payment123",
      "amount": 100.00,
      "currency": "USD",
      "customerId": "user123",
      "status": "CAPTURED"
    }
```

#### *Error Response* 

```text
    HTTP/1.1 404 Not Found
```

#### *Fail Response*

```text
    HTTP/1.1 400 Bad Request
    Content-Type: application/json
    
    {
      "error": "Insufficient funds"
    }
```

<br>
<br>

- ### Refund payment

    `POST` `/payments/{paymentId}/refund`

#### *Success Response*

```text
     HTTP/1.1 200 OK

    {
      "paymentId": "payment123",
      "amount": 100.00,
      "currency": "USD",
      "customerId": "user123"
    }
```

#### *Error Response*

```text
    HTTP/1.1 404 Not Found
```

<br>
<br>

- ### Cancel payment 
    
    `POST` `/payments/{paymentId}/cancel`

#### *Success Response*

```text
     HTTP/1.1 200 OK
```

#### *Error Response*

```text
    HTTP/1.1 404 Not Found
```

#### *Conflict Response*

```text
    {
      "error": "Payment has already been processed and cannot be cancelled."
    }
```











