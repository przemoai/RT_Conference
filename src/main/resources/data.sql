INSERT INTO CONFERENCE(id,title,start,finish) VALUES (1,'KONFERENCJA NR 1','2021-06-01 10:00:00','2021-06-01 11:45:00');
INSERT INTO CONFERENCE(id,title,start,finish) VALUES (2,'KONFERENCJA NR 2','2021-06-01 12:00:00','2021-06-01 13:45:00');
INSERT INTO CONFERENCE(id,title,start,finish) VALUES (3,'KONFERENCJA NR 3','2021-06-01 14:00:00','2021-06-01 15:45:00');

INSERT INTO TOPIC (id,title) VALUES (1,'JAVA');
INSERT INTO TOPIC (id,title) VALUES (2,'PHP');
INSERT INTO TOPIC (id,title) VALUES (3,'JS');

INSERT INTO CONFERENCE_TOPICS(id,conference_id,topic_id)VALUES(1,1,1);
INSERT INTO CONFERENCE_TOPICS(id,conference_id,topic_id)VALUES(2,1,2);
INSERT INTO CONFERENCE_TOPICS(id,conference_id,topic_id)VALUES(3,1,3);

INSERT INTO CONFERENCE_TOPICS(id,conference_id,topic_id)VALUES(4,2,1);