CREATE TABLE System (
	Username Varchar(20) Primary Key,
	Password Varchar(60),
	LastCohort int,
	SecretQn Varchar(100),
	SecretAns Varchar(100)
);

INSERT into System values ('Heather', '$2a$10$lSrx0hKlJi4W3Z8ZCw8.OO7t5AuJ6FyykM4I94uEYKeQviJNqpC5W', 20132, null, null);
INSERT into System values ('admin', '$2a$10$lSrx0hKlJi4W3Z8ZCw8.OO7t5AuJ6FyykM4I94uEYKeQviJNqpC5W', null, null, null);

