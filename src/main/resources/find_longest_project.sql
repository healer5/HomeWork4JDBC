SELECT p.ID, p.CLIENT_ID, c."NAME" AS CLIENT_NAME, p.START_DATE, p.FINISH_DATE,
       EXTRACT(MONTH FROM AGE(p.FINISH_DATE, p.START_DATE)) AS MONTH_COUNT
FROM project p
JOIN client c ON p.CLIENT_ID = c.ID
WHERE EXTRACT(MONTH FROM AGE(p.FINISH_DATE, p.START_DATE)) = (
    SELECT MAX(EXTRACT(MONTH FROM AGE(FINISH_DATE, START_DATE)))
    FROM project
);