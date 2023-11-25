create sequence worker_seq;
create sequence client_seq;
create sequence project_seq;

-- Set the starting value for the sequences
ALTER SEQUENCE worker_seq RESTART WITH 1;
ALTER SEQUENCE client_seq RESTART WITH 1;
ALTER SEQUENCE project_seq RESTART WITH 1;

CREATE TYPE workerlevel AS ENUM ('Trainee', 'Junior', 'Middle', 'Senior');

create table worker (
    ID bigint primary key default nextval('worker_seq'),
    "NAME" varchar(1000) not null check(length("NAME"::text) >= 2 and length("NAME"::text) <= 1000),
    BIRTHDAY DATE check(EXTRACT(YEAR FROM BIRTHDAY) > 1900),
    "LEVEL" workerlevel NOT NULL,
    SALARY INT CHECK(SALARY >= 100 AND SALARY <= 100000)
);

create table client (
    ID bigint primary key default nextval('client_seq'),
    "NAME" varchar(1000) not null check(length("NAME"::text) >= 2 and length("NAME"::text) <= 1000)
);

create table project (
    ID bigint primary key default nextval('project_seq'),
    CLIENT_ID bigint,
    START_DATE date,
    FINISH_DATE date
);

create table project_worker (
    PROJECT_ID bigint,
    WORKER_ID bigint,
    PRIMARY KEY (PROJECT_ID, WORKER_ID),
    FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
    FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
)