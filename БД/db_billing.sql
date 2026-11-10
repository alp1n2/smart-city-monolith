-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 26 2026 г., 07:06
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
-- База данных: `db_billing`
--

-- --------------------------------------------------------

--
-- Структура таблицы `invoices`
--

CREATE TABLE `invoices` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` datetime(6) NOT NULL,
  `service_type` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `invoices`
--

INSERT INTO `invoices` (`id`, `amount`, `created_at`, `currency`, `description`, `due_date`, `service_type`, `status`, `updated_at`, `user_id`) VALUES
(1, 1500.00, '2026-09-25 23:30:29.000000', 'RUB', 'Оплата ЖКХ за сентябрь', '2026-10-31 23:59:59.000000', 'UTILITY', 'PAID', '2026-09-25 15:36:00.000000', 1),
(2, 2500.00, '2026-09-26 00:20:52.000000', 'RUB', 'Оплата за октябрь', '2026-11-30 23:59:59.000000', 'UTILITY', 'PAID', '2026-09-25 16:22:54.000000', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `payments`
--

CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `completed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `invoice_id` bigint(20) NOT NULL,
  `method` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `transaction_id` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `payments`
--

INSERT INTO `payments` (`id`, `amount`, `completed_at`, `created_at`, `invoice_id`, `method`, `status`, `transaction_id`, `user_id`) VALUES
(1, 1500.00, '2026-09-25 15:36:00.000000', '2026-09-25 15:33:37.000000', 1, 'CARD', 'SUCCESS', '2599c2aa-4fbe-48d3-8f72-d9cf7a34f933', 1),
(2, 2500.00, '2026-09-25 16:22:54.000000', '2026-09-25 16:22:23.000000', 2, 'CARD', 'SUCCESS', '92d5d7ca-471c-45e9-a821-36a88fa5791c', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `invoices`
--
ALTER TABLE `invoices`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `payments`
--
ALTER TABLE `payments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
