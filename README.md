# SpringSecurityBoot

This application is for training my skills in security configuration. It contains the following modules:

## basic
localhost:8080/basic/
This module involves in-memory user (without a database).

- It has 2 users with hardcoded login and password. The roles are `admin` and `user`.
- It has a login/logout form.
- The `/actuator/health` endpoint is accessible without authorization.
- The `/user/hello` endpoint is accessible only for `user` and `admin` roles. It should return a 404 without authorization.
- The `/admin/hello` endpoint is accessible only for the `admin` role. It should return a 404 without authorization or for the `user` role.

## db (Database user)
localhost:8983/db/
../registration - for user registration

This module has the following requirements:

- It meets the requirements from the basic module (points 2-5).
- The login/password is stored in a relational database.
- The password is not stored as plain text.
- A user can register with the following password requirements (by default switched off):
    - Minimum 8 characters
    - At least 1 uppercase, 1 lowercase
    - At least 1 digit, 1 letter
    - At least 1 special character
- These requirements are displayed to the user on the screen.
- After 3 failed login attempts with an incorrect password, the user is locked for 20 minutes. The user cannot log in even with the correct login and password during this time. After 20 minutes, the user can log in normally.
- An admin can block any user. Such a user should not be able to log in even with the correct login and password.