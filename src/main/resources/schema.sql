drop table if exists department CASCADE ;
drop table if exists employee CASCADE ;
drop table if exists employee_department CASCADE ;
drop table if exists employee_project CASCADE ;
drop table if exists project CASCADE ;
create table department (department_id varchar(255) not null, name varchar(255), primary key (department_id));
create table employee (employee_id varchar(255) not null, name varchar(255), primary key (employee_id));
create table employee_department (id integer generated by default as identity, dept_id varchar(255) not null, emp_id varchar(255) not null, primary key (id));
create table employee_project (dept_id varchar(255) not null, project_id varchar(255) not null, emp_id varchar(255) not null, primary key (dept_id, project_id, emp_id));
create table project (department_id varchar(255) not null, project_id varchar(255) not null, project_name varchar(255), primary key (department_id, project_id));
alter table employee_department add constraint DEPARTMENT_FK foreign key (dept_id) references department;
alter table employee_department add constraint EMPLOYEE_FK foreign key (emp_id) references employee;
alter table employee_project add constraint PROJECT_FK foreign key (emp_id) references employee;
alter table employee_project add constraint EMP_FK foreign key (dept_id, project_id) references project;
alter table project add constraint DEPT_FK foreign key (department_id) references department;