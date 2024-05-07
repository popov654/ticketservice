DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS events;
CREATE TABLE IF NOT EXISTS tickets (
    id varchar(80) not null primary key,
    event_id integer not null,
    owner_id integer,
    cost decimal,
    is_used tinyint
);
CREATE TABLE IF NOT EXISTS clients (
    id integer not null primary key autoincrement,
    username varchar(255),
    password varchar(255),
    email varchar(80),
    name varchar(120),
    balance decimal);
CREATE TABLE IF NOT EXISTS events (
    id integer not null primary key autoincrement,
    name varchar(500),
    date datetime
);
REPLACE INTO clients VALUES (1, 'admin', 'password', 'admin@example.com', 'Admin', 0), (2, 'anonymous', 'password', 'anonymous@example.com', 'Anonymous', 900);