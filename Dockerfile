FROM tomcat
USER root

EXPOSE 8081:8080
EXPOSE 5432:5432

COPY target/MyCalories.war /usr/local/tomcat/webapps/

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]