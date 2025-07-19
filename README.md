CRIME RECORD MANAGEMENT SYSTEM

Crime Record Management System-JavaFX Application
Overview:
This project is a desktop application built using JavaFX to manage and track crime-related information. The system includes separate dashboards for Admins, Civilian, and Officers, offering role-specific functionalities, and provides a login and registration system for user authentication.
Modules:
1.	Registration & Login
•	Registration allows new users (civilians) to create an account.
•	Login screen allows registered users to sign in.
•	Credentials and user’s role (Admin, Civilian, Officer) are validated and stored in (Firebase) 
Dashboards:
•	Admin Dashboard: 
- FXML Controller: AdminDashboardController
-UI Elements: 
                        - Label: Welcome, Admin
                      - Buttons:
                         . Management Users-View/edit/delete users (officers, civilians)
                          . View Reports- View crime and System reports
                           . System Settings – Administer system configurations
-	Logout Button
Civilian Dashboard:
FXML Controller: CiviliantDashboardController
-	UI Elements:
        . Label: Welcome, Civilian 
             . Buttons:
                   . File Complaint -Sumit a new complaint
                     . View FIRs-View filed FIRs 
                      . Criminal Info-Browse criminal database
. Logout Button

Officer Dashboard:
FXML Controller: OfficerDashboardController
-	UI Elements:
-	Label: Welcome, Officer
-	Buttons: 
. Manage Cases-Assign/update case files
. View Assignments-See assigned cases
Report Status-Submit reports/status updates
               . Logout Status Button

Technologies Used 
JavaFX: For user interface 
FXML: Declaration UI design
Java: Core application logic 
Controllers: AdminDashboardController,  CivilianDashboardController,  OfficerDashboardControlller
Database: Firebase
Styling: Inline CSS for consistent UI design

Roles & Permissions
Role: 
Admin: Full access to system setting and use data
Civilian: Can file complaints and view related FIRs
Officer: Can manage cases and assignments 

This JavaFX application is a structured, role-based crime management system, with clear navigation and user-friendly interfaces tailored to each type or user. It is ideal for desktop use in local police stations or administrative offices. 


#===========================================HOW TO RUN THE PROJECT =========================
# Prerequisite 
[IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community or Ultimate)
- Java JDK 17 or compatible
- JavaFX SDK 21 || 24
- Internet connection (for Firebase)
- Firebase project credentials
-
  # Setup
  ```bash
   git clone https://github.com/CSC325-SEF/crime_record_management.git
   cd crime_record_management
 Download Firebase SDK, rename it [serviceAccountKey.json]
- Copy and paste it inside [files]
- Use Crime Record potal to register/login

  



