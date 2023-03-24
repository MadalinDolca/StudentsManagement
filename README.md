# Students Management



🗓️ Developed during the December of 2020

⚙️ The managing companion tool of this app can be seen [here](https://github.com/MadalinDolca/ControlPanel).

<img src="art/main_role_admin.png" width="500px"/>

## 🌟 Features
- User authentication
- Role-based access
- Database connection handler
- Network connection handler
- **Students Data**
  - Show every student of the school and their data
  - Show the history of student data changes
  - Add a new student to the system
  - Update the data of a selected student
- **Classes**
  - View all the students of a selected class
  - Add a student to a class
  - Move a student to another class
- **Gradebook**
  - Show the grades of a student in a certain class from a certain subject
  - Add a new regular grade or the term paper's grade 
  - Modify an existing grade
  - Delete an existing grade
  - Export the student's grades to a file
  - Show the arithmetic mean of the grades obtained by the selected student at a selected subject
  - Mark the absence of a selected student to the selected subject
  - Modify a selected absence
- **Schoolarships**
  - View all the students that have the selected scholarship
  - Assign students to scholarships
  - Chnage the scholarship of a selected user

## 🔮 Technologies
- Java for the app logic
- Java Swing for the UI
- MySQL database
- MySQL Connector to connect to the database

## 👀 Preview

| Login | Main interface (teacher) |
| - | - |
| <img src="art/login.png" width="300px"/> | <img src="art/main_role_teacher.png" width="500px"/> |

### Students data

| Students data | Logs |
| :-: | :-: |
| <img src="art/student_data.png" width="500px"/> | <img src="art/student_data_log.png" width="500px"/> |
| Add student data | Update student data |
| <img src="art/student_data_student_add.png" width="500px"/> | <img src="art/student_data_student_update.png" width="500px"/> |

### Class view

| Class view (admin) | Class view (teacher) |
| :-: | :-: |
| <img src="art/class_role_admin.png" width="500px"/> | <img src="art/class_role_teacher.png" width="500px"/> |
| Add student to class | Change student class |
| <img src="art/class_student_add.png" width="500px"/> | <img src="art/class_student_update.png" width="500px"/> |

### Gradebook

| Gradebook view | Select student  |
| :-: | :-: |
| <img src="art/gradebook.png" width="500px"/> | <img src="art/gradebook_select_student.png" width="500px"/> |
| Select subject | Export notes
| <img src="art/gradebook_select_subject.png" width="500px"/> | <img src="art/gradebook_export.png" width="500px"/> |
| Add grade | Modify grade |
| <img src="art/gradebook_grade_add.png" width="500px"/> | <img src="art/gradebook_grade_update.png" width="500px"/> |
| Add absence | Modify absence |
| <img src="art/gradebook_absence_add.png" width="500px"/> | <img src="art/gradebook_absence_update.png" width="500px"/> |

### Scholarships

| Scholarships (teacher view) | Select scholarship |
| :-: | :-: |
| <img src="art/scholarship.png" width="500px"/> | <img src="art/scholarships_select.png" width="500px"/> |
| Add scholarship | Update student scholarship |
| <img src="art/scholarship_student_add.png" width="500px"/> | <img src="art/scholarship_student_update.png" width="500px"/> |
