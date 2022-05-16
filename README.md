# projectMangement


Need to design and implement a Rest API within the following constraints. Expectation - working code with automated test, in zip/github/gitlab/bitbucket/etc
A Rest API which can only be used to retrieve all Employees associated with a given project.
Define and implement the API/URL; how will parameter be passed to the API; how success or failure will be reported by the API. Anything else you feel will be relevant to the caller of your API
A Rest API to dissociate an employee from a specific project. 
Constraints: (Do not change or alter)
This is a system consisting of 3 logical entities, Employee, Department, Project
An Employee can be identified by a unique employee code - empcode - e.g. E10001, E10002, etc
A Department can be identified by a unique department code - departcode - e.g. D101, D102, etc
A Project can be identified by a combination of two identifiers - 
project code - projcode - e.g. P1001, P1002, etc
and department code - departcode (the department to which the project belongs to)
i.e. there can be two projects with the same pid, say P1001, but they will be associated to two different departments, say D101, D102.
An employee can be associated with one or more departments.
A department can have one or more projects.
An employee can be part of one or more projects.
If using RDBMS configure the project to use H2. And provide DDL and DML scripts. 
