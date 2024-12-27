-- Tabla de propietarios
CREATE TABLE tasks (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(256) NOT NULL,
    due_date DATE NOT NULL,
    state VARCHAR(20) NOT NULL
);
