## API Reference 

#### Show conference with details about topics and participants
```http
  GET /conference/details
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `List<ThemeConference>` | Return list of conferences with details |





#### Show conference with details about topics and participants by ID
```http
  GET /conference/details/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id`    | `ThemeConference` | Return conference with details by ID |


#### Show conference with details about topics and participants by ID
```http
  POST /conference/{id}/participant/{login}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id, login`    | `ResponseEntity` | Sign participant to conference by theme conference ID and participant unique Login|


#### Show conference with details about topics and participants by ID
```http
  DEL /conference/{id}/participant/{login}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|   `id, login`    | `ResponseEntity` | Remove participant from conference by theme conference ID and participant unique Login|



#### Get all Conferences

```http
  GET /conference
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
|           | `List<Conference>` | Return list of conferences Time Schedule |

#### Get Conference

```http
  GET /conference/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `List<Conference>` | Return conference by ID |

#### Get Participants



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


#### Add Participant
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

#### Edit Participant
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

