INSERT INTO worker ("NAME", BIRTHDAY, "LEVEL", SALARY) VALUES
('Olena Kozlova', '1990-01-01', 'Trainee', 899),
('Vira Kulakowa', '1985-03-15', 'Trainee', 899),
('Ivan Kryshyna', '1980-05-20', 'Junior', 999),
('Daria Tychenko', '1975-07-10', 'Junior', 1099),
('Maksym Shovkovec', '1992-09-05', 'Junior', 1199),
('Anna Korolenko', '1987-11-12', 'Middle', 1599),
('Anton Konopenko', '1982-04-18', 'Middle', 1899),
('Natalia Bzdoch', '1978-06-25', 'Middle', 2499),
('Olga Rendchen', '1995-08-30', 'Senior', 3999),
('Mira Sokolovska', '1989-10-22', 'Senior', 5999);

INSERT INTO client (ID, "NAME") VALUES
(1, 'Britta Shevchuk'),
(2, 'Olle Sumin'),
(3, 'Lasse Sumi'),
(4, 'Lisa Sojer'),
(5, 'Agda Macko'),
(6, 'Alex Run');

INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    (1, 1, '2023-01-01', '2023-02-15'),
    (2, 2, '2023-02-05', '2023-05-20'),
    (3, 3, '2023-03-10', '2023-07-25'),
    (4, 4, '2023-04-15', '2023-10-30'),
    (5, 5, '2023-05-20', '2023-12-05'),
    (6, 6, '2023-06-25', '2024-05-12'),
    (7, 1, '2023-07-30', '2024-10-18'),
    (8, 2, '2023-08-05', '2025-12-22'),
    (9, 2, '2023-09-10', '2027-02-28'),
    (10, 1, '2023-10-15', '2029-04-05');

  INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1),
    (2, 1),
    (2, 2),
    (3, 2),
    (4, 2),
    (4, 3),
    (4, 4),
    (4, 5),
    (5, 5),
    (5, 6),
    (5, 7),
    (6, 7),
    (7, 7),
    (7, 8),
    (8, 8),
    (9, 9),
    (10, 9),
    (10, 10);