-- scripts421.sql

-- 1. Ограничение: возраст не менее 16 лет
ALTER TABLE student
    ADD CONSTRAINT check_student_age CHECK (age >= 16);

-- 2. Ограничение: имя уникально и не null
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;
ALTER TABLE student
    ADD CONSTRAINT unique_student_name UNIQUE (name);

-- 3. Ограничение: пара "название факультета – цвет" уникальна
ALTER TABLE faculty
    ADD CONSTRAINT unique_faculty_name_color UNIQUE (name, color);

-- 4. Значение по умолчанию для возраста — 20
ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;
