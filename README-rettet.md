Here's the complete Markdown format for your Doctor API HTTP tests, with all sections included as requested:

---

# Doctor API HTTP Tests

## OPGAVE 1.5.4

### Test of `getALL` Endpoint

```http
GET http://localhost:7007/api/doctor
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 10:47:10 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `729 bytes`

```json
[
  {
    "id": 1,
    "name": "Dr. John Doe",
    "birthDate": "1975-03-15",
    "yearOfGraduation": 2000,
    "nameOfClinic": "City Health Clinic",
    "speciality": "Cardiology"
  },
  {
    "id": 2,
    "name": "Dr. Jane Smith",
    "birthDate": "1980-07-22",
    "yearOfGraduation": 2005,
    "nameOfClinic": "Downtown Medical Center",
    "speciality": "Pediatrics"
  },
  {
    "id": 3,
    "name": "Dr. Sam Brown",
    "birthDate": "1969-11-05",
    "yearOfGraduation": 1995,
    "nameOfClinic": "General Hospital",
    "speciality": "Neurology"
  },
  {
    "id": 4,
    "name": "Dr. Emily White",
    "birthDate": "1985-02-28",
    "yearOfGraduation": 2010,
    "nameOfClinic": "Westside Clinic",
    "speciality": "Dermatology"
  },
  {
    "id": 5,
    "name": "Dr. Michael Green",
    "birthDate": "1978-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "Eastside Health Services",
    "speciality": "Orthopedics"
  }
]
```

---

### Test of `getById` Endpoint

```http
GET http://localhost:7007/api/doctor/1
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 10:46:38 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `141 bytes`

```json
{
  "id": 1,
  "name": "Dr. John Doe",
  "birthDate": "1975-03-15",
  "yearOfGraduation": 2000,
  "nameOfClinic": "City Health Clinic",
  "speciality": "Cardiology"
}
```

---

### Test of `getBySpeciality` Endpoint

```http
GET http://localhost:7007/api/doctor/speciality/SURGERY
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 10:54:50 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `151 bytes`

```json
[
  {
    "id": 5,
    "name": "Dr. Michael Green",
    "birthDate": "1978-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "Eastside Health Services",
    "speciality": "SURGERY"
  }
]
```

---

### Test of `GetByBirthDateRange` Endpoint

```http
GET http://localhost:7007/api/doctor/birthdate-range/1980-01-01/1990-01-01
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 10:54:08 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `292 bytes`

```json
[
  {
    "id": 2,
    "name": "Dr. Jane Smith",
    "birthDate": "1980-07-22",
    "yearOfGraduation": 2005,
    "nameOfClinic": "Downtown Medical Center",
    "speciality": "PEDIATRICS"
  },
  {
    "id": 4,
    "name": "Dr. Emily White",
    "birthDate": "1985-02-28",
    "yearOfGraduation": 2010,
    "nameOfClinic": "Westside Clinic",
    "speciality": "GERIATRICS"
  }
]
```

---

### Test of `Create` Endpoint

```http
POST http://localhost:7007/api/doctor
```

#### Response

- **Status**: `201 Created`
- **Date**: `Tue, 29 Oct 2024 11:01:39 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `146 bytes`

```json
{
  "id": 6,
  "name": "CREATED DOCTOR",
  "birthDate": "1978-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "Eastside Health Services",
  "speciality": "SURGERY"
}
```

---

### Test of `Update` Endpoint

```http
PUT http://localhost:7007/api/doctor/6
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 11:11:08 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `154 bytes`

```json
{
  "id": 6,
  "name": "UPDATED CREATED DOCTOR",
  "birthDate": "1978-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "Eastside Health Services",
  "speciality": "SURGERY"
}
```

---

## OPGAVE 3.2

### Purpose of Using Generics

Using generics allows us to reuse the same `iDAO` interface for multiple DAO classes that implement `iDAO`. This way,
`iDAO` specifies the type of object each class will handle. This makes the code more flexible and easier to maintain, as
we avoid duplicate interfaces and ensure type-safety in our DAO classes.

---

# Doctor API HTTP Tests with Real Database
## OPGAVE 4.11

### Test of `getALL` Endpoint with Real Database

```http
GET http://localhost:7007/api/doctor
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 15:01:04 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `532 bytes`

```json
[
  {
    "id": 1,
    "name": "1 CREATED DOCTOR",
    "dateOfBirth": "1978-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "Eastside Health Services",
    "speciality": "SURGERY",
    "appointments": []
  },
  {
    "id": 2,
    "name": "2 CREATED DOCTOR",
    "dateOfBirth": "1978-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "Eastside Health Services",
    "speciality": "SURGERY",
    "appointments": [
      {
        "id": 1,
        "clientName": "John Doe",
        "date": "2024-11-01",
        "time": "10:30:00",
        "comment": "First appointment"
      },
      {
        "id": 2,
        "clientName": "Jane Doe",
        "date": "2024-11-05",
        "time": "15:00:00",
        "comment": "Follow-up"
      }
    ]
  }
]
```

---

### Test of `getById` Endpoint with Real Database

```http
GET http://localhost:7007/api/doctor/1
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 15:01:18 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `168 bytes`

```json
{
  "id": 1,
  "name": "1 CREATED DOCTOR",
  "dateOfBirth": "1978-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "Eastside Health Services",
  "speciality": "SURGERY",
  "appointments": []
}
```

---

### Test of `getBySpeciality` Endpoint with Real Database

```http
GET http://localhost:7007/api/doctor/speciality/SURGERY
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 15:01:28 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `532 bytes`

```json
[
  {
    "id": 1,
    "name": "1 CREATED DOCTOR",
    "dateOfBirth": "1978-09-12",
    "yearOfGraduation": 2003,
    "name OfClinic": "Eastside Health Services",
    "speciality": "SURGERY",
    "appointments": []
  },
  {
    "id": 2,
    "name": "2 CREATED DOCTOR",
    "dateOfBirth": "1978-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "Eastside Health Services",
    "speciality": "SURGERY",
    "appointments": [
      {
        "id": 1,
        "clientName": "John Doe",
        "date": "2024-11-01",
        "time": "10:30:00",
        "comment": "First appointment"
      },
      {
        "id": 2,
        "clientName": "Jane Doe",
        "date": "2024-11-05",
        "time": "15:00:00",
        "comment": "Follow-up"
      }
    ]
  }
]
```

---

### Test of `GetByBirthDateRange` Endpoint with Real Database

```http
GET http://localhost:7007/api/doctor/birthdate-range/1980-01-01/1990-01-01
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 15:01:58 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `178 bytes`

```json
[
  {
    "id": 1,
    "name": "1 CREATED DOCTOR",
    "dateOfBirth": "1980-09-12",
    "yearOfGraduation": 2003,
    "nameOfClinic": "UPDATED Eastside Health Services",
    "speciality": "SURGERY",
    "appointments": []
  }
]
```

---

### Test of `Create` Endpoint with Real Database

```http
POST http://localhost:7007/api/doctor
```

#### Response

- **Status**: `201 Created`
- **Date**: `Tue, 29 Oct 2024 15:02:21 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `168 bytes`

```json
{
  "id": 3,
  "name": "3 CREATED DOCTOR",
  "dateOfBirth": "1978-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "Eastside Health Services",
  "speciality": "SURGERY",
  "appointments": []
}
```

---

### Test of `Update` Endpoint with Real Database

```http
PUT http://localhost:7007/api/doctor/1
```

#### Response

- **Status**: `200 OK`
- **Date**: `Tue, 29 Oct 2024 15:01:43 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `176 bytes`

```json
{
  "id": 1,
  "name": "1 CREATED DOCTOR",
  "dateOfBirth": "1980-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "UPDATED Eastside Health Services",
  "speciality": "SURGERY",
  "appointments": []
}
```

---

### Test of `Create` Endpoint with Appointment

```http
POST http://localhost:7007/api/doctor
```

#### Response

- **Status**: `201 Created`
- **Date**: `Tue, 29 Oct 2024 15:02:39 GMT`
- **Content-Type**: `application/json`
- **Content-Length**: `361 bytes`

```json
{
  "id": 4,
  "name": "4 CREATED DOCTOR",
  "dateOfBirth": "1978-09-12",
  "yearOfGraduation": 2003,
  "nameOfClinic": "Eastside Health Services",
  "speciality": "SURGERY",
  "appointments": [
    {
      "id": 3,
      "clientName": "John Doe",
      "date": "2024-11-01",
      "time": "10:30:00",
      "comment": "First appointment"
    },
    {
      "id": 4,
      "clientName": "Jane Doe",
      "date": "2024-11-05",
      "time": "15:00:00",
      "comment": "Follow-up"
    }
  ]
}
```

---

# Task 6.4: Key Differences Between Unit Tests and Integration Tests

In standard unit tests, we test individual components (e.g., classes or methods) in isolation by often mocking
dependencies to verify functionality independently. In contrast, the tests in this task are **integration tests**, where
we test multiple components working together, especially through database interactions, to ensure end-to-end
functionality.

# Task 6: Testing Doctor API with REST Assured

## 7.1 Purpose of REST Assured and Why We Test Endpoints This Way

REST Assured is a Java library for testing RESTful APIs. It allows us to simulate HTTP requests and validate responses,
ensuring that API endpoints function as expected. This type of testing is crucial to confirm that our API handles
requests and responses properly in real-world scenarios.

## 7.2 Setting Up the Database for Tests

For testing, we use a specific test database or a clean, isolated test schema. This setup ensures that tests do not
affect production data and allows for repeatable tests by resetting the database to a known state between runs.

## 7.3 Why Testing REST Endpoints Differs from Unit Tests (Task 5)

Testing REST endpoints differs from unit tests as it involves the entire application stack, from handling HTTP requests
to managing data. Unlike unit tests that isolate individual methods or classes, REST endpoint tests validate the whole
system, including data serialization, validation, and execution of business logic.

--- 

Let me know if there's anything else you need!