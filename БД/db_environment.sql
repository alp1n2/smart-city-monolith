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
-- База данных: `db_environment`
--

-- --------------------------------------------------------

--
-- Структура таблицы `sensors`
--

CREATE TABLE `sensors` (
  `id` bigint(20) NOT NULL,
  `installed_at` datetime(6) NOT NULL,
  `latitude` double NOT NULL,
  `location` varchar(255) NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `sensors`
--

INSERT INTO `sensors` (`id`, `installed_at`, `latitude`, `location`, `longitude`, `name`, `status`, `type`) VALUES
(1, '2026-09-25 22:16:14.000000', 55.7649, 'ул. Тверская, 1', 37.6055, 'Датчик воздуха на Тверской', 'ACTIVE', 'AIR'),
(2, '2026-09-25 22:16:14.000000', 55.775, 'Комсомольская пл., 2', 37.655, 'Датчик шума у вокзала', 'ACTIVE', 'NOISE'),
(3, '2026-09-25 22:16:14.000000', 55.733, 'Крымская наб., 10', 37.601, 'Датчик воды на Москве-реке', 'INACTIVE', 'WATER');

-- --------------------------------------------------------

--
-- Структура таблицы `sensor_data`
--

CREATE TABLE `sensor_data` (
  `id` bigint(20) NOT NULL,
  `recorded_at` datetime(6) NOT NULL,
  `sensor_id` bigint(20) NOT NULL,
  `unit` varchar(20) NOT NULL,
  `value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `sensor_data`
--

INSERT INTO `sensor_data` (`id`, `recorded_at`, `sensor_id`, `unit`, `value`) VALUES
(1, '2026-09-25 14:18:17.000000', 1, 'AQI', 42.5),
(2, '2026-09-26 04:39:34.000000', 1, 'AQI', 67);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `sensors`
--
ALTER TABLE `sensors`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sensor_data`
--
ALTER TABLE `sensor_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `sensors`
--
ALTER TABLE `sensors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `sensor_data`
--
ALTER TABLE `sensor_data`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
