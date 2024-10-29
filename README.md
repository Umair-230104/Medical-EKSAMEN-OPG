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

