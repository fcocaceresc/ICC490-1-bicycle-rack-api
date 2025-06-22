CREATE TABLE IF NOT EXISTS records (
    id BIGSERIAL PRIMARY KEY,
    student_id VARCHAR(255) NOT NULL,
    student_name VARCHAR(255) NOT NULL,
    bicycle_description VARCHAR(255) NOT NULL,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP
);
