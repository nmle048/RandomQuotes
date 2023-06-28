CREATE TABLE authors (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(255) NULL
);

CREATE TABLE quotes (
  id INT PRIMARY KEY NOT NULL,
  quote VARCHAR(500) NULL,
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES authors(id)
);
