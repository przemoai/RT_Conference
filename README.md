# How app works

To list available conferences with topics go to 
```http 
GET /conference/details
```

To join to conference we have to first sign in as a Participant
```http 
POST /participant
with example request body
{
    "login": "test_login",
    "email": "test@email.test"
}
```
Then we can sign up to theme conference by calling 
```http
POST /conference/1/participant/test_login
```
By following route we sign up to Theme Conference with ID 1 and topic "JS"

If we want to resign from theme conference we have to call conference ID and our Login used to signing up
```http 
DEL /conference/1/participant/test_login
```




# API Reference 
RT_CONFERENCE.postman_collection.json 

File contains exported postman rest api tests
## Show conference with details about topics and participants
```http
  GET /conference/details
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `List<ThemeConference>` | Return list of conferences with details |





## Show conference with details about topics and participants by ID
```http
  GET /conference/details/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id`    | `ThemeConference` | Return conference with details by ID |


## Add participant to conference
```http
  POST /conference/{id}/participant/{login}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id, login`    | `ResponseEntity` | Sign participant to conference by theme conference ID and participant unique Login|

Example Path Variable
```bash
http://localhost:8080/conference/1/participant/test_login1
```

## Show conference with details about topics and participants by ID
```http
  DEL /conference/{id}/participant/{login}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id, login`    | `ResponseEntity` | Remove participant from conference by theme conference ID and participant unique Login|

Example Path Variable
```bash
http://localhost:8080/conference/1/participant/test_login4
```

## Get all Conferences

```http
  GET /conference
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
|           | `List<Conference>` | Return list of conferences Time Schedule |

## Get Conference

```http
  GET /conference/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `List<Conference>` | Return conference by ID |

## Get Participants



```http
  GET /participant
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `List<Participant>` | Return list of participants |

Example Body Request
```bash
{
  "login":"test_login",
  "email":"test@mail.test"
}
```


## Add Participant
```http
  POST /participant/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `ResponseEntity` | Add new participant |

Example Body Request
```bash
{
    "login":"newTestParticipant",
    "email":"newTest@mail.test"
}
```

## Edit Participant
```http
  PUT /participant/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `ResponseEntity` | Change participant email |

Example Body Request
```bash
{
    "id":1,
    "login":"test_login1",
    "email":"new@mail.test"
}
```

