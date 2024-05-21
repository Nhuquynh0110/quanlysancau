-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 07, 2024 lúc 09:55 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sancau`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaDS` int(11) DEFAULT NULL,
  `MaSan` int(11) DEFAULT NULL,
  `MaDV` int(11) DEFAULT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `TenKH` varchar(50) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `TenSan` varchar(100) DEFAULT NULL,
  `SoGioThue` int(11) DEFAULT NULL,
  `GiaSan` decimal(10,2) DEFAULT NULL,
  `TenDV` varchar(100) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `Gia` decimal(10,2) DEFAULT NULL,
  `TongTienSan` decimal(10,2) DEFAULT NULL,
  `TongTienDV` decimal(10,2) DEFAULT NULL,
  `TongTien` decimal(10,2) DEFAULT NULL,
  `NgayLap` date DEFAULT NULL,
  `TrangThai` varchar(20) DEFAULT 'chưa thanh toán'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaDS`, `MaSan`, `MaDV`, `MaKH`, `TenKH`, `SDT`, `TenSan`, `SoGioThue`, `GiaSan`, `TenDV`, `SoLuong`, `Gia`, `TongTienSan`, `TongTienDV`, `TongTien`, `NgayLap`, `TrangThai`) VALUES
(1, 1, 1, 1, 'John Doe', '123456789', 'Sân 1', 2, 150.00, 'Massage', 2, 50.00, 200.50, 50.75, 251.25, '2024-04-06', 'Đã thanh toán'),
(2, 2, 2, 2, 'Jane Smith', '987654321', 'Sân 2', 2, 100.00, 'Personal Training', 1, 30.00, 150.25, 30.50, 180.75, '2024-04-06', 'Đã thanh toán'),
(3, 3, 6, 3, 'Alice Johnson', '555555555', 'Sân 3', 2, 120.00, 'vợt cầu lông', 1, 500.00, 180.75, 40.00, 220.75, '2024-04-07', 'Đã thanh toán'),
(3, 3, 7, 3, 'Alice Johnson', '555555555', 'Sân 3', 2, 120.00, 'nuoc khoang', 2, 20.00, 180.75, 40.00, 220.75, '2024-04-07', 'Đã thanh toán'),
(17, 1, 1, 1, 'John Doe', '123456789', 'Sân 1', 2, 150.00, 'cầu 5 sao', 2, 15.00, 300.00, 30.00, 330.00, '2024-04-07', 'Đã thanh toán'),
(16, 1, 1, 1, 'John Doe', '123456789', 'Sân 1', 4, 150.00, 'cầu 5 sao', 1, 15.00, 600.00, 54.00, 654.00, '2024-04-07', 'Đã thanh toán'),
(16, 1, 2, 1, 'John Doe', '123456789', 'Sân 1', 4, 150.00, 'bánh đậu xanh', 1, 25.00, 600.00, 54.00, 654.00, '2024-04-07', 'Đã thanh toán'),
(16, 1, 4, 1, 'John Doe', '123456789', 'Sân 1', 4, 150.00, 'bánh tráng', 1, 14.00, 600.00, 54.00, 654.00, '2024-04-07', 'Đã thanh toán'),
(30, 4, 1, 3, 'Alice Johnson', '555555555', 'Sân 4', 2, 500.00, 'cầu 5 sao', 1, 15.00, 1000.00, 25.00, 1025.00, '2024-04-07', 'chưa thanh toán'),
(30, 4, 5, 3, 'Alice Johnson', '555555555', 'Sân 4', 2, 500.00, '7 up', 1, 10.00, 1000.00, 25.00, 1025.00, '2024-04-07', 'chưa thanh toán'),
(28, 1, 4, 1, 'John Doe', '123456789', 'Sân 1', 3, 150.00, 'bánh tráng', 1, 14.00, 450.00, 14.00, 464.00, '2024-04-07', 'Đã thanh toán'),
(19, 1, 1, 1, 'John Doe', '123456789', 'Sân 1', 2, 150.00, 'Massage', 1, 50.00, 300.00, NULL, NULL, '2024-04-07', 'chưa thanh toán'),
(26, 3, 4, 2, 'Jane Smith', '987654321', 'Sân 3', 7, 120.00, 'bánh tráng', 1, 14.00, 840.00, 14.00, 854.00, '2024-04-07', 'chưa thanh toán'),
(25, 2, 1, 2, 'Jane Smith', '987654321', 'Sân 2', 9, 100.00, 'cầu 5 sao', 1, 15.00, 900.00, 15.00, 915.00, '2024-04-08', 'Đã thanh toán'),
(23, 1, 2, 1, 'John Doe', '123456789', 'Sân 1', 2, 150.00, 'bánh đậu xanh', 1, 25.00, 300.00, 25.00, 325.00, '2024-04-08', 'Đã thanh toán'),
(29, 4, 4, 1, 'John Doe', '123456789', 'Sân 4', 2, 500.00, 'bánh tráng', 1, 14.00, 1000.00, 34.00, 1034.00, '2024-04-08', 'chưa thanh toán'),
(29, 4, 7, 1, 'John Doe', '123456789', 'Sân 4', 2, 500.00, 'nuoc khoang', 1, 20.00, 1000.00, 34.00, 1034.00, '2024-04-08', 'chưa thanh toán'),
(18, 1, 4, 1, 'John Doe', '123456789', 'Sân 1', 1, 150.00, 'bánh tráng', 1, 14.00, 150.00, 514.00, 664.00, '2024-04-08', 'Đã thanh toán'),
(18, 1, 6, 1, 'John Doe', '123456789', 'Sân 1', 1, 150.00, 'vợt cầu lông', 1, 500.00, 150.00, 514.00, 664.00, '2024-04-08', 'Đã thanh toán'),
(27, 3, 2, 2, 'Jane Smith', '987654321', 'Sân 3', 4, 120.00, 'Personal Training', 1, 30.00, 480.00, 30.00, 510.00, '2024-04-08', 'chưa thanh toán'),
(15, 1, 4, 1, 'John Doe', '123456789', 'Sân 1', 3, 150.00, 'bánh tráng', 1, 14.00, 450.00, 514.00, 964.00, '2024-04-08', 'chưa thanh toán'),
(15, 1, 6, 1, 'John Doe', '123456789', 'Sân 1', 3, 150.00, 'vợt cầu lông', 1, 500.00, 450.00, 514.00, 964.00, '2024-04-08', 'chưa thanh toán'),
(24, 2, 1, 2, 'Jane Smith', '987654321', 'Sân 2', 2, 100.00, 'Massage', 1, 50.00, 200.00, 50.00, 250.00, '2024-04-08', 'chưa thanh toán'),
(20, 2, 4, 1, 'John Doe', '123456789', 'Sân 2', 2, 100.00, 'bánh tráng', 1, 14.00, 200.00, 14.00, 214.00, '2024-04-08', 'chưa thanh toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhsachdatsan`
--

CREATE TABLE `danhsachdatsan` (
  `MaDS` int(11) NOT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `MaSan` int(11) DEFAULT NULL,
  `LoaiSan` varchar(30) DEFAULT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL,
  `GioBatDau` time DEFAULT NULL,
  `GioKetThuc` time DEFAULT NULL,
  `SoGioThue` int(11) DEFAULT NULL,
  `Thu_2` int(11) DEFAULT NULL,
  `Thu_3` int(11) DEFAULT NULL,
  `Thu_4` int(11) DEFAULT NULL,
  `Thu_5` int(11) DEFAULT NULL,
  `Thu_6` int(11) DEFAULT NULL,
  `Thu_7` int(11) DEFAULT NULL,
  `ChuNhat` int(11) DEFAULT NULL,
  `TongTienSan` float DEFAULT NULL,
  `TongTienDV` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `danhsachdatsan`
--

INSERT INTO `danhsachdatsan` (`MaDS`, `MaKH`, `MaSan`, `LoaiSan`, `NgayBatDau`, `NgayKetThuc`, `GioBatDau`, `GioKetThuc`, `SoGioThue`, `Thu_2`, `Thu_3`, `Thu_4`, `Thu_5`, `Thu_6`, `Thu_7`, `ChuNhat`, `TongTienSan`, `TongTienDV`) VALUES
(1, 1, 1, 'VIP', '2024-04-06', '2024-04-07', '08:00:00', '10:00:00', 2, 1, 0, 1, 0, 1, 0, 1, 200.5, 50.75),
(2, 2, 2, 'SVIP', '2024-04-07', '2024-04-08', '10:00:00', '12:00:00', 2, 0, 1, 0, 1, 0, 1, 0, 150.25, 30.5),
(3, 3, 3, 'SSVIP', '2024-04-08', '2024-04-09', '09:00:00', '11:00:00', 2, 1, 1, 0, 1, 0, 1, 0, 180.75, 40),
(4, 1, 2, 'SVIP', '2024-04-06', '2024-04-06', '08:00:00', '10:00:00', 2, 1, 0, 1, 0, 1, 0, 1, 200, 0),
(13, 1, 1, 'VIP', '2024-04-06', '2024-04-06', '19:15:00', '22:15:00', 3, 0, 0, 1, 0, 1, 0, 0, 450, 160),
(15, 1, 1, 'VIP', '2024-04-06', '2024-04-06', '19:17:00', '22:17:00', 3, 1, 0, 1, 0, 0, 0, 1, 450, 514),
(16, 1, 1, 'SVIP', '2024-04-06', '2024-04-06', '19:46:00', '23:46:00', 4, 1, 0, 1, 0, 1, 0, 0, 600, 54),
(17, 1, 1, 'SSVIP', '2024-04-06', '2024-04-06', '21:14:00', '23:14:00', 2, 0, 0, 1, 0, 1, 0, 0, 300, 30),
(18, 1, 1, 'SSVIP', '2024-04-06', '2024-04-06', '21:14:00', '22:14:00', 1, 1, 0, 1, 0, 0, 0, 0, 150, 514),
(19, 1, 1, 'SSVIP', '2024-04-06', '2024-04-06', '21:32:00', '23:32:00', 2, 0, 0, 1, 0, 0, 0, 1, 300, 30),
(20, 1, 2, 'SVIP', '2024-04-06', '2024-04-06', '21:33:00', '23:33:00', 2, 0, 0, 1, 0, 0, 0, 1, 200, 14),
(21, 1, 1, 'VIP', '2024-04-06', '2024-04-06', '21:45:00', '23:45:00', 2, 0, 0, 1, 0, 1, 0, 0, 300, 0),
(22, 2, 2, 'SSVIP', '2024-04-06', '2024-04-06', '20:45:00', '21:45:00', 1, 0, 0, 1, 0, 1, 0, 0, 100, 0),
(23, 1, 1, 'VIP', '2024-04-06', '2024-04-07', '21:57:00', '23:57:00', 2, 0, 0, 0, 0, 1, 0, 1, 300, 25),
(24, 2, 2, 'SSVIP', '2024-04-06', '2024-04-07', '21:59:00', '23:59:00', 2, 1, 0, 0, 0, 1, 0, 1, 200, 50),
(25, 2, 2, 'SSVIP', '2024-04-06', '2024-04-06', '14:19:00', '23:19:00', 9, 1, 0, 0, 0, 1, 0, 0, 900, 15),
(26, 2, 3, 'SSVIP', '2024-04-06', '2024-04-06', '16:32:00', '23:32:00', 7, 0, 0, 1, 0, 0, 0, 0, 840, 14),
(27, 2, 3, 'SVIP', '2024-04-06', '2024-04-05', '06:37:00', '10:37:00', 4, 1, 0, 0, 0, 1, 0, 0, 480, 30),
(28, 1, 1, 'VIP', '2024-04-07', '2024-04-07', '03:47:00', '06:47:00', 3, 0, 0, 1, 0, 1, 0, 0, 450, 14),
(29, 1, 4, 'MVP', '2024-04-07', '2024-04-07', '04:00:00', '06:00:00', 2, 0, 0, 1, 0, 0, 0, 1, 1000, 34),
(30, 3, 1, 'VIP', '2024-04-07', '2024-04-05', '04:50:00', '06:50:00', 2, 0, 0, 0, 0, 0, 0, 1, 300, 25),
(34, 3, 2, 'SVIP', '2024-04-08', '2024-04-08', '02:52:22', '06:52:00', 4, 1, 0, 1, 0, 1, 0, 0, 400, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu`
--

CREATE TABLE `dichvu` (
  `MaDV` int(11) NOT NULL,
  `TenDV` varchar(50) DEFAULT NULL,
  `LoaiDV` varchar(25) DEFAULT NULL,
  `DVT` varchar(25) DEFAULT NULL,
  `Gia` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dichvu`
--

INSERT INTO `dichvu` (`MaDV`, `TenDV`, `LoaiDV`, `DVT`, `Gia`) VALUES
(1, 'cầu 5 sao', 'Dụng cụ', 'quả', 15),
(2, 'bánh đậu xanh', 'Đồ ăn', 'hộp', 25),
(4, 'bánh tráng', 'Đồ ăn', 'gói', 14),
(5, '7 up', 'Đồ uống', 'lon', 10),
(6, 'vợt cầu lông', 'Dụng cụ', 'cái', 500),
(7, 'nuoc khoang', 'Đồ uống', 'chai', 20),
(8, 'Revive 7 up cầu vồng', 'Dụng cụ', 'chai', 20),
(9, 'bánh gạo', 'Đồ ăn', 'gói', 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaDS` int(11) NOT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `TongTienSan` double NOT NULL,
  `TongTienDV` double NOT NULL,
  `TongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaDS`, `MaKH`, `TongTienSan`, `TongTienDV`, `TongTien`) VALUES
(1, 1, 200.5, 50.75, 251.25),
(2, 2, 150.25, 30.5, 180.75),
(3, 3, 180.75, 40, 220.75),
(4, 1, 200, 0, 200),
(13, 1, 450, 160, 610),
(15, 1, 450, 514, 964),
(16, 1, 600, 54, 654),
(17, 1, 300, 30, 330),
(18, 1, 150, 514, 664),
(19, 1, 300, 30, 330),
(20, 1, 200, 14, 214),
(21, 1, 300, 0, 300),
(22, 2, 100, 0, 100),
(23, 1, 300, 25, 325),
(24, 2, 200, 50, 250),
(25, 2, 900, 15, 915),
(26, 2, 840, 14, 854),
(27, 2, 480, 30, 510),
(28, 1, 450, 14, 464),
(29, 1, 1000, 34, 1034),
(30, 3, 300, 25, 325),
(34, 3, 400, 0, 400);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `TenKH` varchar(50) DEFAULT NULL,
  `SDT` int(11) DEFAULT NULL,
  `SoLanDat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `SDT`, `SoLanDat`) VALUES
(1, 'John Doe', 123456789, 13),
(2, 'Jane Smith', 987654321, 6),
(3, 'Alice Johnson', 555555555, 2),
(4, 'khanhs', 4355425, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieudathang`
--

CREATE TABLE `phieudathang` (
  `MaDS` int(11) DEFAULT NULL,
  `MaDV` int(11) DEFAULT NULL,
  `TenDV` varchar(50) DEFAULT NULL,
  `Gia` float DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `ThanhTien` float DEFAULT (`SoLuong` * `Gia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieudathang`
--

INSERT INTO `phieudathang` (`MaDS`, `MaDV`, `TenDV`, `Gia`, `SoLuong`, `ThanhTien`) VALUES
(1, 1, 'Massage', 50, 2, 100),
(2, 2, 'Personal Training', 30, 1, 30),
(1, 2, 'Personal Training', 30, 2, 60),
(27, 2, 'Personal Training', 30, 1, 30),
(24, 1, 'Massage', 50, 1, 50),
(16, 1, 'cầu 5 sao', 15, 1, 15),
(16, 2, 'bánh đậu xanh', 25, 1, 25),
(16, 4, 'bánh tráng', 14, 1, 14),
(3, 7, 'nuoc khoang', 20, 2, 40),
(3, 6, 'vợt cầu lông', 500, 1, 500),
(28, 4, 'bánh tráng', 14, 1, 14),
(25, 1, 'cầu 5 sao', 15, 1, 15),
(26, 4, 'bánh tráng', 14, 1, 14),
(23, 2, 'bánh đậu xanh', 25, 1, 25),
(17, 1, 'cầu 5 sao', 15, 2, 30),
(30, 1, 'cầu 5 sao', 15, 1, 15),
(30, 5, '7 up', 10, 1, 10),
(15, 4, 'bánh tráng', 14, 1, 14),
(15, 6, 'vợt cầu lông', 500, 1, 500),
(18, 6, 'vợt cầu lông', 500, 1, 500),
(18, 4, 'bánh tráng', 14, 1, 14),
(19, 5, '7 up', 10, 3, 30),
(29, 7, 'nuoc khoang', 20, 1, 20),
(29, 4, 'bánh tráng', 14, 1, 14),
(20, 4, 'bánh tráng', 14, 1, 14);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san`
--

CREATE TABLE `san` (
  `MaSan` int(11) NOT NULL,
  `TenSan` varchar(50) DEFAULT NULL,
  `LoaiSan` varchar(25) DEFAULT NULL,
  `GiaSan` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `san`
--

INSERT INTO `san` (`MaSan`, `TenSan`, `LoaiSan`, `GiaSan`) VALUES
(1, 'Sân 1', 'VIP', 150),
(2, 'Sân 2', 'SVIP', 100),
(3, 'Sân 3', 'SVIP', 120),
(4, 'Sân 4', 'MVP', 500),
(5, 'Sân 5', 'VIP', 150);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `MaDS` (`MaDS`,`MaSan`,`MaDV`,`MaKH`),
  ADD KEY `MaSan` (`MaSan`),
  ADD KEY `MaKH` (`MaKH`);

--
-- Chỉ mục cho bảng `danhsachdatsan`
--
ALTER TABLE `danhsachdatsan`
  ADD PRIMARY KEY (`MaDS`),
  ADD KEY `MaKH` (`MaKH`,`MaSan`),
  ADD KEY `MaSan` (`MaSan`);

--
-- Chỉ mục cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  ADD PRIMARY KEY (`MaDV`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaDS` (`MaDS`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `phieudathang`
--
ALTER TABLE `phieudathang`
  ADD KEY `MaDS` (`MaDS`,`MaDV`),
  ADD KEY `MaDV` (`MaDV`);

--
-- Chỉ mục cho bảng `san`
--
ALTER TABLE `san`
  ADD PRIMARY KEY (`MaSan`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `danhsachdatsan`
--
ALTER TABLE `danhsachdatsan`
  MODIFY `MaDS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  MODIFY `MaDV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `san`
--
ALTER TABLE `san`
  MODIFY `MaSan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaDS`) REFERENCES `danhsachdatsan` (`MaDS`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSan`) REFERENCES `san` (`MaSan`),
  ADD CONSTRAINT `chitiethoadon_ibfk_3` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Các ràng buộc cho bảng `danhsachdatsan`
--
ALTER TABLE `danhsachdatsan`
  ADD CONSTRAINT `danhsachdatsan_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `danhsachdatsan_ibfk_2` FOREIGN KEY (`MaSan`) REFERENCES `san` (`MaSan`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaDS`) REFERENCES `danhsachdatsan` (`MaDS`);

--
-- Các ràng buộc cho bảng `phieudathang`
--
ALTER TABLE `phieudathang`
  ADD CONSTRAINT `phieudathang_ibfk_1` FOREIGN KEY (`MaDS`) REFERENCES `danhsachdatsan` (`MaDS`),
  ADD CONSTRAINT `phieudathang_ibfk_2` FOREIGN KEY (`MaDV`) REFERENCES `dichvu` (`MaDV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
