-- СТУДЕНТ + ФАКУЛЬТЕТ (INNER JOIN)
SELECT s.name AS student_name,
       s.age AS student_age,
       f.name AS faculty_name
FROM student s
INNER JOIN faculty f ON s.faculty_id = f.id;
-- СТУДЕНТ + АВАТАРКА (INNER JOIN)
SELECT s.name AS student_name
FROM student s
INNER JOIN avatar a ON a.student_id = s.id;
