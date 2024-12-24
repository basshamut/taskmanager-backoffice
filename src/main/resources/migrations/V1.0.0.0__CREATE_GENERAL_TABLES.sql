-- Tabla de propietarios
CREATE TABLE owners (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(256) NOT NULL,
    dueDate VARCHAR(15),
    state VARCHAR(20) NOT NULL
);
