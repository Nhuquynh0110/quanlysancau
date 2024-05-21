package controller;

import Database.DB;
import view.KhachHangView;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import model.KhachHangModel;

public class KhachHangController {

    private Connection conn;

    public KhachHangController() {
    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);

    }

    public List<KhachHangModel> getKhachHangList() {
        List<KhachHangModel> khachhangList = new ArrayList<>();
        try (Connection conn = DB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM khachhang"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                KhachHangModel kh = new KhachHangModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));
                khachhangList.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachhangList;
    }

    public void addKH(KhachHangModel kh) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();
            String sql = "INSERT INTO khachhang (TenKH, SDT)" + "VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSDT());
//            stmt.setFloat(3, kh.getSoLanDat());
            // Thực thisetFloat câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletesan(int maKH) {
        conn = DB.getConnection();
        String sql = "DELETE FROM khachhang WHERE MaKH = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (int) maKH);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatesan(KhachHangModel kh) {
        conn = DB.getConnection();
        String sql = "UPDATE khachhang SET TenKH=?, SDT=?, SoLanDat=? WHERE MaKH=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSDT());
            stmt.setInt(3, kh.getSoLanDat());
            stmt.setInt(4, kh.getMaKH()); // Chú ý thứ tự của các tham số
            stmt.executeUpdate();
            int rowsUpdated = stmt.executeUpdate();
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update thành công!");
            } else {
                System.out.println("Update thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
     public void CapNhatSoLanDat() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();
            String sql = "UPDATE khachhang SET SoLanDat = (SELECT COUNT(MaDS) FROM danhsachdatsan WHERE khachhang.MaKH = danhsachdatsan.MaKH)";
            stmt = conn.prepareStatement(sql);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                  System.out.println("Cập nhật số lần đặt thành công!");
            } else {
                System.out.println("Cập nhật số lần đặt thất bại!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//    public List<KhachHang> findKH(String sql, String a) {
//        List<KhachHang> KHList = new ArrayList<>();
//        conn = DB.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, "%" + a + "%");
//            stmt.execute();
//            rs = stmt.executeQuery();
//            while (rs.next()) {
////				
//                KhachHang kh = new KhachHang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
//                KHList.add(kh);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return KHList;
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(KhachHangView::new);
    }

}
