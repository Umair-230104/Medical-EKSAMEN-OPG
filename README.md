OPGAVE 1.5.4

Test af getALL i HTTP:
GET http://localhost:7007/api/doctor

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 10:47:10 GMT
Content-Type: application/json
Content-Length: 729

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
Response file saved.
> 2024-10-29T114710.200.json

Response code: 200 (OK); Time: 8ms (8 ms); Content length: 729 bytes (729 B)

Test af getById i HTTP:
GET http://localhost:7007/api/doctor/1

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 10:46:38 GMT
Content-Type: application/json
Content-Length: 141

{
"id": 1,
"name": "Dr. John Doe",
"birthDate": "1975-03-15",
"yearOfGraduation": 2000,
"nameOfClinic": "City Health Clinic",
"speciality": "Cardiology"
}
Response file saved.
> 2024-10-29T114638.200.json

Response code: 200 (OK); Time: 13ms (13 ms); Content length: 141 bytes (141 B)

Test af GetBySpeciality i HTTP
GET http://localhost:7007/api/doctor/speciality/SURGERY

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 10:54:50 GMT
Content-Type: application/json
Content-Length: 151

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
Response file saved.
> 2024-10-29T115450.200.json

Response code: 200 (OK); Time: 9ms (9 ms); Content length: 151 bytes (151 B)

Test af GetByBirthDateRange i HTTP
GET http://localhost:7007/api/doctor/birthdate-range/1980-01-01/1990-01-01

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 10:54:08 GMT
Content-Type: application/json
Content-Length: 292

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
Response file saved.
> 2024-10-29T115408.200.json

Response code: 200 (OK); Time: 8ms (8 ms); Content length: 292 bytes (292 B)

Test af Create i HTTP
POST http://localhost:7007/api/doctor

HTTP/1.1 201 Created
Date: Tue, 29 Oct 2024 11:01:39 GMT
Content-Type: application/json
Content-Length: 146

{
"id": 6,
"name": "CREATED DOCTOR",
"birthDate": "1978-09-12",
"yearOfGraduation": 2003,
"nameOfClinic": "Eastside Health Services",
"speciality": "SURGERY"
}
Response file saved.
> 2024-10-29T120139.201.json

Response code: 201 (Created); Time: 145ms (145 ms); Content length: 146 bytes (146 B)

Test af Update i HTTP
PUT http://localhost:7007/api/doctor/6

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 11:11:08 GMT
Content-Type: application/json
Content-Length: 154

{
"id": 6,
"name": "UPDATED CREATED DOCTOR",
"birthDate": "1978-09-12",
"yearOfGraduation": 2003,
"nameOfClinic": "Eastside Health Services",
"speciality": "SURGERY"
}
Response file saved.
> 2024-10-29T121108.200.json

Response code: 200 (OK); Time: 9ms (9 ms); Content length: 154 bytes (154 B)

OPGAVE 3.2
Formålet med at bruge generics er, at man kan genbruge det samme iDAO interface til flere DAO-klasser, der implementerer
iDAO. På den måde kan iDAO specificere, hvilken type objekt hver klasse skal håndtere. Dette gør koden mere fleksibel og
lettere at vedligeholde, da vi undgår duplikerede interfaces og sikrer type-sikkerhed i vores DAO-klasser.




Test af getALL i HTTP med rigtige database:
GET http://localhost:7007/api/doctor

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 15:01:04 GMT
Content-Type: application/json
Content-Length: 532

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
Response file saved.
> 2024-10-29T160105.200.json

Response code: 200 (OK); Time: 238ms (238 ms); Content length: 532 bytes (532 B)




Test af getById i HTTP med rigtige database:
GET http://localhost:7007/api/doctor/1

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 15:01:18 GMT
Content-Type: application/json
Content-Length: 168

{
"id": 1,
"name": "1 CREATED DOCTOR",
"dateOfBirth": "1978-09-12",
"yearOfGraduation": 2003,
"nameOfClinic": "Eastside Health Services",
"speciality": "SURGERY",
"appointments": []
}
Response file saved.
> 2024-10-29T160118.200.json

Response code: 200 (OK); Time: 17ms (17 ms); Content length: 168 bytes (168 B)




Test af GetBySpeciality i HTTP med rigtige database:
GET http://localhost:7007/api/doctor/speciality/SURGERY

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 15:01:28 GMT
Content-Type: application/json
Content-Length: 532

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
Response file saved.
> 2024-10-29T160128.200.json

Response code: 200 (OK); Time: 67ms (67 ms); Content length: 532 bytes (532 B)




Test af GetByBirthDateRange i HTTP med rigtige database:
GET http://localhost:7007/api/doctor/birthdate-range/1980-01-01/1990-01-01

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 15:01:58 GMT
Content-Type: application/json
Content-Length: 178

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
Response file saved.
> 2024-10-29T160158.200.json

Response code: 200 (OK); Time: 22ms (22 ms); Content length: 178 bytes (178 B)




Test af Create i HTTP med rigtige database:
POST http://localhost:7007/api/doctor

HTTP/1.1 201 Created
Date: Tue, 29 Oct 2024 15:02:21 GMT
Content-Type: application/json
Content-Length: 168

{
"id": 3,
"name": "3 CREATED DOCTOR",
"dateOfBirth": "1978-09-12",
"yearOfGraduation": 2003,
"nameOfClinic": "Eastside Health Services",
"speciality": "SURGERY",
"appointments": []
}
Response file saved.
> 2024-10-29T160221.201.json

Response code: 201 (Created); Time: 12ms (12 ms); Content length: 168 bytes (168 B)




Test af Update i HTTP med rigtige database:
PUT http://localhost:7007/api/doctor/1

HTTP/1.1 200 OK
Date: Tue, 29 Oct 2024 15:01:43 GMT
Content-Type: application/json
Content-Length: 176

{
"id": 1,
"name": "1 CREATED DOCTOR",
"dateOfBirth": "1980-09-12",
"yearOfGraduation": 2003,
"nameOfClinic": "UPDATED Eastside Health Services",
"speciality": "SURGERY",
"appointments": []
}
Response file saved.
> 2024-10-29T160143.200.json

Response code: 200 (OK); Time: 25ms (25 ms); Content length: 176 bytes (176 B)




Test af Create i HTTP med rigtige database og appointment:
POST http://localhost:7007/api/doctor

HTTP/1.1 201 Created
Date: Tue, 29 Oct 2024 15:02:39 GMT
Content-Type: application/json
Content-Length: 361

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
Response file saved.
> 2024-10-29T160239.201.json

Response code: 201 (Created); Time: 16ms (16 ms); Content length: 361 bytes (361 B)














### 6.4 Hovedforskelle Mellem Almindelige Unit Tests og Testene i Denne Opgave

I almindelige unit tests tester vi individuelle komponenter (fx klasser eller metoder) isoleret ved ofte at mocke
afhængigheder for at verificere funktionalitet uafhængigt. I modsætning hertil er testene i denne opgave *
*integrationstests**, hvor vi tester flere komponenter, der arbejder sammen, specielt ved interaktion med en rigtig
eller testdatabase for at sikre end-to-end funktionalitet.

---

### Opgave 6: Test af Doctor API med REST Assured

**7.1 Formålet med REST Assured og Hvorfor Vi Tester Endepunkterne på Denne Måde**

REST Assured er et Java-bibliotek til test af RESTful APIs. Det giver os mulighed for at simulere HTTP-forespørgsler og
validere svar, hvilket sikrer, at API-endepunkterne fungerer korrekt. Denne type test er afgørende for at bekræfte, at
vores API håndterer forespørgsler og svar i virkeligheden, som forventet.

**7.2 Opsætning af Databasen til Tests**

Til test bruger vi en specifik test-database eller et rent, isoleret testskema. Denne opsætning sikrer, at tests ikke
påvirker produktionsdata og tillader gentagelige tests ved at nulstille databasen til en kendt tilstand mellem hver
kørsel.

**7.3 Hvorfor Test af REST Endepunkter Er Anderledes End Unit Tests (Opgave 5)**

Test af REST endepunkter adskiller sig fra unit tests, da det involverer hele applikationsstakken, fra håndtering af
HTTP-forespørgsler til datahåndtering. I modsætning til unit tests, der isolerer enkelte metoder eller klasser,
bekræfter REST-endepunktstest, at hele systemet fungerer korrekt sammen, herunder dataserialisering, validering og
udførelse af forretningslogik.

