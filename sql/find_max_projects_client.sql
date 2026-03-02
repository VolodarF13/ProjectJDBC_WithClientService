--Створи файл find_max_projects_client.sql.
--У ньому напиши SQL, який виведе клієнта з найбільшою кількістю проєктів. Якщо таких клієнтів декілька - потрібно вивести всіх.
--
--Приклад результуючої таблиці:
--
--NAME	PROJECT_COUNT
--John Doe	3
--Mix Max	3

SELECT NAME, COUNT(p.ID) AS PROJECT_COUNT
FROM CLIENT c
JOIN PROJECT p ON c.ID = p.CLIENT_ID     -- об'єднуємо таблицю
GROUP BY c.id , c.NAME
HAVING COUNT(p.id) = (                   -- майже те саме що WHERE
    SELECT MAX(PROJECTCOUNT)             -- максимальні проекти
    FROM(
        SELECT COUNT(ID) AS PROJECTCOUNT --кількість проектів на клієнта
        FROM PROJECT
        GROUP BY CLIENT_ID
    ) as SubQuery
)
