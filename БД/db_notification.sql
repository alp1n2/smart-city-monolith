-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 26 2026 г., 07:07
-- Версия сервера: 10.4.32-MariaDB
-- Версия PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `db_notification`
--

-- --------------------------------------------------------

--
-- Структура таблицы `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `channel` varchar(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `message` mediumtext NOT NULL,
  `sent_at` datetime(6) DEFAULT NULL,
  `source_service` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `notifications`
--

INSERT INTO `notifications` (`id`, `channel`, `created_at`, `message`, `sent_at`, `source_service`, `status`, `subject`, `user_id`) VALUES
(1, 'EMAIL', '2026-09-25 16:19:42.000000', 'Привет из Notification', '2026-09-25 16:19:42.000000', 'TEST', 'SENT', 'Тест', 1),
(2, 'EMAIL', '2026-09-25 16:22:54.000000', 'Счёт №2 на сумму 2500.00 RUB оплачен.', '2026-09-25 16:22:54.000000', 'BILLING', 'SENT', 'Платёж прошёл', 1),
(3, 'EMAIL', '2026-09-26 04:42:24.000000', 'Привет пока', '2026-09-26 04:42:24.000000', 'DASHBOARD', 'SENT', 'Тест2', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
