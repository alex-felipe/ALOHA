-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 19-Set-2018 às 20:32
-- Versão do servidor: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ALOHA`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `combo`
--

CREATE TABLE `combo` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(8) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `combo`
--

INSERT INTO `combo` (`id`, `codigo_modelo`) VALUES
(35, 'CMB0035'),
(36, 'CMB0036'),
(37, 'CMB0037'),
(38, 'CMB0038'),
(39, 'CMB0039'),
(40, 'CMB0040'),
(41, 'CMB0041'),
(42, 'CMB0042'),
(43, 'CMB0043'),
(44, 'CMB0044'),
(45, 'CMB0045'),
(46, 'CMB0046'),
(47, 'CMB0047'),
(48, 'CMB0048'),
(49, 'CMB0049'),
(50, 'CMB0050'),
(51, 'CMB0051'),
(52, 'CMB0052');

-- --------------------------------------------------------

--
-- Estrutura da tabela `combo_dias`
--

CREATE TABLE `combo_dias` (
  `id_combo` int(11) NOT NULL,
  `id_dia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `combo_dias`
--

INSERT INTO `combo_dias` (`id_combo`, `id_dia`) VALUES
(35, 2),
(35, 6),
(36, 3),
(36, 7),
(37, 4),
(37, 8),
(38, 5),
(38, 9),
(39, 6),
(39, 10),
(40, 7),
(40, 11),
(41, 2),
(41, 6),
(41, 10),
(42, 3),
(42, 7),
(42, 11),
(43, 2),
(44, 3),
(45, 4),
(46, 5),
(47, 6),
(48, 8),
(49, 9),
(50, 7),
(51, 10),
(52, 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

CREATE TABLE `curso` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `nome` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `curso`
--

INSERT INTO `curso` (`id`, `codigo_modelo`, `nome`) VALUES
(1, 'CRS001', 'Ciência da Computação');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(9) COLLATE utf8_bin NOT NULL,
  `codigo_disciplina` varchar(12) COLLATE utf8_bin NOT NULL,
  `nome` varchar(120) COLLATE utf8_bin NOT NULL,
  `cr_praticos` int(11) NOT NULL,
  `cr_teoricos` int(11) NOT NULL,
  `vagas` int(11) NOT NULL,
  `tipo_sala` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `codigo_modelo`, `codigo_disciplina`, `nome`, `cr_praticos`, `cr_teoricos`, `vagas`, `tipo_sala`) VALUES
(10, 'DISC00002', 'RUS0004', 'MATEMÁTICA BÁSICA', 0, 4, 55, 'Sala'),
(11, 'DISC00003', 'RUS0005', 'ARQUITETURA DE COMPUTADORES', 0, 4, 55, 'Sala'),
(12, 'DISC00004', 'RUS0010', 'INTRODUÇÃO À CIÊNCIA DA COMPUTAÇÃO', 0, 4, 55, 'Sala'),
(13, 'DISC00005', 'RUS0011', 'ÉTICA, DIREITO E LEGISLAÇÃO', 0, 4, 55, 'Sala'),
(14, 'DISC00006', 'RUS0012', 'PRÉ-CÁLCULO', 0, 2, 55, 'Sala'),
(15, 'DISC00007', 'RUS0006', 'PROGRAMAÇÃO ORIENTADA A OBJETOS', 2, 2, 55, 'Sala'),
(16, 'DISC00008', 'RUS0007', 'MATEMÁTICA DISCRETA', 0, 4, 55, 'Sala'),
(17, 'DISC00009', 'RUS0013', 'SISTEMAS OPERACIONAIS', 1, 3, 55, 'Sala'),
(18, 'DISC00010', 'RUS0014', 'ESTRUTURAS DE DADOS', 2, 2, 55, 'Sala'),
(19, 'DISC00011', 'RUS0015', 'CÁLCULO DIFERENCIAL E INTEGRAL I ', 0, 4, 55, 'Sala'),
(20, 'DISC00012', 'RUS0008', 'PROBABILIDADE E ESTATÍSTICA', 0, 4, 55, 'Sala'),
(21, 'DISC00013', 'RUS0062', 'LÓGICA PARA COMPUTAÇÃO ', 1, 3, 55, 'Sala'),
(22, 'DISC00014', 'RUS0070', 'PROGRAMAÇÃO FUNCIONAL', 2, 2, 55, 'Sala'),
(23, 'DISC00015', 'RUS0071', 'ESTRUTURAS DE DADOS AVANÇADAS', 2, 2, 55, 'Sala'),
(24, 'DISC00016', 'RUS0072', 'LINGUAGENS FORMAIS E AUTÔMATOS', 0, 4, 55, 'Sala'),
(25, 'DISC00017', 'RUS0058', ' 	ANÁLISE E PROJETO DE SISTEMAS', 2, 2, 55, 'Sala'),
(26, 'DISC00018', 'RUS0059', 'LINGUAGENS DE PROGRAMAÇÃO', 1, 3, 55, 'Sala'),
(27, 'DISC00019', 'RUS0061', 'FUNDAMENTOS DE BANCO DE DADOS', 2, 2, 55, 'Sala'),
(28, 'DISC00020', 'RUS0067', 'PROJETO E ANÁLISE DE ALGORITMOS', 0, 4, 55, 'Sala'),
(29, 'DISC00021', 'RUS0077', 'ÁLGEBRA LINEAR', 1, 3, 55, 'Sala'),
(30, 'DISC00022', 'RUS0079', 'COMPILADORES', 0, 4, 55, 'Sala'),
(31, 'DISC00023', 'RUS0080', 'COMPUTAÇÃO GRÁFICA', 1, 3, 55, 'Sala'),
(32, 'DISC00024', 'RUS0081', 'MATEMÁTICA COMPUTACIONAL', 1, 3, 55, 'Sala'),
(33, 'DISC00025', 'RUS0082', 'REDES DE COMPUTADORES', 0, 4, 55, 'Sala'),
(34, 'DISC00026', 'RUS0083', ' DESENVOLVIMENTO DE SOFTWARE PARA WEB', 2, 2, 55, 'Sala'),
(35, 'DISC00027', 'RUS0084', 'ENGENHARIA DE SOFTWARE', 1, 3, 0, 'Sala'),
(36, 'DISC00028', 'RUS0085', 'SISTEMAS DISTRIBUÍDOS', 1, 3, 55, 'Sala'),
(37, 'DISC00029', 'RUS0086', 'INTELIGÊNCIA ARTIFICIAL', 1, 3, 55, 'Sala'),
(38, 'DISC00030', 'RUS0087', 'TEORIA DA COMPUTAÇÃO', 0, 4, 55, 'Sala'),
(39, 'DISC00031', 'RUS0256', 'INTERAÇÃO HUMANO-COMPUTADOR', 2, 2, 55, 'Sala'),
(40, 'DISC00032', 'RUS0060', 'EMPREENDEDORISMO', 0, 4, 55, 'Sala'),
(41, 'DISC00033', 'RUS0088', 'PROJETO DE PESQUISA CIENTÍFICA E TECNOLÓGICA', 1, 1, 55, 'Sala'),
(42, 'DISC00034', 'RUS0297', 'FUNDAMENTOS DE PROGRAMAÇÃO', 2, 2, 55, 'Sala'),
(43, 'DISC00035', 'RUS0298', 'INTRODUÇÃO À ENGENHARIA DE SOFTWARE', 0, 4, 55, 'Sala'),
(44, 'DISC00036', 'RUS0009', 'INTRODUÇÃO A PROCESSOS E REQUISITOS DE SOFTWARE', 0, 4, 55, 'Sala'),
(45, 'DISC00037', 'RUS0299', 'LABORATÓRIO DE PROGRAMAÇÃO', 4, 0, 55, 'Sala'),
(46, 'DISC00038', 'RUS0069', 'REQUISITOS DE SOFTWARE', 0, 4, 53, 'Sala'),
(47, 'DISC00039', 'RUS0300', 'ALGORITMOS EM GRAFOS', 0, 4, 55, 'Sala'),
(48, 'DISC00040', 'RUS0068', 'PROCESSOS DE SOFTWARE', 0, 4, 55, 'Sala'),
(49, 'DISC00041', 'RUS0064', 'PROJETO DETALHADO DE SOFTWARE', 0, 4, 55, 'Sala'),
(50, 'DISC00042', 'RUS0096', 'QUALIDADE DE SOFTWARE ', 0, 4, 55, 'Sala'),
(51, 'DISC00043', 'RUS0098', 'VERIFICAÇÃO E VALIDAÇÃO', 0, 4, 55, 'Sala'),
(52, 'DISC00044', 'RUS0099', 'MANUTENÇÃO DE SOFTWARE', 0, 4, 55, 'Sala'),
(53, 'DISC00045', 'RUS0242', 'ARQUITETURA DE SOFTWARE', 0, 4, 55, 'Sala'),
(54, 'DISC00045', 'RUS0001', 'FUNDAMENTOS DE PROGRAMAÇÃO', 3, 3, 55, 'Sala'),
(55, 'DISC00046', 'RUS0001', 'FUNDAMENTOS DE PROGRAMAÇÃO', 3, 3, 55, 'Sala'),
(56, 'NULL', '', '', 0, 0, 0, 'Sala');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina_curso_semestre`
--

CREATE TABLE `disciplina_curso_semestre` (
  `id` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `curso` varchar(45) COLLATE utf8_bin NOT NULL,
  `semestre` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `disciplina_curso_semestre`
--

INSERT INTO `disciplina_curso_semestre` (`id`, `id_disciplina`, `curso`, `semestre`) VALUES
(10, 10, 'Ciência da Computação', 1),
(11, 10, 'Engenharia de Software', 1),
(12, 11, 'Ciência da Computação', 1),
(13, 11, 'Engenharia de Software', 2),
(16, 12, 'Ciência da Computação', 1),
(17, 13, 'Ciência da Computação', 1),
(18, 13, 'Engenharia de Software', 1),
(19, 14, 'Ciência da Computação', 1),
(20, 15, 'Ciência da Computação', 2),
(21, 15, 'Engenharia de Software', 3),
(27, 19, 'Ciência da Computação', 2),
(31, 22, 'Ciência da Computação', 3),
(32, 23, 'Ciência da Computação', 3),
(33, 24, 'Ciência da Computação', 3),
(37, 26, 'Ciência da Computação', 4),
(38, 26, 'Engenharia de Software', 3),
(43, 29, 'Ciência da Computação', 4),
(44, 30, 'Ciência da Computação', 5),
(45, 31, 'Ciência da Computação', 5),
(46, 32, 'Ciência da Computação', 5),
(48, 34, 'Ciência da Computação', 5),
(49, 35, 'Ciência da Computação', 6),
(50, 36, 'Ciência da Computação', 6),
(51, 37, 'Ciência da Computação', 6),
(52, 38, 'Ciência da Computação', 6),
(57, 42, 'Engenharia de Software', 1),
(59, 16, 'Ciência da Computação', 2),
(60, 16, 'Engenharia de Software', 2),
(61, 44, 'Engenharia de Software', 2),
(62, 18, 'Ciência da Computação', 2),
(63, 18, 'Engenharia de Software', 2),
(64, 45, 'Engenharia de Software', 2),
(65, 20, 'Ciência da Computação', 3),
(66, 20, 'Engenharia de Software', 3),
(67, 46, 'Engenharia de Software', 3),
(68, 47, 'Engenharia de Software', 3),
(69, 25, 'Ciência da Computação', 4),
(70, 25, 'Engenharia de Software', 4),
(71, 27, 'Ciência da Computação', 4),
(72, 27, 'Engenharia de Software', 4),
(73, 21, 'Ciência da Computação', 3),
(74, 21, 'Engenharia de Software', 4),
(75, 28, 'Ciência da Computação', 4),
(76, 28, 'Engenharia de Software', 4),
(77, 48, 'Engenharia de Software', 4),
(78, 17, 'Ciência da Computação', 2),
(79, 17, 'Engenharia de Software', 5),
(80, 49, 'Engenharia de Software', 5),
(81, 33, 'Ciência da Computação', 5),
(82, 33, 'Engenharia de Software', 5),
(83, 39, 'Ciência da Computação', 6),
(84, 39, 'Engenharia de Software', 5),
(85, 40, 'Ciência da Computação', 7),
(86, 40, 'Engenharia de Software', 6),
(87, 50, 'Engenharia de Software', 6),
(88, 51, 'Engenharia de Software', 6),
(89, 52, 'Engenharia de Software', 6),
(90, 53, 'Engenharia de Software', 6),
(91, 41, 'Ciência da Computação', 7),
(92, 41, 'Engenharia de Software', 7),
(93, 55, 'Ciência da Computação', 1),
(94, 43, 'Engenharia de Software', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `docente`
--

CREATE TABLE `docente` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(11) COLLATE utf8_bin NOT NULL,
  `nome` varchar(120) COLLATE utf8_bin NOT NULL,
  `cr_minimo` int(11) NOT NULL,
  `cr_maximo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `docente`
--

INSERT INTO `docente` (`id`, `codigo_modelo`, `nome`, `cr_minimo`, `cr_maximo`) VALUES
(44, 'DOC0044', 'zxczzczxc', 10, 12),
(45, 'DOC0045', 'vbccv', 7, 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `docente_dias_semana`
--

CREATE TABLE `docente_dias_semana` (
  `id_docente` int(11) NOT NULL,
  `dia_semana` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `docente_dias_semana`
--

INSERT INTO `docente_dias_semana` (`id_docente`, `dia_semana`) VALUES
(45, 3),
(45, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `horario`
--

CREATE TABLE `horario` (
  `id` int(11) NOT NULL,
  `codigo_modelo` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `descricao` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `horario`
--

INSERT INTO `horario` (`id`, `codigo_modelo`, `descricao`) VALUES
(1, 'HOR001', '08:00 - 10:00'),
(2, 'HOR002', '10:00 - 12:00'),
(3, 'HOR003', '13:30 - 15:30'),
(4, 'HOR004', '15:30 - 17:30');

-- --------------------------------------------------------

--
-- Estrutura da tabela `planejamento`
--

CREATE TABLE `planejamento` (
  `id` int(11) NOT NULL,
  `nome` varchar(150) COLLATE utf8_bin NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `planejamento`
--

INSERT INTO `planejamento` (`id`, `nome`, `status`) VALUES
(2, 'Teste', 1),
(3, 'Teste2', 0),
(4, 'aaaaaa', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `planejamento_disciplina`
--

CREATE TABLE `planejamento_disciplina` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `planejamento_disciplina`
--

INSERT INTO `planejamento_disciplina` (`id`, `id_planejamento`, `id_disciplina`) VALUES
(1, 1, 10),
(2, 4, 10),
(3, 4, 11),
(4, 4, 12),
(5, 4, 13),
(6, 4, 14),
(7, 4, 15),
(8, 4, 16),
(9, 4, 17),
(10, 4, 18),
(11, 4, 19),
(12, 4, 20),
(13, 4, 21),
(14, 4, 22),
(15, 4, 23),
(16, 4, 24),
(17, 4, 25),
(18, 4, 26),
(19, 4, 27),
(20, 4, 28),
(21, 4, 29),
(22, 4, 30),
(23, 4, 31),
(24, 4, 32),
(25, 4, 33),
(26, 4, 34),
(27, 4, 35),
(28, 4, 36),
(29, 4, 37),
(30, 4, 38),
(31, 4, 39),
(32, 4, 40),
(33, 4, 41),
(34, 4, 42),
(35, 4, 43),
(36, 4, 44),
(37, 4, 45),
(38, 4, 46),
(39, 4, 47),
(40, 4, 48),
(41, 4, 49),
(42, 4, 50),
(43, 4, 51),
(44, 4, 52),
(45, 4, 53),
(46, 4, 54),
(47, 4, 55);

-- --------------------------------------------------------

--
-- Estrutura da tabela `planejamento_docente`
--

CREATE TABLE `planejamento_docente` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_docente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `planejamento_docente`
--

INSERT INTO `planejamento_docente` (`id`, `id_planejamento`, `id_docente`) VALUES
(1, 4, 44),
(2, 4, 45);

-- --------------------------------------------------------

--
-- Estrutura da tabela `planejamento_sala`
--

CREATE TABLE `planejamento_sala` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `planejamento_sala`
--

INSERT INTO `planejamento_sala` (`id`, `id_planejamento`, `id_sala`) VALUES
(1, 4, 6),
(2, 4, 7),
(3, 4, 8),
(4, 4, 9),
(5, 4, 11),
(6, 4, 12),
(7, 4, 13),
(8, 4, 14),
(9, 4, 19),
(10, 4, 20),
(11, 4, 21);

-- --------------------------------------------------------

--
-- Estrutura da tabela `planejamento_variaveis_fixas`
--

CREATE TABLE `planejamento_variaveis_fixas` (
  `id` int(11) NOT NULL,
  `id_planejamento` int(11) NOT NULL,
  `id_var_fixa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `preferencia`
--

CREATE TABLE `preferencia` (
  `id_docente` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `preferencia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Extraindo dados da tabela `preferencia`
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
(38, 8, 0),
(45, 5, 0),
(45, 6, 35),
(45, 7, 0),
(45, 8, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sala`
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
-- Extraindo dados da tabela `sala`
--

INSERT INTO `sala` (`id`, `codigo_modelo`, `nome`, `tipo`, `capacidade`, `bloco`) VALUES
(6, 'SAL0002', 'SALA 2', 'Sala', 55, 'Bloco A'),
(7, 'SAL0003', 'SALA 3', 'Sala', 55, 'Bloco A'),
(8, 'SAL0004', 'SALA 4', 'Sala', 55, 'Bloco A'),
(9, 'SAL0005', 'SALA 5', 'Sala', 55, 'Bloco A'),
(11, 'SAL0007', 'SALA 9', 'Sala', 55, 'Bloco B'),
(12, 'SAL0007', 'SALA 10', 'Sala', 55, 'Bloco B'),
(13, 'SAL0008', 'SALA 11', 'Sala', 55, 'Bloco B'),
(14, 'SAL0009', 'SALA 12', 'Sala', 55, 'Bloco B'),
(19, 'SAL0009', 'SALA 1', 'SALA', 55, 'Bloco A'),
(20, 'SAL0010', 'SALA 6', 'SALA', 55, 'Bloco A'),
(21, 'SAL0011', 'SALA 13', 'SALA', 55, 'Bloco B');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) COLLATE utf8_bin NOT NULL,
  `login` varchar(45) COLLATE utf8_bin NOT NULL,
  `senha` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `variaveis_fixas`
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
-- Indexes for dumped tables
--

--
-- Indexes for table `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigoModelo` (`codigo_modelo`);

--
-- Indexes for table `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disciplina_curso_semestre`
--
ALTER TABLE `disciplina_curso_semestre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planejamento`
--
ALTER TABLE `planejamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planejamento_disciplina`
--
ALTER TABLE `planejamento_disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planejamento_docente`
--
ALTER TABLE `planejamento_docente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planejamento_sala`
--
ALTER TABLE `planejamento_sala`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `planejamento_variaveis_fixas`
--
ALTER TABLE `planejamento_variaveis_fixas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `preferencia`
--
ALTER TABLE `preferencia`
  ADD PRIMARY KEY (`id_docente`,`id_disciplina`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `variaveis_fixas`
--
ALTER TABLE `variaveis_fixas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `combo`
--
ALTER TABLE `combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `curso`
--
ALTER TABLE `curso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `disciplina_curso_semestre`
--
ALTER TABLE `disciplina_curso_semestre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT for table `docente`
--
ALTER TABLE `docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `horario`
--
ALTER TABLE `horario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `planejamento`
--
ALTER TABLE `planejamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `planejamento_disciplina`
--
ALTER TABLE `planejamento_disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `planejamento_docente`
--
ALTER TABLE `planejamento_docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `planejamento_sala`
--
ALTER TABLE `planejamento_sala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `planejamento_variaveis_fixas`
--
ALTER TABLE `planejamento_variaveis_fixas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sala`
--
ALTER TABLE `sala`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `variaveis_fixas`
--
ALTER TABLE `variaveis_fixas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
