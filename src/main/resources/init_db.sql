create sequence worker_seq start 1;
create sequence client_seq start 1;
create sequence project_seq start 1;

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