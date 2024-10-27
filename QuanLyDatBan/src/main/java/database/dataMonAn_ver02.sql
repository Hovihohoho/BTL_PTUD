

 -- DELETE FROM MonAn;

USE [QuanLyDatBan]
GO

-- Thêm dữ liệu vào bảng MonAn với tiền tố N để hỗ trợ Unicode
INSERT INTO [dbo].[MonAn] (maMonAn, tenMonAn, giaTien, thongTinMon, trangThaiMonAn, maLoai) VALUES
('M001', N'Trà sữa', 30000, N'Trà sữa thơm ngon với nhiều vị.', N'Có sẵn', 'LM001'),
('M002', N'Cà phê đen', 25000, N'Cà phê đen nguyên chất.', N'Có sẵn', 'LM001'),
('M003', N'Nước cam', 20000, N'Nước cam tươi mát.', N'Có sẵn', 'LM001'),
('M004', N'Bún bò Huế', 50000, N'Món bún bò đặc trưng miền Trung.', N'Có sẵn', 'LM002'),
('M005', N'Phở bò', 60000, N'Phở bò với nước dùng thơm ngon.', N'Có sẵn', 'LM002'),
('M006', N'Canh chua cá', 55000, N'Canh chua với cá tươi.', N'Có sẵn', 'LM002'),
('M007', N'Cơm chiên Dương Châu', 45000, N'Cơm chiên với nhiều loại nguyên liệu.', N'Có sẵn', 'LM003'),
('M008', N'Gà rán', 70000, N'Gà rán giòn rụm.', N'Có sẵn', 'LM003'),
('M009', N'Bánh mì thịt', 25000, N'Bánh mì với nhân thịt đầy đặn.', N'Có sẵn', 'LM003'),
('M010', N'Mì xào thập cẩm', 40000, N'Mì xào với nhiều loại rau củ và thịt.', N'Có sẵn', 'LM003');

GO
