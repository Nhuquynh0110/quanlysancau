package controller;

import Database.ConnectDB;
import model.ItemModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

public class ItemController {

    private Connection conn;

    public ItemController() {
    }

    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);

    }

    public List<ItemModel> getItemList() {
        List<ItemModel> itemList = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM dichvu"); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ItemModel item = new ItemModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getFloat(5));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public void addItem(ItemModel item) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectDB.getConnection();
            String sql = "INSERT INTO  dichvu(TenDV, LoaiDV, DVT, Gia)"
                    + "VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getNameItem());
            stmt.setString(2, item.getTypeItem());
            stmt.setString(3, item.getUnitItem());
            stmt.setFloat(4, item.getPriceItem());
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

    public void deleteItem(int itemId) {
        conn = ConnectDB.getConnection();
        String sql = "DELETE FROM dichvu WHERE MaDV = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemId);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateItem(ItemModel item) {
        conn = ConnectDB.getConnection();
        String sql = "UPDATE dichvu SET TenDV=?, LoaiDV=?, DVT=?, Gia=? WHERE MaDV=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getNameItem());
            stmt.setString(2, item.getTypeItem());
            stmt.setString(3, item.getUnitItem());
            stmt.setFloat(4, item.getPriceItem());
            stmt.setInt(5, item.getIdItem()); // MaDv đang là tham số thứ 5 trong câu lệnh sql có prepareStatement( sẽ xếp theo thứu tự các thành phần có trong câu lệnh);
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

    public List<ItemModel> findItemBy(String sql, String a) {
        List<ItemModel> itemlList = new ArrayList<>();
        conn = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + a + "%");
            stmt.execute();
            rs = stmt.executeQuery();
            while (rs.next()) {
//				
                ItemModel item = new ItemModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
                itemlList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemlList;
    }

   
}
//Lớp Main chịu trách nhiệm về điều khiển logic của ứng dụng,
//bao gồm việc thêm, sửa, xoá và tìm kiếm các mục trong cơ sở dữ liệu.