--Завдання №7 - вивести вартість кожного проєкту
--Створи файл print_project_prices.sql. У ньому напиши SQL, який виведе список проєктів та вартість кожного проєкту.
--
--Вартість проєкту - це сума заробітних плат працівників, що працюють над цим проєктом, помножених на тривалість у місяцях проєкту.
--
--Наприклад, над проєктом Project A працюють працівники Max (заробітна плата 1000) та Joe (заробітна плата 1500).
--Проєкт триває 17 місяців. Тоді вартість проєкту Project A = 17 * (1000 + 1500) = 42500
--
--Відсортуй проєкти по спаданню - спочатку виведи найдорожчі проєкти.
--
--Приклад таблиці:
--
--NAME	PRICE
--Project A	42500
--Project B	18000

SELECT p.ID AS PROJECT_ID,
    SUM(w.salary) * DATEDIFF('MONTH', p.start_date, p.finish_date) AS PRICE
FROM project p
JOIN project_worker pw ON p.ID = pw.project_id
JOIN worker w ON pw.worker_id = w.ID
GROUP BY p.ID, p.start_date,  p.finish_date
ORDER BY PRICE DESC;
