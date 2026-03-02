--Завдання №3 - знайти працівника з найбільшою заробітною платою
--Створи файл find_max_salary_worker.sql.
--У ньому напиши SQL, який виведе працівника з найбільшою заробітною платою. Якщо таких працівників декілька - потрібно вивести всіх.
--
--Приклад результуючої таблиці:
--
--NAME	SALARY
--John Doe	1000


SELECT NAME, SALARY FROM worker WHERE SALARY IN(
SELECT MAX(SALARY) FROM worker
);
