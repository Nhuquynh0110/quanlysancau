package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaoCaoController {
    public DefaultTableModel getAllChiTietHoaDons() {
        DefaultTableModel model = new DefaultTableModel();

        // Thêm các cột vào bảng
        model.addColumn("Mã đặt sân");
        model.addColumn("Mã sân");
        model.addColumn("Mã dịch vụ");
        model.addColumn("Mã khách hàng");
        model.addColumn("Tên khách hàng");
        model.addColumn("Số điện thoại");
        model.addColumn("Tên sân");
        model.addColumn("Số giờ thuê");
        model.addColumn("Giá sân");
        model.addColumn("Tên dịch vụ");
        model.addColumn("Số lượng");
        model.addColumn("Giá");  
        model.addColumn("Tổng tiền sân");
        model.addColumn("Tổng tiền dịch vụ");
        model.addColumn("Tổng tiền");
        model.addColumn("Ngày lập");
        model.addColumn("Trạng thái");

        // Kết nối đến cơ sở dữ liệu và truy vấn dữ liệu từ bảng "chitiethoadon"
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sancau", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM chitiethoadon")) {
            
            // Duyệt qua các dòng kết quả và thêm vào bảng
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("maDS"),
                        rs.getString("maSan"),
                        rs.getString("maDV"),        
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("SDT"),
                        rs.getString("tenSan"),
                        rs.getString("soGioThue"),
                        rs.getString("giaSan"),
                        rs.getString("tenDV"),
                        rs.getString("soLuong"),
                        rs.getString("Gia"),
                        rs.getString("tongTienSan"),
                        rs.getString("tongTienDV"),
                        rs.getString("tongTien"),
                        rs.getString("ngayLap"),
                        rs.getString("trangThai")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }
}