# Умный город — экосистема микросервисов

Распределённая система управления городской инфраструктурой, состоящая из 6 независимых микросервисов, взаимодействующих через REST API.

## Участники команды

- Пятых Виктория 
- Друзьяк Тимофей
- Волосков Юрий

## Архитектура

| Сервис | Порт | База данных | Назначение |
| :--- | :--- | :--- | :--- |
| Identity & Auth | 8081 | db_identity | Пользователи, аутентификация, JWT |
| Transport | 8082 | db_transport | Транспорт, парковки, бронирования |
| Utility | 8083 | db_utility | Заявки ЖКХ |
| Environment | 8084 | db_environment | Датчики и экологические данные |
| Billing & Payment | 8085 | db_billing | Счета и платежи |
| Notification | 8086 | db_notification | Централизованные уведомления |

## Технологии

- **Java 17**
- **Spring Boot 4.0.8**
- **Spring Security + JWT** (jjwt 0.12.5)
- **Spring Data JPA + Hibernate**
- **MySQL 8** (XAMPP)
- **OpenFeign** (межсервисные вызовы)
- **Maven**

## Принципы

- У каждого сервиса — своя база данных (database per service)
- Прямые соединения между БД запрещены
- Взаимодействие только через HTTP REST API
- Аутентификация через JWT: Identity выдаёт токен, остальные проверяют
- Логирование ключевых операций через SLF4J + Logback
- Обработка ошибок с корректными HTTP-статусами

## Запуск

### Требования

- JDK 17+
- Maven 3.9+
- MySQL 8 (например, через XAMPP)
- IntelliJ IDEA (или любая IDE)

### Порядок запуска

1. Запусти MySQL (XAMPP Control Panel → Start MySQL).
2. Запусти сервисы **в таком порядке** (в отдельных окнах IntelliJ):
    - Identity (8081)
    - Transport (8082)
    - Utility (8083)
    - Environment (8084)
    - Billing (8085)
    - Notification (8086)

Базы данных создаются автоматически при первом запуске (`createDatabaseIfNotExist=true`), таблицы — через Hibernate (`ddl-auto: update`).

### Проверка

- Identity: http://localhost:8081
- Transport: http://localhost:8082/vehicles
- Utility: http://localhost:8083/issues
- Environment: http://localhost:8084/sensors
- Billing: http://localhost:8085/accounts/1/invoices
- Notification: http://localhost:8086 (не имеет GET-эндпоинтов)

## API

### Identity (8081)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| POST | /auth/register | — |
| POST | /auth/login | — |
| GET | /users/me | JWT |
| GET | /users/{id} | JWT |
| GET | /users | JWT |

### Transport (8082)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| GET | /vehicles | — |
| GET | /parking | — |
| POST | /parking/{id}/reserve | JWT |

### Utility (8083)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| POST | /issues | JWT |
| GET | /issues | — |
| GET | /issues/{id} | — |
| PUT | /issues/{id} | JWT |

### Environment (8084)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| GET | /sensors | — |
| GET | /sensors/{id}/data | — |
| POST | /sensors/data | JWT |

### Billing (8085)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| GET | /accounts/{userId}/invoices | — |
| POST | /payments | JWT |
| POST | /payments/webhook/success | — |

### Notification (8086)

| Метод | Путь | Авторизация |
| :--- | :--- | :--- |
| POST | /notifications | — (внутренний) |

## Демонстрация межсервисного взаимодействия

Цепочка «Billing → Notification»:

1. Клиент создаёт платёж: `POST /payments` (Billing).
2. Платёжная система вызывает webhook: `POST /payments/webhook/success`.
3. Billing:
    - меняет статус платежа на `SUCCESS`,
    - меняет статус счёта на `PAID`,
    - вызывает Notification через OpenFeign: `POST /notifications`.

Это демонстрирует взаимодействие микросервисов **только через HTTP API**, без прямых соединений между БД.