CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `code` varchar(20) NOT NULL,
  `nbHeuresbase` varchar(10) NOT NULL,
  `tauxBase` varchar(10) NOT NULL
);


INSERT INTO `grade` (`id`, `code`, `nbHeuresbase`, `tauxBase`) VALUES
(1, 'ABC', '24.00', '6.67'),
(2, 'AAA', '32.00', '99.00');


