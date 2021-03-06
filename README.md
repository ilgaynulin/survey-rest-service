# Survey Rest Service

## Инструкция по запуску

Клонируем проект с помощью командной строки выполнив команду
```
git clone https://github.com/ilgaynulin/survey-rest-service.git
```
Переходим в корневой каталог клонированного проекта и выполняем следующие команды
```
./mvnw install
docker build -f Dockerfile -t spring-survey-app .
docker pull postgres:alpine
docker-compose up
```
Приложение развёрнуто и принимает запросы на
```
http://localhost:8080/
```
В Swagger можно попасть по адресу
```
http://localhost:8080/swagger-ui.html
```

## Задание

Необходимо создать REST - сервис для управления справочными данными.

#### Структура данных:

Опрос|
--------------------|
Наименование опроса |
Дата начала |
Дата окончания |
Активность (да/нет) |

Вопрос опроса|
-----------------|
Ссылка на опрос|
Текст вопроса|
Порядок отображения|

#### REST-сервис должен предоставлять следующие методы:
- Получить все опросы (Опционально можно передать фильтр по наименованию, дате, активности. Обязательно указание сортировки: по наименованию или по дате начала опроса. Должна поддерживаться пагинация.)
- Создание опроса
- Редактирование опроса
- Удаление опроса

Cервис должен предоставлять документацию с использованием Swagger.

Cервис при первом запуске должен самостоятельно создавать необходимые объекты в БД с помощью Liquibase.
Необходимо использовать PostgreSQL.

Для реализации необходимо использовать Java 8, Spring Boot, Hibernate, PostgreSQL, Liquibase.

Исходный код загрузить в GitHub и в описании указать краткую инструкцию по запуску приложения (применение Docker/ docker-compose приветствуется).

!В качестве усложнения задания: реализовать отдельные сервисы для данных «Опрос» и «Вопрос опроса» и сделать дополнительный сервис, который бы агрегировал первые два. 
