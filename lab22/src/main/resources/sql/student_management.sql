create database if not EXISTS student_management;

create table student_management.students (
    id int not null auto_increment,
    name varchar(45) default null,
    email varchar(45) default null,
    address varchar(45) default null,
    phone varchar(15) DEFAULT null,
    primary key (id)
);

insert into student_management.students(name, email, address, phone) values
('Khanh', 'khanh@gmail.com', 'Binh Duong', '0357863388'),
('Phuc', 'phuc@gmail.com', 'DAKLaK', '0357863128'),
('Son', 'so@gmail.com', 'Dong Thap', '0309287353')
;
