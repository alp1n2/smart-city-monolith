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
-- База данных: `db_utility`
--

-- --------------------------------------------------------

--
-- Структура таблицы `issues`
--

CREATE TABLE `issues` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `assigned_to` bigint(20) DEFAULT NULL,
  `category` varchar(50) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` mediumtext DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `priority` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `issues`
--

INSERT INTO `issues` (`id`, `address`, `assigned_to`, `category`, `created_at`, `description`, `latitude`, `longitude`, `priority`, `status`, `title`, `updated_at`, `user_id`) VALUES
(1, 'ул. Ленина, 5', 1, 'WATER', '2026-09-25 13:48:13.000000', 'Вода на улице', NULL, NULL, 'HIGH', 'IN_PROGRESS', 'Прорыв трубы', '2026-09-25 13:50:28.000000', 1),
(2, 'ул. Ленина, 48', NULL, 'ELECTRICITY', '2026-09-26 04:38:35.000000', NULL, NULL, NULL, 'MEDIUM', 'NEW', 'Порвался провод', NULL, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `issue_comments`
--

CREATE TABLE `issue_comments` (
  `id` bigint(20) NOT NULL,
  `comment` mediumtext NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `issue_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `issues`
--
ALTER TABLE `issues`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `issue_comments`
--
ALTER TABLE `issue_comments`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `issues`
--
ALTER TABLE `issues`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `issue_comments`
--
ALTER TABLE `issue_comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
