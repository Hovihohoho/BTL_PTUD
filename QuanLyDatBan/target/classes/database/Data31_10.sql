USE [master]
GO
/****** Object:  Database [QuanLyDatBan]    Script Date: 10/31/2024 9:11:03 PM ******/
CREATE DATABASE [QuanLyDatBan]
GO
ALTER DATABASE [QuanLyDatBan] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyDatBan].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyDatBan] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyDatBan] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyDatBan] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyDatBan] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyDatBan] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyDatBan] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyDatBan] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyDatBan] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyDatBan] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyDatBan] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyDatBan] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [QuanLyDatBan]
GO
/****** Object:  Table [dbo].[Ban]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ban](
	[maBan] [nchar](10) NOT NULL,
	[maLoai] [nchar](10) NOT NULL,
	[trangThaiBan] [nvarchar](50) NOT NULL,
	[soLuongGhe] [int] NULL,
	[viTri] [nvarchar](50) NULL,
 CONSTRAINT [PK_Ban] PRIMARY KEY CLUSTERED 
(
	[maBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[maCa] [nchar](10) NOT NULL,
	[maNV] [nvarchar](50) NOT NULL,
	[gioBatDau] [datetime] NOT NULL,
	[gioKetThuc] [datetime] NOT NULL,
 CONSTRAINT [PK_CaLamViec] PRIMARY KEY CLUSTERED 
(
	[maCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ChiTietDonDatBan]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDonDatBan](
	[maHD] [nchar](20) NOT NULL,
	[maKH] [nchar](10) NOT NULL,
	[maNV] [nvarchar](50) NOT NULL,
	[maBan] [nchar](10) NOT NULL,
	[maYeuCau] [int] NOT NULL,
	[thoiGianTao] [datetime] NOT NULL,
	[ngayDatBan] [datetime] NOT NULL,
 CONSTRAINT [PK_ChiTietDonDatBan] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maYeuCau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DonDatBan]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonDatBan](
	[maHD] [nchar](20) NOT NULL,
	[soLuongKhach] [int] NOT NULL,
 CONSTRAINT [PK_DonDatBan] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [nchar](10) NOT NULL,
	[tenKH] [nvarchar](50) NOT NULL,
	[sdt] [nchar](11) NULL,
	[email] [nvarchar](50) NULL,
	[lichSuDatBan] [nvarchar](max) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LoaiBan]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiBan](
	[maLoai] [nchar](10) NOT NULL,
	[tenLoai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LoaiBan] PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LoaiMon]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiMon](
	[maLoai] [nchar](10) NOT NULL,
	[tenLoai] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MonAn]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonAn](
	[maMonAn] [nchar](10) NOT NULL,
	[tenMonAn] [nvarchar](50) NOT NULL,
	[giaTien] [real] NOT NULL,
	[thongTinMon] [nvarchar](max) NULL,
	[trangThaiMonAn] [nvarchar](25) NOT NULL,
	[maLoai] [nchar](10) NULL,
 CONSTRAINT [PK_MonAn] PRIMARY KEY CLUSTERED 
(
	[maMonAn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](50) NOT NULL,
	[maTK] [nchar](10) NOT NULL,
	[tenNV] [nvarchar](50) NOT NULL,
	[sdt] [nchar](11) NULL,
	[email] [nvarchar](50) NULL,
	[ngayVaoLam] [datetime] NOT NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTK] [nchar](10) NOT NULL,
	[tenTK] [nvarchar](50) NOT NULL,
	[chucVu] [nchar](10) NOT NULL,
	[matKhau] [nchar](10) NOT NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[YeuCauKhachHang]    Script Date: 10/31/2024 9:11:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[YeuCauKhachHang](
	[maYeuCau] [int] NOT NULL,
	[maMonAn] [nchar](10) NOT NULL,
	[maKH] [nchar](10) NOT NULL,
	[yeuCauDacBiet] [nvarchar](max) NULL,
	[danhSachMon] [nvarchar](max) NULL,
 CONSTRAINT [PK_YeuCauKhachHang] PRIMARY KEY CLUSTERED 
(
	[maYeuCau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B001      ', N'L001      ', N'Trống', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B002      ', N'L001      ', N'Có khách', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B003      ', N'L002      ', N'Trống', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B004      ', N'L002      ', N'Có khách', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B005      ', N'L003      ', N'Trống', 8, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B006      ', N'L003      ', N'Đặt trước', 8, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B007      ', N'L001      ', N'Trống', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B008      ', N'L001      ', N'Có khách', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B009      ', N'L002      ', N'Trống', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B010      ', N'L002      ', N'Có khách', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B011      ', N'L003      ', N'Trống', 8, N'Tầng 3')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B012      ', N'L003      ', N'Đặt trước', 8, N'Tầng 3')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B013      ', N'L001      ', N'Trống', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B014      ', N'L001      ', N'Có khách', 4, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B015      ', N'L002      ', N'Trống', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B016      ', N'L002      ', N'Có khách', 6, N'Tầng 2')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B017      ', N'L003      ', N'Trống', 8, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B018      ', N'L003      ', N'Đặt trước', 8, N'Tầng 1')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B019      ', N'L001      ', N'Trống', 4, N'Tầng 3')
INSERT [dbo].[Ban] ([maBan], [maLoai], [trangThaiBan], [soLuongGhe], [viTri]) VALUES (N'B020      ', N'L001      ', N'Có khách', 4, N'Tầng 3')
INSERT [dbo].[LoaiBan] ([maLoai], [tenLoai]) VALUES (N'L001', N'bàn thường')
INSERT [dbo].[LoaiBan] ([maLoai], [tenLoai]) VALUES (N'L002', N'bàn vip')
INSERT [dbo].[LoaiBan] ([maLoai], [tenLoai]) VALUES (N'L003', N'bàn')
INSERT [dbo].[LoaiMon] ([maLoai], [tenLoai]) VALUES (N'LM001', N'Đồ uống')
INSERT [dbo].[LoaiMon] ([maLoai], [tenLoai]) VALUES (N'LM002', N'Món khô')
INSERT [dbo].[LoaiMon] ([maLoai], [tenLoai]) VALUES (N'LM003', N'Món nước')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M001      ', N'Trà sữa', 30000, N'Trà sữa thơm ngon với nhiều vị.', N'Có sẵn', N'LM001     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M002      ', N'Cà phê đen', 25000, N'Cà phê đen nguyên chất.', N'Có sẵn', N'LM001     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M003      ', N'Nước cam', 20000, N'Nước cam tươi mát.', N'Có sẵn', N'LM001     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M004      ', N'Bún bò Huế', 50000, N'Món bún bò đặc trưng miền Trung.', N'Có sẵn', N'LM002     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M005      ', N'Phở bò', 60000, N'Phở bò với nước dùng thơm ngon.', N'Có sẵn', N'LM002     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M006      ', N'Canh chua cá', 55000, N'Canh chua với cá tươi.', N'Có sẵn', N'LM002     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M007      ', N'Cơm chiên Dương Châu', 45000, N'Cơm chiên với nhiều loại nguyên liệu.', N'Có sẵn', N'LM003     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M008      ', N'Gà rán', 70000, N'Gà rán giòn rụm.', N'Có sẵn', N'LM003     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M009      ', N'Bánh mì thịt', 25000, N'Bánh mì với nhân thịt đầy đặn.', N'Có sẵn', N'LM003     ')
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [giaTien], [thongTinMon], [trangThaiMonAn], [maLoai]) VALUES (N'M010      ', N'Mì xào thập cẩm', 40000, N'Mì xào với nhiều loại rau củ và thịt.', N'Có sẵn', N'LM003     ')
INSERT [dbo].[TaiKhoan] ([maTK], [tenTK], [chucVu], [matKhau]) VALUES (N'TK001', N'hao', N'NV', N'1')
ALTER TABLE [dbo].[Ban]  WITH CHECK ADD FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiBan] ([maLoai])
GO
ALTER TABLE [dbo].[Ban]  WITH CHECK ADD FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiBan] ([maLoai])
GO
ALTER TABLE [dbo].[CaLamViec]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[ChiTietDonDatBan]  WITH CHECK ADD FOREIGN KEY([maBan])
REFERENCES [dbo].[Ban] ([maBan])
GO
ALTER TABLE [dbo].[ChiTietDonDatBan]  WITH CHECK ADD FOREIGN KEY([maYeuCau])
REFERENCES [dbo].[YeuCauKhachHang] ([maYeuCau])
GO
ALTER TABLE [dbo].[ChiTietDonDatBan]  WITH CHECK ADD FOREIGN KEY([maHD])
REFERENCES [dbo].[DonDatBan] ([maHD])
GO
ALTER TABLE [dbo].[ChiTietDonDatBan]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[MonAn]  WITH CHECK ADD  CONSTRAINT [FK_MonAn_LoaiMon] FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiMon] ([maLoai])
GO
ALTER TABLE [dbo].[MonAn] CHECK CONSTRAINT [FK_MonAn_LoaiMon]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maTK])
REFERENCES [dbo].[TaiKhoan] ([maTK])
GO
ALTER TABLE [dbo].[YeuCauKhachHang]  WITH CHECK ADD FOREIGN KEY([maMonAn])
REFERENCES [dbo].[MonAn] ([maMonAn])
GO
ALTER TABLE [dbo].[YeuCauKhachHang]  WITH CHECK ADD FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
USE [master]
GO
ALTER DATABASE [QuanLyDatBan] SET  READ_WRITE 
GO
