--Завдання №6 - знайти найстаршого та наймолодшого працівника
--Створи файл find_youngest_eldest_workers.sql.
--У ньому напиши SQL, який вибере наймолодшого та найстаршого працівників, та виведе їх у форматі таблиці з наступними полями:
--
--TYPE - тип (може бути YOUNGEST або OLDEST)
--NAME - ім'я працівника
--BIRTHDAY - дата народження працівника
--Якщо є декілька наймолодших/найстарших працівників - то необхідно вивести їх всіх.
--
--Приклад таблиці:
--
--TYPE	NAME	BIRTHDAY
--YOUNGEST	John Doe	2000-01-07
--YOUNGEST	John Doe	2000-01-07
--ELDEST	Maxim	1980-06-17

SELECT 'YOUNGEST' as TYPE, NAME, BIRTHDAY
FROM worker
WHERE BIRTHDAY  = (SELECT MAX(BIRTHDAY) FROM worker)
UNION ALL
SELECT 'OLDEST' as TYPE, NAME, BIRTHDAY
FROM worker
WHERE BIRTHDAY = (SELECT MIN(BIRTHDAY) FROM worker)
