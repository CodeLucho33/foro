-- Crear tabla de usuarios
CREATE TABLE users (
                       id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL
);

-- Crear tabla de perfiles
CREATE TABLE profiles (
                          id_profile BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL UNIQUE
);

-- Tabla intermedia para relación ManyToMany entre usuarios y perfiles
CREATE TABLE user_profiles (
                               user_id BIGINT NOT NULL,
                               profile_id BIGINT NOT NULL,
                               PRIMARY KEY (user_id, profile_id),
                               FOREIGN KEY (user_id) REFERENCES users(id_user) ON DELETE CASCADE,
                               FOREIGN KEY (profile_id) REFERENCES profiles(id_profile) ON DELETE CASCADE
);

-- Crear tabla de cursos
CREATE TABLE courses (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         category VARCHAR(255) NOT NULL
);

-- Crear tabla de tópicos
CREATE TABLE topics (
                        id_topic BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        message TEXT NOT NULL,
                        date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status BOOLEAN NOT NULL DEFAULT TRUE,
                        author_id BIGINT NOT NULL,
                        course_id BIGINT NOT NULL,
                        FOREIGN KEY (author_id) REFERENCES users(id_user) ON DELETE CASCADE,
                        FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Crear tabla de respuestas
CREATE TABLE responses (
                           id_response BIGINT AUTO_INCREMENT PRIMARY KEY,
                           message TEXT NOT NULL,
                           topic_id BIGINT NOT NULL,
                           date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           author_id BIGINT NOT NULL,
                           is_solve BOOLEAN NOT NULL DEFAULT FALSE,
                           FOREIGN KEY (topic_id) REFERENCES topics(id_topic) ON DELETE CASCADE,
                           FOREIGN KEY (author_id) REFERENCES users(id_user) ON DELETE CASCADE
);
