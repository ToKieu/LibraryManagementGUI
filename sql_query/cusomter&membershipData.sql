use LIBRARY_MANAGEMENT;

insert into `CUSTOMER` values
('CUS001', 'Thế Vũ', '2003-08-29', 'HCM', '01234', 'vu@gmail.com', '0123456789', 'Nam', 0),
('CUS002', 'Minh Trí', '2003-08-29', 'HCM', '01234', 'tri@gmail.com', '0123456789', 'Nam', 0),
('CUS003', 'Ngọc Huy', '2003-08-29', 'HCM', '01234', 'huy@gmail.com', '0123456789', 'Nam', 0),
('CUS004', 'Bảo Quỳnh', '2003-08-29', 'HCM', '01234', 'quynh@gmail.com', '0123456789', 'Nam', 0),
('CUS005', 'Tiến Hải', '2003-08-29', 'HCM', '01234', 'hai@gmail.com', '0123456789', 'Nam', 0),
('CUS006', 'Tiến Đạt', '2003-08-29', 'HCM', '01234', 'dat@gmail.com', '0123456789', 'Nam', 0),
('CUS007', 'Minh Nam', '2003-08-29', 'HCM', '01234', 'nam@gmail.com', '0123456789', 'Nam', 0),
('CUS008', 'An Tuấn', '2003-08-29', 'HCM', '01234', 'tuan@gmail.com', '0123456789', 'Nam', 0),
('CUS009', 'Tuấn Khải', '2003-08-29', 'HCM', '01234', 'khai@gmail.com', '0123456789', 'Nam', 0),
('CUS010', 'Huy Hoàng', '2003-08-29', 'HCM', '01234', 'hoang@gmail.com', '0123456789', 'Nam', 0),
('CUS011', 'Hải Nam', '2003-08-29', 'HCM', '01234', 'nam@gmail.com', '0123456789', 'Nam', 0),
('CUS012', 'Minh Khôi', '2003-08-29', 'HCM', '01234', 'khoi@gmail.com', '0123456789', 'Nam', 0),
('CUS013', 'Như Quỳnh', '2003-08-29', 'HCM', '01234', 'quynh@gmail.com', '0123456789', 'Nữ', 0),
('CUS014', 'Yến Nhi', '2003-08-29', 'HCM', '01234', 'nhi@gmail.com', '0123456789', 'Nữ', 0),
('CUS015', 'Quốc Tiến', '2003-08-29', 'HCM', '01234', 'tien@gmail.com', '0123456789', 'Nam', 0),
('CUS016', 'Đức Thắng', '2003-08-29', 'HCM', '01234', 'thang@gmail.com', '0123456789', 'Nam', 0),
('CUS017', 'Kim Phú', '2003-08-29', 'HCM', '01234', 'phu@gmail.com', '0123456789', 'Nam', 0),
('CUS018', 'Quốc Cường', '2003-08-29', 'HCM', '01234', 'cuong@gmail.com', '0123456789', 'Nam', 0),
('CUS019', 'Quốc Đại', '2003-08-29', 'HCM', '01234', 'dai@gmail.com', '0123456789', 'Nam', 0),
('CUS020', 'Bình Hải', '2003-08-29', 'HCM', '01234', 'hai2@gmail.com', '0123456789', 'Nam', 0),
('CUS021', 'Mỹ Ngọc', '2003-08-29', 'HCM', '01234', 'ngoc@gmail.com', '0123456789', 'Nữ', 0),
('CUS022', 'Việt Hùng', '2003-08-29', 'HCM', '01234', 'hung@gmail.com', '0123456789', 'Nam', 0),
('CUS023', 'Minh Quang', '2003-08-29', 'HCM', '01234', 'quang@gmail.com', '0123456789', 'Nam', 0),
('CUS024', 'Hiếu Ngân', '2003-08-29', 'HCM', '01234', 'ngan@gmail.com', '0123456789', 'Nữ', 0),
('CUS025', 'Diễm Quỳnh', '2003-08-29', 'HCM', '01234', 'quynh2@gmail.com', '0123456789', 'Nữ', 0),
('CUS026', 'Minh Nghị', '2003-08-29', 'HCM', '01234', 'nghi@gmail.com', '0123456789', 'Nam', 0),
('CUS027', 'Duy Thành', '2003-08-29', 'HCM', '01234', 'thanh@gmail.com', '0123456789', 'Nam', 0),
('CUS028', 'Minh Nguyên', '2003-08-29', 'HCM', '01234', 'nguyen@gmail.com', '0123456789', 'Nam', 0),
('CUS029', 'Thiên Quốc', '2003-08-29', 'HCM', '01234', 'quoc@gmail.com', '0123456789', 'Nam', 0),
('CUS030', 'Trung Kha', '2003-08-29', 'HCM', '01234', 'kha@gmail.com', '0123456789', 'Nam', 0);

insert into `MEMBERSHIP_TYPE` values ('Bạc', 10, 0), ('Vàng', 20, 0), ('Bạch Kim', 30, 0);

insert into `MEMBERSHIP` values
('MEM001', 'CUS001', 'Bạc', '2023-09-28', '2024-09-28', 0),
('MEM002', 'CUS004', 'Vàng', '2023-07-28', '2024-07-28', 0),
('MEM003', 'CUS005', 'Bạch Kim', '2023-08-28', '2024-07-28', 0),
('MEM004', 'CUS007', 'Bạch Kim', '2023-04-28', '2024-07-28', 0),
('MEM005', 'CUS012', 'Bạch Kim', '2023-05-28', '2024-07-28', 0),
('MEM006', 'CUS030', 'Bạch Kim', '2023-06-28', '2024-07-28', 0),
('MEM007', 'CUS029', 'Bạc', '2023-06-28', '2024-07-28', 0),
('MEM008', 'CUS015', 'Bạc', '2023-06-28', '2024-07-28', 0),
('MEM009', 'CUS002', 'Vàng', '2023-01-28', '2024-07-28', 0),
('MEM010', 'CUS003', 'Bạc', '2023-01-28', '2024-07-28', 0),
('MEM011', 'CUS008', 'Vàng', '2023-03-28', '2024-07-28', 0),
('MEM012', 'CUS013', 'Bạch Kim', '2023-03-28', '2024-07-28', 0),
('MEM013', 'CUS014', 'Bạc', '2023-10-28', '2024-07-28', 0),
('MEM014', 'CUS018', 'Bạc', '2023-12-28', '2024-07-28', 0),
('MEM015', 'CUS011', 'Vàng', '2023-10-28', '2024-07-28', 0),
('MEM016', 'CUS006', 'Vàng', '2023-11-28', '2024-07-28', 0),
('MEM017', 'CUS022', 'Bạc', '2023-09-28', '2024-07-28', 0),
('MEM018', 'CUS023', 'Bạc', '2023-04-28', '2024-07-28', 0),
('MEM019', 'CUS025', 'Vàng', '2023-04-28', '2024-07-28', 0),
('MEM020', 'CUS027', 'Bạc', '2023-04-28', '2024-07-28', 0);