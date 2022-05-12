## API Reference 

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

#### Add Participant
```http
  POST /participant/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `ResponseEntity` | Add new participant |

#### Edit Participant
```http
  PUT /participant/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|       | `ResponseEntity` | Change participant email |
