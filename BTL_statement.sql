DROP DATABASE IF EXISTS sale_management;

CREATE DATABASE sale_management
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE sale_management;

CREATE TABLE Employees (
	EmployeeID 		CHAR(5) PRIMARY KEY,
    EName 			VARCHAR(30) NOT NULL,
    Gender			VARCHAR(5),
    Phone 			CHAR(10) UNIQUE NOT NULL,
    Email			VARCHAR(50) UNIQUE NOT NULL,
    Salary			INT NOT NULL
);

CREATE TABLE Managers (
	ManagerID		CHAR(5) PRIMARY KEY,
    Title			VARCHAR(30) UNIQUE NOT NULL,
    FOREIGN KEY (ManagerID) REFERENCES Employees(EmployeeID) 
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
    
CREATE TABLE Staffs (
	StaffID			CHAR(5) PRIMARY KEY,
    HireDate 		DATE NOT NULL,
    ManagerID		CHAR(5)	 NOT NULL,
    FOREIGN KEY (StaffID) REFERENCES Employees(EmployeeID) 
		ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (ManagerID) REFERENCES Managers(ManagerID)
		ON UPDATE CASCADE
);

CREATE TABLE Customers (
	CustomerID		CHAR(8) PRIMARY KEY,
    Phone 			CHAR(10) UNIQUE NOT NULL,
    Email			VARCHAR(50) UNIQUE,
    Address			VARCHAR(255) NOT NULL
);

CREATE TABLE IndividualCus (
	IndividualID	CHAR(8) PRIMARY KEY,
    ICName 			VARCHAR(30) NOT NULL,
    Gender			VARCHAR(5),
    FOREIGN KEY (IndividualID) REFERENCES Customers(CustomerID) 
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE CorporateCus (
	CorporateID		CHAR(8) PRIMARY KEY,
    CompanyName		VARCHAR(100) UNIQUE NOT NULL,
    TaxCode 		VARCHAR(15) UNIQUE NOT NULL,
    ContactPerson 	VARCHAR(30) NOT NULL,
    FOREIGN KEY (CorporateID) REFERENCES Customers(CustomerID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE Products (
	ProductID		CHAR(8) PRIMARY KEY,
    ProductName		VARCHAR(30)	 UNIQUE NOT NULL,
    Descriptions	VARCHAR(255) NOT NULL,
    Price 			INT NOT NULL
);

CREATE TABLE Orders (
	OrderID			CHAR(8) PRIMARY KEY,
    ManagerID		CHAR(5) NULL,
    CustomerID		CHAR(8) NOT NULL,
    OrderDate		DATETIME NOT NULL,
    OrderStatus		VARCHAR(30) NOT NULL,
    FOREIGN KEY (ManagerID) REFERENCES Managers(ManagerID)
		ON UPDATE CASCADE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE OrderDetail (
	OrderID		CHAR(8) NOT NULL,
    ProductID 	CHAR(8) NOT NULL,
    Quantity 	INT NOT NULL CHECK(Quantity > 0),
	PRIMARY KEY (OrderID, ProductID),
	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
	FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
		ON DELETE CASCADE
);

CREATE TABLE Payment (
	 PaymentID		CHAR(10) PRIMARY KEY,
     OrderID		CHAR(8) UNIQUE NOT NULL,
     PaymentMethod	VARCHAR(30),
     PaymentDate	DATETIME,
     PaymentStatus	VARCHAR(30) NOT NULL,
     FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Reports (
	ReportID		CHAR(5)	 PRIMARY KEY,
	ManagerID		CHAR(5)  NOT NULL,
    StaffID			CHAR(5)  NOT NULL,
    StartDate		DATE  NOT NULL,
    EndDate			DATE  NOT NULL,
	FOREIGN KEY (ManagerID) REFERENCES Managers(ManagerID)
		ON UPDATE CASCADE,
    FOREIGN KEY (StaffID) REFERENCES Staffs(StaffID)
		ON UPDATE CASCADE,
    CHECK (DATEDIFF(EndDate, StartDate) >= 7)
);

-- Bảng 1: Employees (15 người)
INSERT INTO Employees VALUES
('E2501', 'Nguyễn Văn An', 'Nam', '0912345678', 'nguyenvanan@gmail.com', 15000000),
('E2502', 'Trần Thị Bình', 'Nữ', '0923456789', 'tranthibinh@gmail.com', 8000000),
('E2503', 'Lê Văn Cường', 'Nam', '0934567890', 'levancuong@gmail.com', 12000000),
('E2504', 'Phạm Thị Dung', 'Nữ', '0945678901', 'phamthidung@gmail.com', 7000000),
('E2505', 'Hoàng Văn Đức', 'Nam', '0956789012', 'hoangvanduc@gmail.com', 18000000),
('E2506', 'Vũ Thị Em', 'Nữ', '0967890123', 'vuthiem@gmail.com', 6000000),
('E2507', 'Đặng Văn Phong', 'Nam', '0978901234', 'dangvanphong@gmail.com', 14000000),
('E2508', 'Bùi Thị Giang', 'Nữ', '0989012345', 'buithigiang@gmail.com', 9000000),
('E2509', 'Nguyễn Văn Hải', 'Nam', '0990123456', 'nguyenvanhai@gmail.com', 11000000),
('E2510', 'Trần Văn Khôi', 'Nam', '0911122334', 'tranvankhoi@gmail.com', 13000000),
('E2511', 'Lê Thị Lan', 'Nữ', '0922233445', 'lethilan@gmail.com', 7500000),
('E2512', 'Phạm Văn Minh', 'Nam', '0933344556', 'phamvanminh@gmail.com', 16000000),
('E2513', 'Hoàng Thị Ngọc', 'Nữ', '0944455667', 'hoangthingoc@gmail.com', 8500000),
('E2514', 'Vũ Văn Phú', 'Nam', '0955566778', 'vuvanphu@gmail.com', 19000000),
('E2515', 'Đặng Thị Quỳnh', 'Nữ', '0966677889', 'dangthiquynh@gmail.com', 9500000);

-- Bảng 2: Managers (4 người)
INSERT INTO Managers VALUES
('E2501', 'Giám đốc'),
('E2505', 'Trưởng phòng Marketing'),
('E2507', 'Quản lý Bán hàng'),
('E2512', 'Trưởng phòng Kế hoạch');

-- Bảng 3: Staffs (11 người)
INSERT INTO Staffs VALUES
('E2502', '2023-03-15', 'E2501'),
('E2503', '2023-05-20', 'E2505'),
('E2504', '2024-01-10', 'E2507'),
('E2506', '2023-08-25', 'E2512'),
('E2508', '2024-02-14', 'E2501'),
('E2509', '2023-11-30', 'E2505'),
('E2510', '2023-07-12', 'E2507'),
('E2511', '2024-03-05', 'E2512'),
('E2513', '2023-09-18', 'E2501'),
('E2514', '2024-04-22', 'E2505'),
('E2515', '2023-12-08', 'E2507');

-- Bảng 4: Customers (30 người)
INSERT INTO Customers VALUES
('C2023001', '0911222333', 'khachhang1@gmail.com', 'Số 12, ngõ 45, phố Lê Duẩn, Ba Đình, Hà Nội'),
('C2023002', '0922333444', 'khachhang2@gmail.com', 'Số 78, tổ 3, phường Giảng Võ, Ba Đình, Hà Nội'),
('C2024001', '0933444555', 'khachhang3@gmail.com', 'Số 23, ngách 56, phố Kim Mã, Ba Đình, Hà Nội'),
('C2024002', '0944555666', 'khachhang4@gmail.com', 'Số 89, khu tập thể A1, phường Liễu Giai, Ba Đình, Hà Nội'),
('C2024003', '0955666777', 'khachhang5@gmail.com', 'Số 34, ngõ 12, phố Nguyễn Chí Thanh, Đống Đa, Hà Nội'),
('C2024004', '0966777888', 'khachhang6@gmail.com', 'Số 56, tổ 7, phường Láng Hạ, Đống Đa, Hà Nội'),
('C2024005', '0977888999', 'khachhang7@gmail.com', 'Số 78, ngách 34, phố Tây Sơn, Đống Đa, Hà Nội'),
('C2024006', '0988999000', 'khachhang8@gmail.com', 'Số 90, khu tập thể B2, phường Trung Liệt, Đống Đa, Hà Nội'),
('C2024007', '0999000111', 'khachhang9@gmail.com', 'Số 45, ngõ 89, phố Khâm Thiên, Đống Đa, Hà Nội'),
('C2024008', '0911112222', 'khachhang10@gmail.com', 'Số 67, tổ 5, phường Phương Liên, Đống Đa, Hà Nội'),
('C2024009', '0922223333', 'khachhang11@gmail.com', 'Số 12, ngách 78, phố Bạch Mai, Hai Bà Trưng, Hà Nội'),
('C2024010', '0933334444', 'khachhang12@gmail.com', 'Số 34, khu tập thể C3, phường Minh Khai, Hai Bà Trưng, Hà Nội'),
('C2024011', '0944445555', 'khachhang13@gmail.com', 'Số 56, ngõ 23, phố Trần Đại Nghĩa, Hai Bà Trưng, Hà Nội'),
('C2024012', '0955556666', 'khachhang14@gmail.com', 'Số 78, tổ 9, phường Bạch Đằng, Hai Bà Trưng, Hà Nội'),
('C2024013', '0966667777', 'khachhang15@gmail.com', 'Số 90, ngách 45, phố Lê Thanh Nghị, Hai Bà Trưng, Hà Nội'),
('C2024014', '0977778888', 'khachhang16@gmail.com', 'Số 23, khu tập thể D4, phường Đồng Tâm, Hai Bà Trưng, Hà Nội'),
('C2025001', '0988889999', 'khachhang17@gmail.com', 'Số 45, ngõ 67, phố Tạ Quang Bửu, Hai Bà Trưng, Hà Nội'),
('C2025002', '0999990000', 'khachhang18@gmail.com', 'Số 67, tổ 2, phường Thanh Lương, Hai Bà Trưng, Hà Nội'),
('C2025003', '0910001111', 'khachhang19@gmail.com', 'Số 89, ngách 56, phố Vũ Tông Phan, Thanh Xuân, Hà Nội'),
('C2025004', '0921112222', 'khachhang20@gmail.com', 'Số 12, khu tập thể E5, phường Nhân Chính, Thanh Xuân, Hà Nội'),
('C2025005', '0932223333', 'khachhang21@gmail.com', 'Số 34, ngõ 78, phố Nguyễn Trãi, Thanh Xuân, Hà Nội'),
('C2025006', '0943334444', 'khachhang22@gmail.com', 'Số 56, tổ 4, phường Khương Trung, Thanh Xuân, Hà Nội'),
('C2025007', '0954445555', 'khachhang23@gmail.com', 'Số 78, ngách 67, phố Khuất Duy Tiến, Thanh Xuân, Hà Nội'),
('C2025008', '0965556666', 'khachhang24@gmail.com', 'Số 90, khu tập thể F6, phường Thanh Xuân Bắc, Thanh Xuân, Hà Nội'),
('C2025009', '0976667777', 'khachhang25@gmail.com', 'Số 23, ngõ 34, phố Lê Văn Lương, Thanh Xuân, Hà Nội'),
('C2025010', '0987778888', 'khachhang26@gmail.com', 'Số 45, tổ 8, phường Hạ Đình, Thanh Xuân, Hà Nội'),
('C2023003', '0998889999', 'khachhang27@gmail.com', 'Số 67, ngách 23, phố Nguyễn Huy Tưởng, Thanh Xuân, Hà Nội'),
('C2023004', '0919990000', 'khachhang28@gmail.com', 'Số 89, khu tập thể G7, phường Kim Giang, Thanh Xuân, Hà Nội'),
('C2023005', '0920001111', 'khachhang29@gmail.com', 'Số 12, ngõ 90, phố Trần Duy Hưng, Cầu Giấy, Hà Nội'),
('C2023006', '0931112222', 'khachhang30@gmail.com', 'Số 34, tổ 6, phường Dịch Vọng, Cầu Giấy, Hà Nội');

-- Bảng 5: IndividualCus (20 người)
INSERT INTO IndividualCus VALUES
('C2023001', 'Nguyễn Văn Nam', 'Nam'),
('C2023002', 'Trần Thị Hoa', 'Nữ'),
('C2024001', 'Lê Văn Tùng', 'Nam'),
('C2024002', 'Phạm Thị Mai', 'Nữ'),
('C2024003', 'Hoàng Văn Sơn', 'Nam'),
('C2024004', 'Vũ Thị Lan', 'Nữ'),
('C2024005', 'Đặng Văn Bình', 'Nam'),
('C2024006', 'Bùi Thị Huệ', 'Nữ'),
('C2024007', 'Nguyễn Văn Đạt', 'Nam'),
('C2024008', 'Trần Thị Nga', 'Nữ'),
('C2024009', 'Lê Văn Hùng', 'Nam'),
('C2024010', 'Phạm Thị Thảo', 'Nữ'),
('C2024011', 'Hoàng Văn Thắng', 'Nam'),
('C2024012', 'Vũ Thị Dung', 'Nữ'),
('C2025001', 'Đặng Văn Kiên', 'Nam'),
('C2025002', 'Bùi Thị Ngọc', 'Nữ'),
('C2025003', 'Nguyễn Văn Tuấn', 'Nam'),
('C2025004', 'Trần Thị Hương', 'Nữ'),
('C2023003', 'Lê Văn Phong', 'Nam'),
('C2023004', 'Phạm Thị Linh', 'Nữ');

-- Bảng 6: CorporateCus (10 người)
INSERT INTO CorporateCus VALUES
('C2024013', 'Công ty TNHH Thương mại Dịch vụ ABC', '0101234567', 'Nguyễn Văn An'),
('C2024014', 'Công ty Cổ phần Sản xuất XYZ', '0102345678', 'Trần Văn Bình'),
('C2025005', 'Công ty TNHH MTV DEF', '0103456789', 'Lê Thị Chi'),
('C2025006', 'Công ty Cổ phần GHI Group', '0104567890', 'Phạm Văn Đức'),
('C2025007', 'Công ty TNHH JKL Việt Nam', '0105678901', 'Hoàng Thị Em'),
('C2025008', 'Công ty Cổ phần MNO', '0106789012', 'Vũ Văn Phong'),
('C2025009', 'Công ty TNHH PQR Solutions', '0107890123', 'Đặng Thị Giang'),
('C2025010', 'Công ty Cổ phần STU', '0108901234', 'Bùi Văn Hải'),
('C2023005', 'Công ty TNHH VWX', '0109012345', 'Nguyễn Thị Khôi'),
('C2023006', 'Công ty Cổ phần YZ Corporation', '0100123456', 'Trần Văn Lan');

-- Bảng 7: Products (30 sản phẩm)
INSERT INTO Products VALUES
('P2023001', 'Danh thiếp Cao cấp', 'Danh thiếp in offset chất lượng cao, 2 mặt', 150000),
('P2023002', 'Tờ rơi A4', 'Tờ rơi quảng cáo khổ A4, in 4 màu', 50000),
('P2023003', 'Poster A3', 'Poster quảng cáo khổ A3, in chất lượng cao', 120000),
('P2023004', 'Banner khổ lớn', 'Banner quảng cáo ngoài trời, chống thấm nước', 450000),
('P2023005', 'Hộp đựng quà tặng', 'Hộp giấy đựng quà cao cấp, nhiều kích thước', 80000),
('P2023006', 'Túi giấy shopping', 'Túi giấy có quai xách, in logo thương hiệu', 60000),
('P2023007', 'Menu nhà hàng', 'Menu da bìa cứng, in offset chất lượng cao', 250000),
('P2023008', 'Sổ tay công ty', 'Sổ tay bìa da, ruột giấy cao cấp', 180000),
('P2023009', 'Bìa hồ sơ', 'Bìa hồ sơ công ty, in logo và thông tin', 90000),
('P2023010', 'Thiệp mời', 'Thiệp mời sự kiện, in chữ mạ vàng', 75000),
('P2023011', 'Tem bảo hành', 'Tem bảo hành sản phẩm, chống giả', 35000),
('P2023012', 'Nhãn sản phẩm', 'Nhãn dán sản phẩm, nhiều kích thước', 40000),
('P2023013', 'Bảng hiệu công ty', 'Bảng hiệu mica, in UV chất lượng cao', 650000),
('P2023014', 'Kẹp file tài liệu', 'Kẹp file có in logo công ty', 55000),
('P2023015', 'Bút bi quảng cáo', 'Bút bi in logo thương hiệu', 30000),
('P2023016', 'Áo đồng phục', 'Áo thun đồng phục công ty', 150000),
('P2023017', 'Mũ bảo hiểm', 'Mũ bảo hiểm in logo công ty', 350000),
('P2023018', 'Cốc giữ nhiệt', 'Cốc giữ nhiệt in logo thương hiệu', 120000),
('P2023019', 'Ba lô công ty', 'Ba lô vải canvas in logo', 280000),
('P2023020', 'Ổ cắm điện quảng cáo', 'Ổ cắm điện có in logo công ty', 95000),
('P2023021', 'Túi vải không dệt', 'Túi vải thân thiện môi trường', 45000),
('P2024001', 'Móc khóa quảng cáo', 'Móc khóa kim loại in logo', 25000),
('P2024002', 'Bộ văn phòng phẩm', 'Bộ sản phẩm văn phòng đầy đủ', 320000),
('P2024003', 'Giấy note hình logo', 'Giấy note hình logo công ty', 35000),
('P2024004', 'Hộp namecard gỗ', 'Hộp đựng danh thiếp bằng gỗ tự nhiên', 180000),
('P2024005', 'Bảng tên nhân viên', 'Bảng tên mạ vàng, in chìm', 85000),
('P2024006', 'Tấm che mặt nạ', 'Tấm che mặt nạ in thông điệp', 55000),
('P2025001', 'Bộ quà tặng Tết', 'Bộ quà tặng cao cấp dịp Tết', 500000),
('P2025002', 'Thiệp chúc mừng năm mới', 'Thiệp chúc Tết thiết kế độc quyền', 65000),
('P2025003', 'Lịch để bàn 2025', 'Lịch để bàn 12 tháng, in 4 màu', 95000);

-- Bảng 8: Orders (40 đơn hàng)
INSERT INTO Orders (OrderID, ManagerID, CustomerID, OrderDate, OrderStatus) VALUES
('ORD23001', 'E2501', 'C2024013', '2023-01-15 10:25:40', 'Đã hoàn thành'),
('ORD23002', 'E2505', 'C2023001', '2023-02-20 14:10:05', 'Đã hoàn thành'),
('ORD23003', 'E2507', 'C2024014', '2023-03-10 17:55:12', 'Đã hoàn thành'),
('ORD23004', 'E2512', 'C2024001', '2023-04-05 08:05:33', 'Đang xử lý'),
('ORD23005', 'E2501', 'C2024013', '2023-05-18 11:30:59', 'Đã hoàn thành'),
('ORD23006', 'E2505', 'C2025005', '2023-06-22 15:45:00', 'Đã hoàn thành'),
('ORD23007', 'E2507', 'C2023002', '2023-07-30 13:20:18', 'Đã hoàn thành'),
('ORD23008', 'E2512', 'C2024014', '2023-08-12 16:50:24', 'Đã hoàn thành'),
('ORD23009', 'E2501', 'C2024002', '2023-09-25 09:15:37', 'Đang xử lý'),
('ORD23010', 'E2505', 'C2025006', '2023-10-08 12:40:46', 'Đã hoàn thành'),
('ORD23011', 'E2507', 'C2024003', '2023-11-15 17:05:03', 'Đã hoàn thành'),
('ORD23012', 'E2512', 'C2024013', '2023-12-20 10:58:19', 'Đã hoàn thành'),
('ORD24001', 'E2501', 'C2025007', '2024-01-10 14:35:28', 'Đã hoàn thành'),
('ORD24002', 'E2505', 'C2024004', '2024-02-14 11:22:45', 'Đang xử lý'),
('ORD24003', 'E2507', 'C2024014', '2024-03-18 09:47:11', 'Đã hoàn thành'),
('ORD24004', 'E2512', 'C2025008', '2024-04-22 16:00:27', 'Đã hoàn thành'),
('ORD24005', 'E2501', 'C2024005', '2024-05-30 13:13:50', 'Đã hoàn thành'),
('ORD24006', 'E2505', 'C2024013', '2024-06-05 17:40:17', 'Đã hoàn thành'),
('ORD24007', 'E2507', 'C2025009', '2024-07-12 08:50:39', 'Đang xử lý'),
('ORD24008', 'E2512', 'C2024006', '2024-08-25 10:18:04', 'Đã hoàn thành'),
('ORD24009', 'E2501', 'C2024014', '2024-09-08 15:30:11', 'Đã hoàn thành'),
('ORD24010', 'E2505', 'C2023003', '2024-10-15 12:05:29', 'Đã hoàn thành'),
('ORD24011', 'E2507', 'C2025010', '2024-11-20 16:25:47', 'Đang xử lý'),
('ORD24012', 'E2512', 'C2024007', '2024-12-05 09:33:01', 'Đã hoàn thành'),
('ORD24013', 'E2501', 'C2024013', '2024-01-18 14:02:15', 'Đã hoàn thành'),
('ORD24014', 'E2505', 'C2024008', '2024-02-22 11:48:32', 'Đã hoàn thành'),
('ORD24015', 'E2507', 'C2024014', '2024-03-30 17:08:58', 'Đã hoàn thành'),
('ORD24016', 'E2512', 'C2025005', '2024-04-12 08:37:19', 'Đang xử lý'),
('ORD24017', 'E2501', 'C2024009', '2024-05-25 13:55:36', 'Đã hoàn thành'),
('ORD24018', 'E2505', 'C2024013', '2024-06-08 15:11:03', 'Đã hoàn thành'),
('ORD24019', 'E2507', 'C2023004', '2024-07-15 10:42:20', 'Đã hoàn thành'),
('ORD24020', 'E2512', 'C2024014', '2024-08-20 14:59:41', 'Đã hoàn thành'),
('ORD24021', 'E2501', 'C2025006', '2024-09-05 16:44:09', 'Đang xử lý'),
('ORD24022', 'E2505', 'C2024010', '2024-10-18 11:09:27', 'Đã hoàn thành'),
('ORD24023', 'E2507', 'C2024013', '2024-11-22 09:28:44', 'Đã hoàn thành'),
('ORD24024', 'E2512', 'C2023005', '2024-12-30 12:38:55', 'Đã hoàn thành');

INSERT INTO Payment (PaymentID, OrderID, PaymentMethod, PaymentDate, PaymentStatus) VALUES
('PAY23001', 'ORD23001', 'Chuyển khoản', '2023-01-15 11:25:40', 'Đã thanh toán'),
('PAY23002', 'ORD23002', 'Tiền mặt', '2023-02-20 15:10:05', 'Đã thanh toán'),
('PAY23003', 'ORD23003', 'Thẻ tín dụng', '2023-03-10 18:55:12', 'Đã thanh toán'),
('PAY23004', 'ORD23004', NULL, NULL, 'Chưa thanh toán'),
('PAY23005', 'ORD23005', 'Tiền mặt', '2023-05-18 12:30:59', 'Đã thanh toán'),
('PAY23006', 'ORD23006', 'Chuyển khoản', '2023-06-22 16:45:00', 'Đã thanh toán'),
('PAY23007', 'ORD23007', 'Thẻ tín dụng', '2023-07-30 14:20:18', 'Đã thanh toán'),
('PAY23008', 'ORD23008', 'Tiền mặt', '2023-08-12 17:50:24', 'Đã thanh toán'),
('PAY23009', 'ORD23009', NULL, NULL, 'Chưa thanh toán'),
('PAY23010', 'ORD23010', 'Chuyển khoản', '2023-10-08 13:40:46', 'Đã thanh toán'),
('PAY23011', 'ORD23011', 'Tiền mặt', '2023-11-15 18:05:03', 'Đã thanh toán'),
('PAY23012', 'ORD23012', 'Thẻ tín dụng', '2023-12-20 11:58:19', 'Đã thanh toán'),
('PAY24001', 'ORD24001', 'Tiền mặt', '2024-01-10 15:35:28', 'Đã thanh toán'),
('PAY24002', 'ORD24002', NULL, NULL, 'Chưa thanh toán'),
('PAY24003', 'ORD24003', 'Chuyển khoản', '2024-03-18 10:47:11', 'Đã thanh toán'),
('PAY24004', 'ORD24004', 'Thẻ tín dụng', '2024-04-22 17:00:27', 'Đã thanh toán'),
('PAY24005', 'ORD24005', 'Tiền mặt', '2024-05-30 14:13:50', 'Đã thanh toán'),
('PAY24006', 'ORD24006', 'Chuyển khoản', '2024-06-05 18:40:17', 'Đã thanh toán'),
('PAY24007', 'ORD24007', NULL, NULL, 'Chưa thanh toán'),
('PAY24008', 'ORD24008', 'Tiền mặt', '2024-08-25 11:18:04', 'Đã thanh toán'),
('PAY24009', 'ORD24009', 'Thẻ tín dụng', '2024-09-08 16:30:11', 'Đã thanh toán'),
('PAY24010', 'ORD24010', 'Chuyển khoản', '2024-10-15 13:05:29', 'Đã thanh toán'),
('PAY24011', 'ORD24011', NULL, NULL, 'Chưa thanh toán'),
('PAY24012', 'ORD24012', 'Tiền mặt', '2024-12-05 10:33:01', 'Đã thanh toán'),
('PAY24013', 'ORD24013', 'Thẻ tín dụng', '2024-01-18 15:02:15', 'Đã thanh toán'),
('PAY24014', 'ORD24014', 'Tiền mặt', '2024-02-22 12:48:32', 'Đã thanh toán'),
('PAY24015', 'ORD24015', 'Chuyển khoản', '2024-03-30 18:08:58', 'Đã thanh toán'),
('PAY24016', 'ORD24016', NULL, NULL, 'Chưa thanh toán'),
('PAY24017', 'ORD24017', 'Thẻ tín dụng', '2024-05-25 14:55:36', 'Đã thanh toán'),
('PAY24018', 'ORD24018', 'Tiền mặt', '2024-06-08 16:11:03', 'Đã thanh toán'),
('PAY24019', 'ORD24019', 'Chuyển khoản', '2024-07-15 11:42:20', 'Đã thanh toán'),
('PAY24020', 'ORD24020', 'Thẻ tín dụng', '2024-08-20 15:59:41', 'Đã thanh toán'),
('PAY24021', 'ORD24021', NULL, NULL, 'Chưa thanh toán'),
('PAY24022', 'ORD24022', 'Tiền mặt', '2024-10-18 12:09:27', 'Đã thanh toán'),
('PAY24023', 'ORD24023', 'Chuyển khoản', '2024-11-22 10:28:44', 'Đã thanh toán'),
('PAY24024', 'ORD24024', 'Thẻ tín dụng', '2024-12-30 13:38:55', 'Đã thanh toán');

-- Bảng 9: OrderDetail
INSERT INTO OrderDetail VALUES
-- ORD23001
('ORD23001', 'P2023001', 2),
('ORD23001', 'P2023002', 3),
('ORD23001', 'P2023005', 5),
-- ORD23002
('ORD23002', 'P2023003', 1),
('ORD23002', 'P2023015', 2),
-- ORD23003
('ORD23003', 'P2023004', 2),
('ORD23003', 'P2023007', 1),
('ORD23003', 'P2023013', 1),
('ORD23003', 'P2023018', 3),
-- ORD23004
('ORD23004', 'P2023001', 1),
-- ORD23005
('ORD23005', 'P2023006', 4),
('ORD23005', 'P2023014', 2),
-- ORD23006
('ORD23006', 'P2023008', 2),
('ORD23006', 'P2023010', 3),
('ORD23006', 'P2023016', 1),
-- ORD23007
('ORD23007', 'P2023002', 1),
-- ORD23008
('ORD23008', 'P2023004', 3),
('ORD23008', 'P2023009', 2),
('ORD23008', 'P2023011', 4),
('ORD23008', 'P2023017', 1),
('ORD23008', 'P2023019', 1),
-- ORD23009
('ORD23009', 'P2023003', 1),
('ORD23009', 'P2023012', 3),
-- ORD23010
('ORD23010', 'P2023005', 2),
('ORD23010', 'P2023008', 1),
('ORD23010', 'P2023013', 1),
('ORD23010', 'P2023020', 2),
-- ORD23011
('ORD23011', 'P2023003', 1),
-- ORD23012
('ORD23012', 'P2023001', 2),
('ORD23012', 'P2023006', 3),
('ORD23012', 'P2023015', 3),
-- ORD24001
('ORD24001', 'P2023021', 5),
('ORD24001', 'P2024002', 4),
-- ORD24002
('ORD24002', 'P2023006', 1),
-- ORD24003
('ORD24003', 'P2023004', 2),
('ORD24003', 'P2023013', 1),
('ORD24003', 'P2024003', 1),
('ORD24003', 'P2024006', 2),
-- ORD24004
('ORD24004', 'P2023007', 1),
('ORD24004', 'P2023010', 2),
('ORD24004', 'P2023018', 1),
-- ORD24005
('ORD24005', 'P2023002', 3),
('ORD24005', 'P2023012', 2),
-- ORD24006
('ORD24006', 'P2023001', 3),
('ORD24006', 'P2023005', 2),
('ORD24006', 'P2023009', 1),
('ORD24006', 'P2023014', 3),
('ORD24006', 'P2023016', 2),
-- ORD24007
('ORD24007', 'P2023008', 1),
('ORD24007', 'P2023011', 2),
-- ORD24008
('ORD24008', 'P2023007', 1),
-- ORD24009
('ORD24009', 'P2023004', 3),
('ORD24009', 'P2023013', 1),
('ORD24009', 'P2023017', 1),
('ORD24009', 'P2023019', 1),
-- ORD24010
('ORD24010', 'P2023003', 1),
('ORD24010', 'P2023015', 1),
-- ORD24011
('ORD24011', 'P2023005', 3),
('ORD24011', 'P2023008', 1),
('ORD24011', 'P2023010', 2),
-- ORD24012
('ORD24012', 'P2023005', 1),
-- ORD24013
('ORD24013', 'P2023001', 2),
('ORD24013', 'P2023006', 3),
('ORD24013', 'P2023012', 4),
('ORD24013', 'P2023015', 5),
-- ORD24014
('ORD24014', 'P2023009', 2),
('ORD24014', 'P2023014', 2),
-- ORD24015
('ORD24015', 'P2023004', 4),
('ORD24015', 'P2023007', 1),
('ORD24015', 'P2023013', 1),
('ORD24015', 'P2023017', 1),
('ORD24015', 'P2023019', 1),
-- ORD24016
('ORD24016', 'P2023008', 2),
('ORD24016', 'P2023010', 1),
('ORD24016', 'P2023018', 1),
-- ORD24017
('ORD24017', 'P2023008', 1),
-- ORD24018
('ORD24018', 'P2023002', 4),
('ORD24018', 'P2023011', 3),
-- ORD24019
('ORD24019', 'P2023010', 1),
-- ORD24020
('ORD24020', 'P2023004', 3),
('ORD24020', 'P2023013', 1),
('ORD24020', 'P2023017', 1),
('ORD24020', 'P2023019', 1),
-- ORD24021
('ORD24021', 'P2023005', 2),
('ORD24021', 'P2023009', 3),
('ORD24021', 'P2023014', 2),
-- ORD24022
('ORD24022', 'P2023003', 1),
('ORD24022', 'P2023012', 2),
-- ORD24023
('ORD24023', 'P2023001', 4),
('ORD24023', 'P2023006', 3),
('ORD24023', 'P2023009', 2),
('ORD24023', 'P2023014', 3),
('ORD24023', 'P2023016', 1),
-- ORD24024
('ORD24024', 'P2023017', 1);


-- Bảng 10: Reports - SỬA MÃ THÀNH R2501 TĂNG DẦN (ĐÃ XÓA CÁC CỘT TÍNH TOÁN)
INSERT INTO Reports (ReportID, ManagerID, StaffID, StartDate, EndDate) VALUES
-- Báo cáo tháng 1/2023
('R2501', 'E2501', 'E2502', '2023-01-01', '2023-01-31'),
-- Báo cáo tháng 2/2023
('R2502', 'E2505', 'E2503', '2023-02-01', '2023-02-28'),
-- Báo cáo tháng 3/2023
('R2503', 'E2507', 'E2504', '2023-03-01', '2023-03-31'),
-- Báo cáo tháng 4/2023
('R2504', 'E2512', 'E2506', '2023-04-01', '2023-04-30'),
-- Báo cáo tháng 5/2023
('R2505', 'E2501', 'E2502', '2023-05-01', '2023-05-31'),
-- Báo cáo tháng 6/2023
('R2506', 'E2505', 'E2503', '2023-06-01', '2023-06-30'),
-- Báo cáo tháng 7/2023
('R2507', 'E2507', 'E2504', '2023-07-01', '2023-07-31'),
-- Báo cáo tháng 8/2023
('R2508', 'E2512', 'E2506', '2023-08-01', '2023-08-31'),
-- Báo cáo tháng 9/2023
('R2509', 'E2501', 'E2502', '2023-09-01', '2023-09-30'),
-- Báo cáo tháng 10/2023
('R2510', 'E2505', 'E2503', '2023-10-01', '2023-10-31'),
-- Báo cáo tháng 11/2023
('R2511', 'E2507', 'E2504', '2023-11-01', '2023-11-30'),
-- Báo cáo tháng 12/2023
('R2512', 'E2512', 'E2506', '2023-12-01', '2023-12-31'),

-- Báo cáo tháng 1/2024
('R2513', 'E2501', 'E2508', '2024-01-01', '2024-01-31'),
-- Báo cáo tháng 2/2024
('R2514', 'E2505', 'E2509', '2024-02-01', '2024-02-29'),
-- Báo cáo tháng 3/2024
('R2515', 'E2507', 'E2510', '2024-03-01', '2024-03-31'),
-- Báo cáo tháng 4/2024
('R2516', 'E2512', 'E2511', '2024-04-01', '2024-04-30'),
-- Báo cáo tháng 5/2024
('R2517', 'E2501', 'E2513', '2024-05-01', '2024-05-31'),
-- Báo cáo tháng 6/2024
('R2518', 'E2505', 'E2514', '2024-06-01', '2024-06-30'),
-- Báo cáo tháng 7/2024
('R2519', 'E2507', 'E2515', '2024-07-01', '2024-07-31'),
-- Báo cáo tháng 8/2024
('R2520', 'E2512', 'E2506', '2024-08-01', '2024-08-31'),
-- Báo cáo tháng 9/2024
('R2521', 'E2501', 'E2502', '2024-09-01', '2024-09-30'),
-- Báo cáo tháng 10/2024
('R2522', 'E2505', 'E2503', '2024-10-01', '2024-10-31'),
-- Báo cáo tháng 11/2024
('R2523', 'E2507', 'E2504', '2024-11-01', '2024-11-30'),
-- Báo cáo tháng 12/2024
('R2524', 'E2512', 'E2506', '2024-12-01', '2024-12-31'),

-- Báo cáo quý
('R2525', 'E2501', 'E2508', '2023-02-01', '2023-02-28'),
('R2526', 'E2505', 'E2509', '2024-05-01', '2024-05-31'),
('R2527', 'E2507', 'E2510', '2024-10-01', '2024-10-31'),
('R2528', 'E2512', 'E2511', '2023-01-01', '2023-03-31'),
('R2529', 'E2501', 'E2502', '2023-04-01', '2023-06-30'),
('R2530', 'E2505', 'E2503', '2023-07-01', '2023-09-30');



