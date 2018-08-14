-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 15/08/2018 às 00:42
-- Versão do servidor: 10.1.32-MariaDB
-- Versão do PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `ALOHA`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `combo`
--

CREATE TABLE `combo` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(8) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `combo`
--

INSERT INTO `combo` (`id`, `codigo_modelo`) VALUES
(1, 'CMB0001'),
(2, 'CMB0002'),
(3, 'CMB0003');

-- --------------------------------------------------------

--
-- Estrutura para tabela `combo_dias`
--

CREATE TABLE `combo_dias` (
  `id_combo` int(11) NOT NULL,
  `id_dia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `combo_dias`
--

INSERT INTO `combo_dias` (`id_combo`, `id_dia`) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `curso_semestre`
--

CREATE TABLE `curso_semestre` (
  `id` int(11) NOT NULL,
  `curso` varchar(45) COLLATE utf8_bin NOT NULL,
  `semestre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(9) COLLATE utf8_bin NOT NULL,
  `codigo_disciplina` varchar(12) COLLATE utf8_bin NOT NULL,
  `nome` varchar(45) COLLATE utf8_bin NOT NULL,
  `cr_praticos` int(11) NOT NULL,
  `cr_teoricos` int(11) NOT NULL,
  `vagas` int(11) NOT NULL,
  `tipo_sala` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `codigo_modelo`, `codigo_disciplina`, `nome`, `cr_praticos`, `cr_teoricos`, `vagas`, `tipo_sala`) VALUES
(5, 'DISC00001', 'RUS0014', 'Fundamentos de Programação', 1, 1, 13, 'Laboratório de Informática');

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina_curso_semestre`
--

CREATE TABLE `disciplina_curso_semestre` (
  `id` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `curso` varchar(45) COLLATE utf8_bin NOT NULL,
  `semestre` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `disciplina_curso_semestre`
--

INSERT INTO `disciplina_curso_semestre` (`id`, `id_disciplina`, `curso`, `semestre`) VALUES
(3, 5, 'Ciência da Computação', 1),
(4, 5, 'Engenharia de Software', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `docente`
--

CREATE TABLE `docente` (
  `id` int(11) NOT NULL,
  `codigo_modelo` int(11) NOT NULL,
  `nome` varchar(120) COLLATE utf8_bin NOT NULL,
  `cr_minimo` int(11) NOT NULL,
  `cr_maximo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `docente_combo`
--

CREATE TABLE `docente_combo` (
  `id_docente` int(11) NOT NULL,
  `id_combo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `preferencia`
--

CREATE TABLE `preferencia` (
  `id_docente` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `preferencia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `sala`
--

CREATE TABLE `sala` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(8) COLLATE utf8_bin NOT NULL,
  `nome` varchar(45) COLLATE utf8_bin NOT NULL,
  `tipo` varchar(45) COLLATE utf8_bin NOT NULL,
  `capacidade` int(11) NOT NULL,
  `bloco` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `sala`
--

INSERT INTO `sala` (`id`, `codigo_modelo`, `nome`, `tipo`, `capacidade`, `bloco`) VALUES
(1, 'SAL001', 'Sala 002', 'Sala', 60, 'Bloco A'),
(3, 'Sal0002', 'Sala Teste', 'Sala', 50, 'Bloco A');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigoModelo` (`codigo_modelo`);

--
-- Índices de tabela `curso_semestre`
--
ALTER TABLE `curso_semestre`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `disciplina_curso_semestre`
--
ALTER TABLE `disciplina_curso_semestre`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `docente_combo`
--
ALTER TABLE `docente_combo`
  ADD PRIMARY KEY (`id_docente`,`id_combo`);

--
-- Índices de tabela `preferencia`
--
ALTER TABLE `preferencia`
  ADD PRIMARY KEY (`id_docente`,`id_disciplina`);

--
-- Índices de tabela `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `curso_semestre`
--
ALTER TABLE `curso_semestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `disciplina_curso_semestre`
--
ALTER TABLE `disciplina_curso_semestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `docente`
--
ALTER TABLE `docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `sala`
--
ALTER TABLE `sala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
