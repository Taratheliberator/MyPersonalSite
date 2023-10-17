# Используйте официальный базовый образ Java
FROM openjdk:22

# Копируйте JAR-файл в контейнер
COPY target/MyPersonalSite-0.0.1-SNAPSHOT.jar app.jar

# Запуск приложения
ENTRYPOINT ["java","-jar","/app.jar"]
