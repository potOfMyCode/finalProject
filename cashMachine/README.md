Cashmachine
==========================
4. Система Кассовый Аппарат. Кассир может открыть чек, добавить
выбранные товары по коду из базы данных (петрушка = 234, хлеб = 222) или
по названию товара, указать кол-во товаров или вес. Закрыть чек. Старший
кассир может отменить чек, отменить один товар в чеке и вернуть деньги
покупателю. Сделать X и Z отчеты. Товаровед может создавать товары и
указывать их кол-во на складе.
### Setup
<li>
     JDK 1.8 or higher
<li>
     Apache Maven 3.6.1 or higher
<li>
     MySQL 8.0.15 or higher
<li>
     Apache Tomcat 8.5.39 or higher
    
### Installation
* Clone project from GitHub (git clone https://github.com/potOfMyCode/hometask/tree/master/finalProject/cashMachine)
* Specify values of "mysql.user" and "mysql.password" keys in *../src/main/resources/db.property*
* Execute script _../sql/finalproject.sql_ to create database
* cd to root project folder and execute command *mvn clean install*
* copy artifact ROOT.war from target folder to %TOMCAT%\webapps
    
### Running
* Start Tomcat server by running the script %TOMCAT%\bin\startup .bat for Windows or .sh for Unix based OS.
* After server start, application will be available by URL http://localhost:8080/cashmachine  
* Use login "**admin**" and password "**admin**" to log in with administrator rights.
* Use login "**dimaC1**" or "**vasyaC2**" or "**olyaC3**" and password "**1234**" to log in with cashier rights.
* Use login "**kolyaHC1**" and password "**1234**" to log in with high cashier rights.
* Use login "**ivanPM1**" or "**katyaPM2**" and password "**1234**" to log in with productmaker rights.</li>
* To stop server run script %TOMCAT%\bin\shutdown .bat for Windows or .sh for Unix based OS.
