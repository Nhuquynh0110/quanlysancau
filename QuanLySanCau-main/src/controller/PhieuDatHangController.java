package controller;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.ItemModel2;
import model.PhieuDatHangModel;

public class PhieuDatHangController {

    private Connection conn;

    public PhieuDatHangController() {

    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }

    public List<ItemModel2> getItemList() {
        List<ItemModel2> itemList = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM dichvu");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ItemModel2 item = new ItemModel2(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getFloat(5));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public List<PhieuDatHangModel> getPhieuDatHangList(int maDS) {
        List<PhieuDatHangModel> phieudathangList = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn
                        .prepareStatement("SELECT * FROM phieudathang WHERE MaDS = ?")) {
            preparedStatement.setInt(1, maDS);
            try (ResultSet resultSet1 = preparedStatement.executeQuery()) {
                while (resultSet1.next()) {
                    PhieuDatHangModel phieudathang = new PhieuDatHangModel(resultSet1.getInt(1), resultSet1.getInt(2),
                            resultSet1.getString(3), resultSet1.getFloat(4), resultSet1.getInt(5),
                            resultSet1.getFloat(6));
                    phieudathangList.add(phieudathang);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieudathangList;
    }

    public void addPhieuDatHang(int maDS, int maDV, String tenDV, float donGia, int soLuong, float thanhTien) {
        conn = ConnectDB.getConnection();
        String sql = "INSERT INTO phieudathang (MaDS,MaDV, TenDV,Gia, SoLuong, ThanhTien) VALUES (?,?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maDS);
            stmt.setInt(2, maDV);
            stmt.setString(3, tenDV);
            stmt.setFloat(4, donGia);
            stmt.setInt(5, soLuong);
            stmt.setFloat(6, thanhTien);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("dich vu duoc order thành công!");
            } else {
                System.out.println("dich vu order thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updatePhieuDatHang(int maDV, int soLuongMoi, float thanhTienMoi, int madatsan) {
        conn = ConnectDB.getConnection();
        String sql = "UPDATE phieudathang SET SoLuong = ?, ThanhTien = ? WHERE MaDV = ? AND MaDS = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, soLuongMoi);
            stmt.setFloat(2, thanhTienMoi);
            stmt.setInt(3, maDV);
            stmt.setInt(4, madatsan);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật phiếu đặt hàng thành công!");
            } else {
                System.out.println("Cập nhật phiếu đặt hàng thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deletePhieuDatHang(int madv, int madatsan) {
        conn = ConnectDB.getConnection();
        String sql = "DELETE FROM phieudathang WHERE MaDV = ? AND MaDS = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, madv);
            stmt.setInt(2, madatsan);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không có dữ liệu nào được xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPhieuDatHang(int madatsan) {
        conn = ConnectDB.getConnection();
        try {
            // Xóa toàn bộ dữ liệu phiếu đặt hàng từ cơ sở dữ liệu
            String sql = "DELETE FROM phieudathang WHERE maDS = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, madatsan);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getMaDSFromDatabase(int maDS, JTextField maDSField) {
        // Kết nối tới cơ sở dữ liệu
        try (Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn
                        .prepareStatement("SELECT * FROM danhsachdatsan WHERE MaDS = ?")) {
            preparedStatement.setInt(1, maDS); // Thiết lập giá trị của tham số MaDS trong câu lệnh SQL
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Tim thay ma dat san trong co so du lieu: " + maDS);
                    maDSField.setText(Integer.toString(maDS)); // Hiển thị mã đặt sân lên ô text
                } else {
                    System.out.println("Khong tim thay ma dat san: " + maDS);
                    maDSField.setText("Không tìm thấy");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
