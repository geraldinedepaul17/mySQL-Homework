DROP TABLE IF EXISTS project_category; 
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS step; 
DROP TABLE IF EXISTS category; 
DROP TABLE IF EXISTS projects;

CREATE TABLE projects (
project_id INT AUTO_INCREMENT NOT NULL,  /*primary key*/
project_name VARCHAR(128) NOT NULL, 
estimated_hours DECIMAL(7,2), 
actual_hours DECIMAL(7,2), 
difficulty INT, 
notes TEXT,
PRIMARY KEY (project_id)
);

CREATE TABLE material (
material_id INT AUTO_INCREMENT NOT NULL,  /*primary key*/
project_id INT NOT NULL,   /*foreign key*/
material_name VARCHAR(128) NOT NULL, 
num_required INT, 
cost DECIMAL(7,2), 
PRIMARY KEY (material_id), 
FOREIGN KEY (project_id) REFERENCES project (project_id)  ON DELETE CASCADE
); 

CREATE TABLE step (
step_id INT AUTO_INCREMENT  NOT NULL,  /*primary key*/
project_id INT NOT NULL,  /*foreign key*/
step_text TEXT NOT NULL,
step_order INT NOT NULL, 
PRIMARY KEY (step_id), 
FOREIGN KEY (project_id) REFERENCES project (project_id)  ON DELETE CASCADE
); 

CREATE TABLE category (
category_id INT AUTO_INCREMENT  NOT NULL, /*primary key*/
category_name VARCHAR(128) NOT NULL,
PRIMARY KEY (category_id)
); 

CREATE TABLE project_category (
project_id INT AUTO_INCREMENT NOT NULL, 
category_id INT NOT NULL, /*foreign key, unique key with category_id*/
FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE, 
FOREIGN KEY (category_id) REFERENCES category (category_id)  ON DELETE CASCADE,
UNIQUE KEY (project_id, category_id)
); 
