-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 05/09/2018 às 00:17
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
(26, 'CMB0026'),
(28, 'CMB0028'),
(29, 'CMB0029'),
(30, 'CMB0030'),
(31, 'CMB0031'),
(32, 'CMB0032'),
(33, 'CMB0033');

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
(26, 1),
(26, 3),
(28, 1),
(28, 3),
(29, 1),
(29, 3),
(30, 0),
(30, 3),
(31, 0),
(31, 3),
(32, 0),
(32, 3),
(33, 0),
(33, 3),
(34, 0),
(34, 3);

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
(5, 'DISC00001', 'RUS0014', 'Fundamentos de Programação', 1, 1, 13, 'Laboratório de Informática'),
(6, 'DISC00002', 'RUS0014', 'Estrutura de Dados', 1, 1, 10, 'Sala'),
(7, 'DISC00003', 'RUS0014', 'Matemática Computacional', 4, 1, 22, 'Laboratório de Química'),
(8, 'DISC00004', 'RUS0013', 'PPCT', 0, 2, 39, 'Sala');

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
(4, 5, 'Engenharia de Software', 1),
(5, 6, 'Ciência da Computação', 3),
(6, 7, 'Ciência da Computação', 4),
(7, 8, 'Ciência da Computação', 7),
(8, 8, 'Engenharia de Software', 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `docente`
--

CREATE TABLE `docente` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(11) COLLATE utf8_bin NOT NULL,
  `nome` varchar(120) COLLATE utf8_bin NOT NULL,
  `cr_minimo` int(11) NOT NULL,
  `cr_maximo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Fazendo dump de dados para tabela `docente`
--

INSERT INTO `docente` (`id`, `codigo_modelo`, `nome`, `cr_minimo`, `cr_maximo`) VALUES
(44, 'DOC0044', 'zxczzczxc', 10, 12);

-- --------------------------------------------------------

--
-- Estrutura para tabela `docente_dias_semana`
--

CREATE TABLE `docente_dias_semana` (
  `id_docente` int(11) NOT NULL,
  `dia_semana` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `horario`
--

CREATE TABLE `horario` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `descricao` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `planejamento`
--

CREATE TABLE `planejamento` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) COLLATE utf8_bin NOT NULL,
  `status` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `planejamento_disciplina`
--

CREATE TABLE `planejamento_disciplina` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `planejamento_docente`
--

CREATE TABLE `planejamento_docente` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_docente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `planejamento_sala`
--

CREATE TABLE `planejamento_sala` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura para tabela `planejamento_variaveis_fixas`
--

CREATE TABLE `planejamento_variaveis_fixas` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_var_fixa` int(11) NOT NULL
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

--
-- Fazendo dump de dados para tabela `preferencia`
--

INSERT INTO `preferencia` (`id_docente`, `id_disciplina`, `preferencia`) VALUES
(18, 5, 0),
(18, 6, 0),
(18, 7, 0),
(18, 8, 0),
(25, 5, 0),
(25, 6, 0),
(25, 7, 0),
(25, 8, 0),
(26, 5, 100),
(26, 6, 100),
(26, 7, 0),
(26, 8, 0),
(27, 5, 100),
(27, 6, 100),
(27, 7, 0),
(27, 8, 50),
(30, 5, 99),
(30, 6, 0),
(30, 7, 0),
(30, 8, 48),
(31, 5, 0),
(31, 6, 0),
(31, 7, 0),
(31, 8, 0),
(32, 5, 0),
(32, 6, 0),
(32, 7, 0),
(32, 8, 0),
(35, 5, 0),
(35, 6, 0),
(35, 7, 0),
(35, 8, 0),
(37, 5, 0),
(37, 6, 0),
(37, 7, 0),
(37, 8, 0),
(38, 5, 100),
(38, 6, 0),
(38, 7, 0),
(38, 8, 0);

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

-- --------------------------------------------------------

--
-- Estrutura para tabela `variaveis_fixas`
--

CREATE TABLE `variaveis_fixas` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(9) COLLATE utf8_bin NOT NULL,
  `id_docente` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `id_combo` int(11) NOT NULL,
  `id_horario` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Índices de tabela `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `planejamento`
--
ALTER TABLE `planejamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `planejamento_disciplina`
--
ALTER TABLE `planejamento_disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `planejamento_docente`
--
ALTER TABLE `planejamento_docente`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `planejamento_sala`
--
ALTER TABLE `planejamento_sala`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `planejamento_variaveis_fixas`
--
ALTER TABLE `planejamento_variaveis_fixas`
  ADD PRIMARY KEY (`id`);

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
-- Índices de tabela `variaveis_fixas`
--
ALTER TABLE `variaveis_fixas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de tabela `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `disciplina_curso_semestre`
--
ALTER TABLE `disciplina_curso_semestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `docente`
--
ALTER TABLE `docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de tabela `horario`
--
ALTER TABLE `horario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `planejamento`
--
ALTER TABLE `planejamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `planejamento_disciplina`
--
ALTER TABLE `planejamento_disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `planejamento_docente`
--
ALTER TABLE `planejamento_docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `planejamento_sala`
--
ALTER TABLE `planejamento_sala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `planejamento_variaveis_fixas`
--
ALTER TABLE `planejamento_variaveis_fixas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `sala`
--
ALTER TABLE `sala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `variaveis_fixas`
--
ALTER TABLE `variaveis_fixas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
