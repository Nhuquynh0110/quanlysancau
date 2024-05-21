/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Database.DB;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.SanModel;
import view.SanView;

/**
 *
 * @author Windows
 */
public class SanController {

    private Connection conn;

    public SanController() {

    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);

    }

    public List<SanModel> getSanList() {
        List<SanModel> sanList = new ArrayList<>();
        try (Connection conn = DB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM san"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SanModel s = new SanModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getFloat(4));
                sanList.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanList;
    }

    public void addsan(SanModel s) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();
            String sql = "INSERT INTO san(TenSan, LoaiSan,GiaSan)"
                    + "VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getTenSan());
            stmt.setString(2, s.getLoaiSan());
            stmt.setFloat(3, s.getGiaSan());
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

    public void deletesan(int sanMa) {
        conn = DB.getConnection();
        String sql = "DELETE FROM san WHERE MaSan = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, (int) sanMa);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatesan(SanModel s) {
        conn = DB.getConnection();
        String sql = "UPDATE san SET TenSan=?, LoaiSan=?, GiaSan=? WHERE MaSan=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getTenSan());
            stmt.setString(2, s.getLoaiSan());
            stmt.setFloat(3, s.getGiaSan());
            stmt.setInt(4, s.getMaSan()); // Chú ý thứ tự của các tham số
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

//    public List<San> findSan(String sql, String a) {
//        List<San> SannList = new ArrayList<>();
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
//                San s = new San(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
//                SannList.add(s);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return SannList;
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SanView::new);
    }

}
