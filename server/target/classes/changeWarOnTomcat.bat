cd C:\Users\kriscool\hessian\chat-hessian
call mvn clean install
call rmdir /s /q C:\apache-tomcat-9.0.12\webapps\hessian-service
call del -q C:\apache-tomcat-9.0.12\webapps\hessian-service.war
call copy C:\Users\kriscool\hessian\chat-hessian\target\hessian-service.war C:\apache-tomcat-9.0.12\webapps
call cd C:\apache-tomcat-9.0.12\bin
call startup.bat