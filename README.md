# To Run application

> Install mysql database
 
> Install Intellij 

> Join `pom.xml` and ran maven to install all dependencies

> Build whole application

> Join `application.properties` and change `spring.datasource.url` to your database name, like replace AliShiyyab to your database name.


## How to install `mysql`

> https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04


## Install Intellij Community version

> https://www.jetbrains.com/idea/promo/?source=google&medium=cpc&campaign=9730674410&term=intellij%20idea&content=602143185274&gclid=Cj0KCQiA3eGfBhCeARIsACpJNU_8Q5VVVmhAOg210IV64-iHUQ_6fvh3S0ep5Wswp9J9jYSx69ksSFwaAiI9EALw_wcB

## How To Create New Database

* Install MYSQL
* Create username and password in mysql
  * Joine `https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql` to create new user name : `root` with password : `root`
* join mysql by user: 
  * mysql -u username -p
  * when click enter terminal ask you to enter your password and enter password which add when create your user.
* when you join enter : CREATE DATABASE NAME;  `I use AliShiyyab as a name and put it in the application.properties`
* Run Application to create a new table
* insert in roles table three columns:
  * insert into roles (name) values("CUSTOMER");
  * insert into roles (name) values("ADMIN_STORE");
  * insert into roles (name) values("ROLE_ADMIN");
* After all steps, you can test login and register api.