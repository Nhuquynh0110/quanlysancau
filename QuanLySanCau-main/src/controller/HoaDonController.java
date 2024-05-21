package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.HoaDonModel;

public class HoaDonController {
    // Phương thức để lấy tất cả các hóa đơn từ cơ sở dữ liệu
    public ArrayList<HoaDonModel> getAllHoadons() {
        ArrayList<HoaDonModel> hoadons = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sancau"; // URL của cơ sở dữ liệu
        String user = "root"; // Tên người dùng
        String password = ""; // Mật khẩu

        try {
            // Kết nối cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM hoadon";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Lặp qua kết quả và thêm vào danh sách hoadons
            while (rs.next()) {
                HoaDonModel hoadon = new HoaDonModel();
                hoadon.setMaDS(rs.getString("MaDS"));
                hoadon.setMaKH(rs.getString("MaKH"));
                hoadon.setTongTienSan(rs.getString("TongTienSan"));
                hoadon.setTongTienDV(rs.getString("TongTienDV"));
                hoadon.setTongTien(rs.getString("TongTien"));

                hoadons.add(hoadon);
            }

            // Đóng kết nối
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return hoadons;
    }

    public void insertDataFromDanhsachdatsanToHoadon() {
        String url = "jdbc:mysql://localhost:3306/sancau";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String delSql = "DELETE FROM hoadon";
            PreparedStatement delStatement = conn.prepareStatement(delSql);
            delStatement.executeUpdate();
            // Thêm dữ liệu mới từ bảng danhsachdatsan vào bảng hoadon
            String insertSql = "INSERT INTO hoadon (MaDS, MaKH, TongTienSan, TongTienDV, TongTien) " +
                    "SELECT ds.MaDS, ds.MaKH, ds.TongTienSan, ds.TongTienDV, (ds.TongTienSan + ds.TongTienDV) AS TongTien "
                    +
                    "FROM danhsachdatsan ds";
            PreparedStatement insertStatement = conn.prepareStatement(insertSql);

            // Thực thi truy vấn cập nhật
            int rowsUpdated = insertStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Chèn dữ liệu từ bảng danhsachdatsan vào bảng hoadon thành công.");
            } else {
                System.out.println("Không có dữ liệu mới từ bảng danhsachdatsan được chèn vào bảng hoadon.");
            }

            // Đóng kết nối
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để cập nhật dữ liệu từ bảng phieudathang vào bảng chitiethoadon
    public void updateChiTietHoaDonFromPhieuDatHang(int maDS) {
        String url = "jdbc:mysql://localhost:3306/sancau"; // URL của cơ sở dữ liệu
        String user = "root"; // Tên người dùng
        String password = ""; // Mật khẩu

        try {
            // Kết nối cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(url, user, password);

            // Kiểm tra xem hóa đơn đã tồn tại trong bảng chitiethoadon hay chưa
            String checkSql = "SELECT * FROM chitiethoadon WHERE MaDS = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkSql);
            checkStatement.setInt(1, maDS);
            ResultSet checkResult = checkStatement.executeQuery();

            if (!checkResult.next()) {
                // Thêm dữ liệu mới từ bảng phieudathang vào bảng chitiethoadon
                String insertSql = "INSERT INTO chitiethoadon (MaDS, MaSan, MaDV, MaKH, TenKH, SDT, TenSan, SoGioThue, GiaSan, TenDV, SoLuong, Gia, TongTienSan, TongTienDV, TongTien, NgayLap, TrangThai) "
                        +
                        "SELECT ds.MaDS, ds.MaSan, pd.MaDV, ds.MaKH, kh.TenKH, kh.SDT, s.TenSan, ds.SoGioThue, s.GiaSan, pd.TenDV, pd.SoLuong, pd.Gia, ds.TongTienSan, ds.TongTienDV, (ds.TongTienSan + ds.TongTienDV) AS TongTien, CURRENT_DATE(), 'chưa thanh toán' "
                        +
                        "FROM danhsachdatsan ds " +
                        "INNER JOIN san s ON ds.MaSan = s.MaSan " +
                        "INNER JOIN khachhang kh ON ds.MaKH = kh.MaKH " +
                        "INNER JOIN phieudathang pd ON ds.MaDS = pd.MaDS " +
                        "WHERE ds.MaDS = ?";
                PreparedStatement insertStatement = conn.prepareStatement(insertSql);
                insertStatement.setInt(1, maDS);

                // Thực thi truy vấn cập nhật
                int rowsUpdated = insertStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Cập nhật dữ liệu từ bảng phieudathang vào bảng chitiethoadon thành công.");
                } else {
                    System.out.println("Không có dữ liệu mới từ bảng phieudathang được cập nhật.");
                }
            } else {
                System.out.println("Hóa đơn đã tồn tại trong bảng chitiethoadon.");
            }

            // Đóng kết nối
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Phương thức để lấy chi tiết hóa đơn dựa trên mã đặt sân
    public ArrayList<HoaDonModel> getChiTietHoaDonByMaDS(int maDS) {
        ArrayList<HoaDonModel> chiTietHoaDons = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sancau"; // URL của cơ sở dữ liệu
        String user = "root"; // Tên người dùng
        String password = ""; // Mật khẩu
        Connection conn = null;

        try {
            // Kết nối cơ sở dữ liệu
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM chitiethoadon WHERE MaDS = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, maDS);
            ResultSet rs = statement.executeQuery();

            // Lặp qua kết quả và thêm vào danh sách chiTietHoaDons
            while (rs.next()) {
                HoaDonModel chiTietHoaDon = new HoaDonModel();
                // Thiết lập các giá trị của chi tiết hóa đơn từ ResultSet
                chiTietHoaDon.setMaDS(rs.getString("MaDS"));
                chiTietHoaDon.setMaKH(rs.getString("MaKH"));
                chiTietHoaDon.setMaDV(rs.getString("MaDV"));
                chiTietHoaDon.setMaSan(rs.getString("MaSan"));
                chiTietHoaDon.setTenKH(rs.getString("TenKH"));
                chiTietHoaDon.setSDT(rs.getString("SDT"));
                chiTietHoaDon.setTenSan(rs.getString("TenSan"));
                chiTietHoaDon.setSoGioThue(rs.getString("SoGioThue"));
                chiTietHoaDon.setGiaSan(rs.getString("GiaSan"));
                chiTietHoaDon.setTenDV(rs.getString("TenDV"));
                chiTietHoaDon.setSoLuong(rs.getString("SoLuong"));
                chiTietHoaDon.setGia(rs.getString("Gia"));
                chiTietHoaDon.setTongTienSan(rs.getString("TongTienSan"));
                chiTietHoaDon.setTongTienDV(rs.getString("TongTienDV"));
                chiTietHoaDon.setTongTien(rs.getString("TongTien"));
                chiTietHoaDon.setNgayLap(rs.getString("NgayLap"));
                chiTietHoaDon.setTrangThai(rs.getString("TrangThai"));

                chiTietHoaDons.add(chiTietHoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Đóng kết nối sau khi sử dụng
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return chiTietHoaDons;
    }

    // Phương thức để cập nhật trạng thái của hóa đơn
    public void updateTrangThaiHoaDon(String maDS, String trangThai) {
        String url = "jdbc:mysql://localhost:3306/sancau"; // URL của cơ sở dữ liệu
        String user = "root"; // Tên người dùng
        String password = ""; // Mật khẩu

        try {
            // Kết nối cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(url, user, password);

            // Cập nhật trạng thái hóa đơn trong bảng chitiethoadon
            String updateSql = "UPDATE chitiethoadon SET TrangThai = ? WHERE MaDS = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateSql);
            updateStatement.setString(1, trangThai);
            updateStatement.setString(2, maDS);

            // Thực thi truy vấn cập nhật
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật trạng thái hóa đơn thành công.");
            } else {
                System.out.println("Không có hóa đơn nào được cập nhật.");
            }

            // Đóng kết nối
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}