CREATE DATABASE tiendaserbatic;

USE tiendaserbatic;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2024 a las 07:42:28
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendaserbatic`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `baja` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `baja`) VALUES
(1, 'Misterio', 1),
(2, 'Superheroes', 1),
(3, 'Infantil', 1),
(4, 'Terror', 1),
(5, 'Fantasia', 1),
(6, 'Manga', 1),
(7, 'Aventuras Epicas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `valor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`) VALUES
(1, 'factura', '169'),
(2, 'cif', 'A12345678'),
(3, 'direccion', 'C/ Julio Quesada Nº8'),
(4, 'provincia', 'Zamora'),
(5, 'telefono', '980652358');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id` int(11) NOT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `producto_id` int(11) DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `preciounidad` decimal(10,2) DEFAULT NULL,
  `impuesto` double DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id`, `pedido_id`, `producto_id`, `unidades`, `preciounidad`, `impuesto`, `total`) VALUES
(250, 167, 2, 2, 23.75, 21, 47.50),
(252, 168, 4, 1, 6.60, 21, 6.60),
(253, 169, 5, 1, 15.15, 21, 15.15),
(254, 169, 1, 2, 20.80, 21, 41.60),
(255, 169, 13, 1, 20.80, 21, 20.80),
(256, 169, 4, 2, 6.60, 21, 13.20),
(257, 169, 10, 1, 23.85, 21, 23.85),
(258, 169, 2, 2, 23.75, 21, 47.50),
(259, 169, 14, 1, 18.95, 21, 18.95),
(260, 170, 2, 2, 23.75, 21, 47.50),
(261, 170, 7, 2, 47.50, 21, 95.00),
(262, 171, 33, 1, 17.35, 21, 17.35),
(263, 171, 5, 2, 15.15, 21, 30.30),
(265, 172, 1, 2, 20.80, 21, 41.60),
(266, 172, 2, 1, 23.75, 21, 23.75),
(267, 173, 7, 1, 47.50, 21, 47.50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcionmenu`
--

CREATE TABLE `opcionmenu` (
  `id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `opcionmenu`
--

INSERT INTO `opcionmenu` (`id`, `rol_id`, `nombre`, `url`) VALUES
(1, 2, 'Alta Producto', '/ControladorProducto/RutaAltaProducto'),
(2, 2, 'Lista Productos', '/ControladorProducto/RutaListaProducto'),
(3, 2, 'Alta Cliente', '/ControladorUsuario/RutaAltaUsuario'),
(4, 2, 'Lista Clientes', '/ControladorUsuario/RutaListaUsuario'),
(5, 2, 'Alta Categoría', '/ControladorCategoria/RutaAltaCategoria'),
(6, 2, 'Lista Categorías', '/ControladorCategoria/RutaListaCategoria'),
(7, 2, 'Gestionar Pedidos', '/ControladorPedido/RutaListaPedido'),
(8, 1, 'Mis Pedidos', './RutaMisPedidos'),
(9, 1, 'Cambiar Contraseña', './RutaCambio'),
(11, 1, 'Info E-BOOK', './RutaInfoEmpresa'),
(13, 1, 'Contacto', './RutaFormulario'),
(14, 3, 'Lista Producto', '/ControladorProducto/RutaListaProducto'),
(15, 3, 'Lista Clientes', '/ControladorUsuario/RutaListaUsuario'),
(16, 3, 'Gestionar Pedidos', '/ControladorPedido/RutaListaPedido'),
(17, 3, 'Alta Empleado', '/ControladorUsuario/RutaAltaEmpleado'),
(18, 3, 'Lista Empleados', '/ControladorUsuario/RutaListaEmpleado'),
(19, 2, 'Cambiar Contraseña', '/ControladorUsuario/RutaCambioClave'),
(20, 3, 'Cambiar Contraseña', '/ControladorUsuario/RutaCambioClave'),
(21, 2, 'Cambiar Datos', '/ControladorUsuario/RutaCambiarInformacion'),
(22, 3, 'Importación/Exportación', '/ControladorProducto/RutaImportacionExportacion'),
(23, 2, 'Importación/Exportación', '/ControladorProducto/RutaImportacionExportacion'),
(24, 3, 'Configuración', '/ControladorConfiguracion/RutaListaConfiguracion'),
(25, 3, 'Gráfico', '/ControladorProducto/RutaGrafico'),
(26, 2, 'Alta Proveedor', '/ControladorProveedor/RutaAltaProveedor'),
(27, 2, 'Lista Proveedores', '/ControladorProveedor/RutaListaProveedor'),
(28, 3, 'Alta Cliente', '/ControladorUsuario/RutaAltaUsuario'),
(29, 3, 'Alta Producto', '/ControladorProducto/RutaAltaProducto'),
(30, 3, 'Alta Categoría', '/ControladorCategoria/RutaAltaCategoria'),
(31, 3, 'Lista Categorías', '/ControladorCategoria/RutaListaCategoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `metodopago` varchar(50) DEFAULT NULL,
  `numfactura` varchar(50) DEFAULT NULL,
  `estado` varchar(50) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `usuario_id`, `fecha`, `metodopago`, `numfactura`, `estado`, `total`) VALUES
(167, 52, '2024-05-09 00:09:31', 'tarjeta', 'FAC-000156', 'E', 57.21),
(168, 52, '2024-05-09 00:11:31', 'tarjeta', 'FAC-000158', 'E', 7.99),
(169, 27, '2024-05-09 01:06:57', 'tarjeta', 'FAC-000162', 'E', 219.07),
(170, 27, '2024-05-09 01:07:09', 'tarjeta', 'FAC-000163', 'E', 172.43),
(171, 27, '2024-05-09 01:07:20', 'tarjeta', 'FAC-000164', 'E', 57.66),
(172, 52, '2024-05-09 01:20:28', 'tarjeta', 'FAC-000166', 'E', 78.21),
(173, 52, '2024-05-09 01:22:04', 'tarjeta', 'FAC-000168', 'E', 57.48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `proveedor_id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `impuesto` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `categoria_id`, `proveedor_id`, `nombre`, `descripcion`, `precio`, `impuesto`, `stock`, `baja`, `imagen`) VALUES
(1, 1, 1, 'EL PROBLEMA FINAL', 'Un crimen imposible. Un detective insospechado y astuto.', 20.80, 21, 29, 1, 'https://imagessl0.casadellibro.com/a/l/s7/60/9788420476360.webp'),
(2, 4, 2, 'LA ERA DEL FUTURO DEGRADADO', 'Y OTROS RELATOS DE HORROR Y PESADILLA', 23.75, 21, 16, 1, 'https://imagessl6.casadellibro.com/a/l/s7/26/9788477029526.webp'),
(3, 2, 2, 'MÁS ALLÁ DEL CABALLERO BLANCO', 'La esperada secuela de Batman: Caballero Blanco y Batman.', 60.98, 21, 6, 1, 'https://imagessl2.casadellibro.com/a/l/s7/62/9788410134362.webp'),
(4, 3, 2, 'NORA LA INVENTORA', 'A Nora la Inventora le ENCANTA la ciencia y NO cree en la magia.', 6.60, 21, 9, 1, 'https://imagessl1.casadellibro.com/a/l/s7/21/9781805076421.webp'),
(5, 3, 2, 'JULIA Y LOS MORTIMORT', '¡CUIDADO! Con este libro podrías morirte... ¡de risa!', 15.15, 21, 15, 1, 'https://imagessl5.casadellibro.com/a/l/s7/15/9788419650115.webp'),
(6, 6, 1, 'ONE PIECE Nº 2', 'CONTRA LOS PIRATAS DE BUGGY', 8.07, 21, 32, 1, 'https://imagessl2.casadellibro.com/a/l/s7/32/9788468471532.webp'),
(7, 5, 1, 'EL SILMARILLION', 'Por primera vez, una edición muy especia.', 47.50, 21, 25, 1, 'https://imagessl4.casadellibro.com/a/l/s7/94/9788445016794.webp'),
(8, 5, 2, 'CHOQUE DE REYES ', 'LOS LIBROS QUE INSPIRARON LA SERIE JUEGO DE TRONOS DE HBO', 14.20, 21, 20, 1, 'https://imagessl1.casadellibro.com/a/l/s7/31/9788401032431.webp'),
(9, 4, 1, 'EL HORROR DE DUNWICH', 'La familia de Dunwich, conserva parte del atroz secreto del Necronomicón.', 19.00, 21, 19, 1, 'https://imagessl6.casadellibro.com/a/l/s7/36/9788494033636.webp'),
(10, 2, 2, 'UNA MUERTE EN LA FAMILIA', 'Jason Todd es el nuevo Robin, pero hay algo que no va bien.', 23.85, 21, 31, 1, 'https://imagessl4.casadellibro.com/a/l/s7/24/9788410134324.webp'),
(13, 5, 2, 'LA EDAD DEL SOL', 'Lo mejor de la fantasia epica con un ritmo trepidante y asombroso', 20.80, 21, 35, 1, 'https://imagessl2.casadellibro.com/a/l/s7/62/9788466678162.webp'),
(14, 7, 2, 'LOS JUEGOS DEL HAMBRE 1', 'Ganar significa fama y riqueza. Perder significa una muerte segura.', 18.95, 21, 38, 1, 'https://imagessl2.casadellibro.com/a/l/s7/22/9788427202122.webp'),
(31, 6, 2, 'HAJIME NO IPPO', 'El enfrentamiento entre el este y el oeste ha llegado: Ippo Makunouchi vs Takeshi Send!', 5.50, 21, 37, 1, 'https://imagessl6.casadellibro.com/a/l/s7/36/9788411612036.webp'),
(32, 4, 1, 'SI TE GUSTA LA OSCURIDAD', 'uno de los mejores narradores de nuestro tiempo', 21.80, 21, 39, 1, 'https://imagessl9.casadellibro.com/a/l/s7/69/9788401032769.webp'),
(33, 1, 1, 'CASTIGO', 'Imagina lo peor que le puede pasar a un ser humano.', 17.35, 21, 35, 1, 'https://imagessl6.casadellibro.com/a/l/s7/26/9788467071726.webp');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `baja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nombre`, `direccion`, `ciudad`, `telefono`, `email`, `baja`) VALUES
(1, 'Asturlibros', 'C/ Álvaro Quijarro Nº9', 'Santander', '976564344', 'asturlibros@gmail.com', 1),
(2, 'Popular', 'C/ Sagrados Corazones Nº1', 'Madrid', '987675566', 'popularlibros@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `nombre`) VALUES
(1, 'cliente'),
(2, 'empleado'),
(3, 'administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `baja` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `rol_id`, `email`, `clave`, `nombre`, `apellidos`, `baja`) VALUES
(27, 1, 'erney@gmail.com', 'dHR0', 'ernesto', 'sanchez', 1),
(35, 2, 'mario@gmail.com', 'ZDNkMw==', 'Mario', 'Sanchez', 1),
(36, 1, 'pepe@gmail.com', 'ZXJy', 'pepe', 'sanchez', 1),
(38, 2, 'juan@gmail.com', 'b29v', 'Juan', 'Perez', 1),
(45, 1, 'sergio160502@gmail.com', 'bm5u', 'Sergio', 'Rodríguez', 1),
(49, 3, 'admin@gmail.com', 'bm5u', 'admin', 'adminapellido', 1),
(50, 1, 'kike@gmail.com', 'aGho', 'kike', 'wdwd', 1),
(51, 2, 'alberto@gmail.com', 'cHBw', 'Alberto', 'García', 1),
(52, 1, 'sergioSerbatic2002@hotmail.com', 'eXl5eXk=', 'Sergio', 'Rodriguez', 1),
(61, 3, 'antonio@gmail.com', 'YWFhYWE=', 'antonio', 'sanchez', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion`
--

CREATE TABLE `valoracion` (
  `id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `valoracion` int(11) NOT NULL,
  `comentario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `valoracion`
--

INSERT INTO `valoracion` (`id`, `producto_id`, `usuario_id`, `valoracion`, `comentario`) VALUES
(1, 1, 27, 1, 'Esta bien'),
(3, 1, 36, 1, 'No me gusta leer'),
(4, 7, 27, 4, 'librazo'),
(5, 6, 27, 4, 'Me gusto'),
(7, 9, 27, 2, 'Muy largo'),
(8, 9, 36, 5, 'Me da miedo'),
(9, 8, 27, 5, 'Me encanto!'),
(10, 2, 27, 1, 'meh'),
(11, 4, 27, 2, 'para ninos'),
(16, 3, 50, 5, 'Me flipo'),
(23, 4, 52, 4, 'Me gusto bastante'),
(24, 1, 52, 4, 'Muy misterioso');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pedido_id` (`pedido_id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `opcionmenu`
--
ALTER TABLE `opcionmenu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=268;

--
-- AUTO_INCREMENT de la tabla `opcionmenu`
--
ALTER TABLE `opcionmenu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=174;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
