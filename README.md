<p align="center">
  <img width="400" height="400" alt="calling_6738322" src="https://github.com/user-attachments/assets/295e3232-8506-46bd-a023-e92ce5411c67" />
</p>


# 📱 Mobile Contact App

• Author: Jakub Tokarski, Index: 51700 

• Completion Date: May 24, 2025  

## 📄 Project Description

This mobile application is designed to help users manage personal contacts through a secure and intuitive interface. 

Built with Kotlin and Jetpack Compose, it offers a modern user experience with smooth navigation, responsive layouts, and clean visual design. Users can register and log in using Firebase Authentication, ensuring that only verified accounts can access the contact dashboard.

Once authenticated, users can create, view, edit, and delete contacts, each containing a name, surname, and phone number. 

The app includes built-in validation to prevent incorrect or incomplete entries, and features a direct call function that allows users to initiate phone calls straight from the contact list. 

All data is stored locally, with session persistence handled via Shared Preferences to streamline the login experience.

## ✨ Features

• Custom Authentication Interface – The app features a tailored login screen with personalized input fields for email and password, including a visibility toggle and real-time validation to ensure secure and smooth access.

• Firebase-Backed Registration – New users can register by providing their credentials, which are securely stored using Firebase Authentication. This integration ensures reliable account creation and identity management.

• Credential-Based Access Control – Users log in using their registered credentials. Invalid login attempts trigger immediate feedback, while successful authentication grants access to the contact management interface.

• Interactive Home Screen – Upon login, users are directed to a clean and responsive Home screen featuring a contact widget. This component allows users to quickly add new contacts with intuitive input fields.

• Contact Management – Users can create contacts by entering a first name, surname, and phone number. Each contact is displayed in a scrollable list and includes a built-in call function for direct dialing from the app.

• Edit & Delete Functionality – Contacts can be updated or removed at any time. The interface supports full CRUD operations, with changes reflected instantly in the contact list.

• Robust Input Validation – The app enforces strict validation rules to maintain data integrity. For example, name fields accept only alphabetic characters, and phone number fields reject non-numeric input, preventing users from entering letters or symbols where numbers are required.


## 🛠️ Technologies Used

- **Jetpack Compose** – Framework to build mobile applications for Android system, using Kotlin language

- **Kotlin** – A modern, statically typed programming language used for building Android applications, including application logic and user interfaces
  
- **Android Studio** – Integrated development environment (IDE) for building and testing the app
  
- **Firebase Authentication** – Secure user login and registration

## 📄 License

This application was developed as part of academic coursework at **Uniwersytet Dolnośląski DSW Wrocław** and is intended solely for educational purposes.

All icons used in this file were sourced from the following website: https://pl.freepik.com
