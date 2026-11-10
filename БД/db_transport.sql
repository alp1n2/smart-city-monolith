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
-- База данных: `db_transport`
--

-- --------------------------------------------------------

--
-- Структура таблицы `parking`
--

CREATE TABLE `parking` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `available_spots` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `price_per_hour` decimal(10,2) NOT NULL,
  `total_spots` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `parking`
--

INSERT INTO `parking` (`id`, `address`, `available_spots`, `latitude`, `longitude`, `name`, `price_per_hour`, `total_spots`) VALUES
(1, 'Красная площадь, 1', 49, 55.7539, 37.6208, 'Парковка у Кремля', 100.00, 50),
(2, 'Комсомольская площадь, 2', 99, 55.775, 37.655, 'Парковка у вокзала', 80.00, 100);

-- --------------------------------------------------------

--
-- Структура таблицы `parking_reservations`
--

CREATE TABLE `parking_reservations` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `parking_id` bigint(20) NOT NULL,
  `reserved_from` datetime(6) NOT NULL,
  `reserved_to` datetime(6) NOT NULL,
  `status` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `vehicle_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `parking_reservations`
--

INSERT INTO `parking_reservations` (`id`, `created_at`, `parking_id`, `reserved_from`, `reserved_to`, `status`, `user_id`, `vehicle_number`) VALUES
(1, '2026-09-25 12:38:02.000000', 1, '2026-01-01 02:00:00.000000', '2026-01-01 04:00:00.000000', 'ACTIVE', 1, 'A123BC'),
(2, '2026-09-26 04:37:35.000000', 2, '2026-09-25 21:37:34.000000', '2026-09-25 22:37:34.000000', 'ACTIVE', 2, 'T456DE');

-- --------------------------------------------------------

--
-- Структура таблицы `vehicles`
--

CREATE TABLE `vehicles` (
  `id` bigint(20) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `model` varchar(100) DEFAULT NULL,
  `number` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `type` varchar(50) NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `vehicles`
--

INSERT INTO `vehicles` (`id`, `latitude`, `longitude`, `model`, `number`, `status`, `type`, `updated_at`) VALUES
(1, 55.7558, 37.6173, 'LiAZ-5292', 'A123BC', 'ACTIVE', 'BUS', '2026-09-25 20:28:48.000000'),
(2, 55.76, 37.62, 'Vityaz-M', 'T456DE', 'ACTIVE', 'TRAM', '2026-09-25 20:28:48.000000'),
(3, 55.75, 37.61, 'Trolza-5265', 'TR789FG', 'MAINTENANCE', 'TROLLEYBUS', '2026-09-25 20:28:48.000000');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `parking_reservations`
--
ALTER TABLE `parking_reservations`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKdt9xs92iuuwcietw5bh6wk0tw` (`number`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `parking`
--
ALTER TABLE `parking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `parking_reservations`
--
ALTER TABLE `parking_reservations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
