-- ---------------------------------------------------------------------------
-- CVDB database schema
-- ---------------------------------------------------------------------------

CREATE TABLE user_roles (
  role VARCHAR(20),
  CONSTRAINT pk_user_roles PRIMARY KEY (role)
);

CREATE TABLE users (
  id          SERIAL             NOT NULL,
  name        VARCHAR(120)       NOT NULL,
  username    VARCHAR(60) UNIQUE NOT NULL,
  password    VARCHAR(30)        NOT NULL,
  role        VARCHAR(20)        NOT NULL,
  enabled     BOOLEAN            NOT NULL DEFAULT FALSE,
  modified_at TIMESTAMP          NOT NULL DEFAULT current_timestamp,
  created_at  TIMESTAMP          NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_users PRIMARY KEY (id),
  CONSTRAINT fk_user_role FOREIGN KEY (role) REFERENCES user_roles (role)
);

CREATE INDEX idx_users_role
  ON users (role);

CREATE TABLE event (
  id         SERIAL       NOT NULL,
  name       VARCHAR(100) NOT NULL,
  short_name VARCHAR(20),
  year       VARCHAR(4)   NOT NULL,
  created_at TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE INDEX idx_event_name
  ON event (name);
CREATE INDEX idx_event_short_name
  ON event (short_name);

CREATE TABLE study_programme (
  name VARCHAR(100) NOT NULL,
  CONSTRAINT pk_study_programme PRIMARY KEY (NAME)
);

CREATE TABLE student (
  id              SERIAL       NOT NULL,
  first_name      VARCHAR(60)  NOT NULL,
  last_name       VARCHAR(60)  NOT NULL,
  year            INTEGER      NOT NULL,
  study_programme VARCHAR(100) NOT NULL,
  modified_at     TIMESTAMP    NULL DEFAULT current_timestamp,
  created_at      TIMESTAMP    NULL DEFAULT current_timestamp,
  CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE INDEX idx_student_study_programme
  ON student (study_programme);

CREATE TABLE participation (
  student_id INTEGER      NOT NULL,
  event_id   INTEGER      NOT NULL,
  cv         VARCHAR(255) NOT NULL,
  CONSTRAINT pk_participation PRIMARY KEY (student_id, event_id),
  CONSTRAINT fk_participation_student FOREIGN KEY (student_id) REFERENCES student (id)
  ON DELETE CASCADE,
  CONSTRAINT fk_participation_event FOREIGN KEY (event_id) REFERENCES event (id)
  ON DELETE CASCADE
);

CREATE TABLE subscription (
  user_id            INTEGER   NOT NULL,
  event_id           INTEGER   NOT NULL,
  subscription_start TIMESTAMP NOT NULL DEFAULT current_timestamp,
  subscription_end   TIMESTAMP NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_subscription PRIMARY KEY (user_id, event_id),
  CONSTRAINT fk_subscription_user FOREIGN KEY (user_id) REFERENCES student (id)
  ON DELETE CASCADE,
  CONSTRAINT fk_subscription_event FOREIGN KEY (event_id) REFERENCES event (id)
  ON DELETE CASCADE
);

INSERT INTO user_roles (role) VALUES ('ADMIN');
INSERT INTO user_roles (role) VALUES ('COMPANY_USER');

INSERT INTO study_programme (name) VALUES ('COMPUTING');
INSERT INTO study_programme (name) VALUES ('ELECTROTECHNIC');
INSERT INTO study_programme (name) VALUES ('TELECOMMUNICATIONS');
INSERT INTO study_programme (name) VALUES ('SOFTWARE_ENGINEERING');
INSERT INTO study_programme (name) VALUES ('COMPUTING_SCIENCE');
INSERT INTO study_programme (name) VALUES ('COMPUTER_ENGINEERING');
INSERT INTO study_programme (name) VALUES ('INFORMATION_PROCESSING');
INSERT INTO study_programme (name) VALUES ('AUTOMATIZATION');
INSERT INTO study_programme (name) VALUES ('ELECTRONIC_AND_COMPUTER_ENGINEERING');
INSERT INTO study_programme (name) VALUES ('ELECTRONICS');
INSERT INTO study_programme (name) VALUES ('ELECTRICAL_POWER_ENGINEERING');
INSERT INTO study_programme (name) VALUES ('WIRELESS_SYSTEMS');
