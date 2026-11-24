INSERT INTO departments (name, description)
VALUES
    ('IT', 'Information Technology'),
    ('HR', 'Human Resources'),
    ('Finance', 'Finance Department'),
    ('Quality', 'Quality Assurance');

INSERT INTO employees (name, email, salary, department_id)
VALUES
    ('Rahul', 'rahul@example.com', 50000,
        (SELECT id FROM departments WHERE name = 'IT')),

    ('Priya', 'priya@example.com', 45000,
        (SELECT id FROM departments WHERE name = 'HR')),

    ('Arjun', 'arjun@example.com', 60000,
        (SELECT id FROM departments WHERE name = 'Finance'));