package view;

import java.util.regex.*;

import controller.DatSanController;
import controller.PhieuDatHangController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItemModel2;
import model.PhieuDatHangModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PhieuDatHangView extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/sancau";
    private static final String USER = "root";
    private static final String PASS = "";

    private int madatsan = 0;
    private JTable menuTable, orderTable;
    private JButton themButton, xoaButton, timKiemButton, trongButton, tongTienButton;
    private DefaultTableModel menuTableModel, orderTableModel;
    private java.util.List<ItemModel2> listItem = new ArrayList<>();
    private java.util.List<PhieuDatHangModel> listPhieuDatHang = new ArrayList<>();
    private JScrollPane menuScrollPane, orderScrollPane;

    int itemId;
    // int madatsan=1 ;//giả sử đang thêm dịch vụ của mã đặt sân 1

    public PhieuDatHangView(int madatsan) {
        this.madatsan = madatsan;
        try {
            formPhieuDatHang();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo giao diện: " + ex.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void formPhieuDatHang() throws SQLException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create menu table
        menuTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Mã dịch vụ", "Tên dịch vụ", "Loại dịch vụ", "Đơn vị tính", "Giá" });
        menuTable = new JTable(menuTableModel);
        menuScrollPane = new JScrollPane(menuTable);
        menuScrollPane.setPreferredSize(new Dimension(400, 300)); // Đặt kích thước cho menuScrollPane
        menuTable.getTableHeader().setReorderingAllowed(false);// không cho sửa các tiêu đề cột
        menuTable.setDefaultEditor(Object.class, null);// không cho sửa dữ liệu trong bảng

        // Create order table
        orderTableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Id sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Thành tiền" });
        orderTable = new JTable(orderTableModel);
        orderScrollPane = new JScrollPane(orderTable);
        orderScrollPane.setPreferredSize(new Dimension(360, 300));
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTable.setDefaultEditor(Object.class, null);
        // Create buttons
        themButton = new JButton("Thêm");
        xoaButton = new JButton("Xoá");
        trongButton = new JButton("Trống");
        tongTienButton = new JButton("Tính tiền");
        timKiemButton = new JButton("Tìm kiếm tên/loại");

        // Create labels and text fields
        JLabel maDSLabel = new JLabel("Mã đặt sân");
        JTextField maDSField = new JTextField(10);
        maDSField.setEditable(false);
        JTextField timKiemField = new JTextField(25);
        timKiemField.setPreferredSize(new Dimension(200, 25));
        JLabel tongTienLabel = new JLabel("Tổng tiền:");
        JTextField tongTienField = new JTextField(10);
        tongTienField.setText("0");
        JLabel nameJLabel = new JLabel("PHIẾU ĐẶT HÀNG");
        Font font = new Font(nameJLabel.getFont().getName(), Font.BOLD, 25); // Đặt font chữ
        nameJLabel.setFont(font); // Áp dụng font cho JLabel

        // Create main panel
        JPanel mainPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);

        // Add components to main panel with SpringLayout
        mainPanel.add(menuScrollPane);
        mainPanel.add(orderScrollPane);

        mainPanel.add(themButton);
        mainPanel.add(xoaButton);
        mainPanel.add(trongButton);
        mainPanel.add(tongTienButton);

        mainPanel.add(maDSLabel);
        mainPanel.add(maDSField);
        mainPanel.add(timKiemButton);
        mainPanel.add(timKiemField);
        mainPanel.add(tongTienLabel);
        mainPanel.add(tongTienField);
        mainPanel.add(nameJLabel);

        // lbl name form
        layout.putConstraint(SpringLayout.NORTH, nameJLabel, 8, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, nameJLabel, 300, SpringLayout.NORTH, mainPanel);

        // Define constraints for menuScrollPane
        layout.putConstraint(SpringLayout.NORTH, menuScrollPane, 100, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, menuScrollPane, 10, SpringLayout.WEST, mainPanel);

        // Define constraints for orderScrollPane
        layout.putConstraint(SpringLayout.NORTH, orderScrollPane, 100, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, orderScrollPane, 10, SpringLayout.EAST, menuScrollPane);

        // Define constraints for buttons
        layout.putConstraint(SpringLayout.NORTH, themButton, 60, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, themButton, 440, SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, xoaButton, 60, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, xoaButton, 20, SpringLayout.EAST, themButton);

        layout.putConstraint(SpringLayout.NORTH, trongButton, 60, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, trongButton, 20, SpringLayout.EAST, xoaButton);

        layout.putConstraint(SpringLayout.NORTH, tongTienButton, 60, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, tongTienButton, 20, SpringLayout.EAST, trongButton);

        layout.putConstraint(SpringLayout.NORTH, timKiemButton, 60, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, timKiemButton, 10, SpringLayout.WEST, mainPanel);

        // Define constraints for labels and text fields
        layout.putConstraint(SpringLayout.NORTH, maDSLabel, 35, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, maDSLabel, 10, SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, maDSField, 35, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, maDSField, 80, SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, timKiemField, 25, SpringLayout.NORTH, maDSField);
        layout.putConstraint(SpringLayout.WEST, timKiemField, 150, SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, tongTienLabel, 10, SpringLayout.SOUTH, orderScrollPane);
        layout.putConstraint(SpringLayout.WEST, tongTienLabel, 600, SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, tongTienField, 10, SpringLayout.SOUTH, orderScrollPane);
        layout.putConstraint(SpringLayout.WEST, tongTienField, 10, SpringLayout.EAST, tongTienLabel);

        // Add main panel to JFrame
        add(mainPanel);
        DefaultTableModel model = (DefaultTableModel) ((JTable) menuScrollPane.getViewport().getView()).getModel();
        DefaultTableModel model1 = (DefaultTableModel) ((JTable) orderScrollPane.getViewport().getView()).getModel();
        PhieuDatHangController controller = new PhieuDatHangController();
        listItem = controller.getItemList();
        this.itemList(listItem);

        listPhieuDatHang = controller.getPhieuDatHangList(madatsan);
        this.phieudathangList(listPhieuDatHang);

        setTitle("Form phiếu đặt hàng");
        setSize(800, 500);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        controller.getMaDSFromDatabase(madatsan, maDSField);

        controller.setButtonListener(timKiemButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = timKiemField.getText().trim().toLowerCase(); // Chuyển từ khóa tìm kiếm thành chữ
                                                                              // thường

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                java.util.List<ItemModel2> searchResult = new ArrayList<>();

                // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong
                // sách hay không
                for (ItemModel2 item : listItem) {
                    String itemNameLower = item.getNameItem().toLowerCase();
                    String itemTypeLower = item.getTypeItem().toLowerCase();

                    if (itemNameLower.contains(keyword) || itemTypeLower.contains(keyword)) {
                        // Nếu tìm thấy từ khóa trong tên sách hoặc loại sách, thêm sách vào danh sách
                        // kết quả
                        searchResult.add(item);
                    }
                }
                // Hiển thị kết quả tìm kiếm trên bảng
                itemList(searchResult);
            }
        });

        controller.setButtonListener(themButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy chỉ số hàng được chọn trong bảng menu
                int selectedRow = menuTable.getSelectedRow();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRow != -1) {
                    // Lấy thông tin của sản phẩm từ hàng được chọn
                    int madv = (int) menuTable.getValueAt(selectedRow, 0); // ID sản phẩm
                    String ten = (String) menuTable.getValueAt(selectedRow, 1); // Tên sản phẩm
                    float gia = (float) menuTable.getValueAt(selectedRow, 4); // Giá sản phẩm
                    int soLuong = 1; // Số lượng mặc định là 1
                    float thanhTien = gia * soLuong; // Tính toán thành tiền

                    // Kiểm tra xem sản phẩm đã tồn tại trong bảng order chưa
                    boolean itemExists = false;
                    for (int i = 0; i < orderTable.getRowCount(); i++) {
                        int idOrder = (int) orderTable.getValueAt(i, 0);
                        if (madv == idOrder) {
                            itemExists = true;
                            break;
                        }
                    }

                    if (!itemExists) {
                        // Tạo một mảng chứa thông tin sản phẩm để thêm vào bảng order
                        Object[] rowData = { madv, ten, gia, soLuong, thanhTien };
                        orderTableModel.addRow(rowData);

                        // Thêm phiếu đặt hàng vào cơ sở dữ liệu
                        controller.addPhieuDatHang(madatsan, madv, ten, gia, soLuong, thanhTien);
                    } else {
                        // Hiển thị thông báo rằng sản phẩm đã tồn tại trong bảng order
                        JOptionPane.showMessageDialog(PhieuDatHangView.this, "Sản phẩm đã tồn tại trong đơn hàng!");
                    }
                    listPhieuDatHang = controller.getPhieuDatHangList(madatsan);
                    phieudathangList(listPhieuDatHang);
                }
            }
        });

        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra xem có phải là kích đúp không
                    int row = orderTable.getSelectedRow();

                    if (row >= 0) {
                        // Lấy thông tin sản phẩm từ bảng
                        int maDV = Integer.parseInt(orderTable.getValueAt(row, 0).toString());
                        int soLuongHienTai = Integer.parseInt(orderTable.getValueAt(row, 3).toString());

                        // Tạo form nhỏ cho phép chỉnh sửa số lượng
                        JTextField txtSoLuongMoi = new JTextField(Integer.toString(soLuongHienTai), 10);
                        JPanel panel = new JPanel();
                        panel.add(new JLabel("Số lượng mới:"));
                        panel.add(txtSoLuongMoi);

                        int result = JOptionPane.showConfirmDialog(PhieuDatHangView.this, panel, "Chỉnh sửa số lượng",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        // Xử lý khi người dùng xác nhận chỉnh sửa
                        if (result == JOptionPane.OK_OPTION) {
                            int soLuongMoi = Integer.parseInt(txtSoLuongMoi.getText());

                            if (soLuongMoi == 0) {
                                // Xoá hàng khỏi bảng
                                DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
                                model.removeRow(row);

                                // Xoá hàng khỏi cơ sở dữ liệu
                                controller.deletePhieuDatHang(maDV, madatsan); // Thêm mã đặt sân

                                // Cập nhật lại hiển thị của bảng
                                listPhieuDatHang = controller.getPhieuDatHangList(madatsan);
                                phieudathangList(listPhieuDatHang);
                            } else {
                                // Cập nhật số lượng trong bảng
                                orderTable.setValueAt(soLuongMoi, row, 3); // Cột số lượng là cột thứ 3 (0-based index)

                                // Cập nhật số lượng trong cơ sở dữ liệu
                                float gia = Float.parseFloat(orderTable.getValueAt(row, 2).toString());
                                float thanhTienMoi = gia * soLuongMoi;
                                controller.updatePhieuDatHang(maDV, soLuongMoi, thanhTienMoi, madatsan); // Thêm mã đặt
                                                                                                         // sân

                                // Cập nhật lại hiển thị của bảng
                                listPhieuDatHang = controller.getPhieuDatHangList(madatsan);
                                phieudathangList(listPhieuDatHang);
                            }
                        }
                    }
                }
            }
        });

        controller.setButtonListener(xoaButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = orderTable.getSelectedRow(); // Lấy chỉ số hàng được chọn trong bảng order
                if (selectedRow != -1) {
                    // Hiển thị hộp thoại xác nhận xóa
                    int confirm = JOptionPane.showConfirmDialog(PhieuDatHangView.this,
                            "Bạn có chắc chắn muốn xóa hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int madv = (int) orderTable.getValueAt(selectedRow, 0); // Lấy ID của mặt hàng từ hàng được chọn
                        controller.deletePhieuDatHang(madv, madatsan); // Gọi phương thức delete từ
                                                                       // PhieuDatHangController với thêm mã đặt sân
                        System.out.println("Xóa thành công!");
                        // Cập nhật lại bảng order để hiển thị dữ liệu mới
                        listPhieuDatHang = controller.getPhieuDatHangList(madatsan);
                        phieudathangList(listPhieuDatHang);
                        System.out.println("Cập nhật");
                    }
                } else {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo cho người dùng
                    JOptionPane.showMessageDialog(PhieuDatHangView.this, "Vui lòng chọn một hàng để xóa!");
                }
            }
        });

        controller.setButtonListener(trongButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá toàn bộ dữ liệu không?",
                        "Xác nhận xoá", JOptionPane.YES_NO_OPTION);

                // Kiểm tra lựa chọn của người dùng
                if (option == JOptionPane.YES_OPTION) {
                    // Người dùng đã chọn "Yes", gọi phương thức xử lý xoá toàn bộ dữ liệu từ
                    // controller
                    controller.deleteAllPhieuDatHang(madatsan);

                    // Cập nhật lại hiển thị của bảng orderTable
                    listPhieuDatHang.clear(); // Xóa toàn bộ dữ liệu hiện có
                    phieudathangList(listPhieuDatHang); // Cập nhật hiển thị trên bảng
                    tongTienField.setText("0");
                    // Hiển thị thông báo xoá thành công
                    JOptionPane.showMessageDialog(null, "Xoá toàn bộ dữ liệu phiếu đặt hàng thành công!");
                }
            }
        });

        // tính tổng tiền
        controller.setButtonListener(tongTienButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float tongTien = 0;
                for (int row = 0; row < orderTable.getRowCount(); row++) {
                    float thanhTien = (float) orderTable.getValueAt(row, 4);
                    tongTien += thanhTien;
                }
                tongTienField.setText(String.valueOf(tongTien));
                System.out.println("da tinh tong tien!");

                // Kết nối tới cơ sở dữ liệu và cập nhật giá trị tổng tiền dịch vụ
                try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                    String updateQuery = "UPDATE danhsachdatsan SET TongTienDV = ? WHERE MaDS = '" + madatsan + "'";
                    
                    PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                    updateStatement.setFloat(1, tongTien);

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Đã cập nhật tổng tiền dịch vụ vào cơ sở dữ liệu.");
                    } else {
                        System.out.println("Không có dòng nào được cập nhật.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tổng tiền dịch vụ: " + ex.getMessage());
                }
                refreshTable();
            }
        });

        // Thêm listener cho cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(PhieuDatHangView.this,
                        " Đừng quên bấm tính tiền trước khi thoát. Bạn chắc chắn muốn thoát?", "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                } else {
                    // Ngăn chặn cửa sổ đóng lại
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void itemList(java.util.List<ItemModel2> updatedItemList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) menuScrollPane.getViewport().getView()).getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        // Thêm dữ liệu mới từ danh sách updatedItemList vào bảng
        for (ItemModel2 item : updatedItemList) {
            model.addRow(new Object[] {
                    item.getIdItem(),
                    item.getNameItem(),
                    item.getTypeItem(),
                    item.getUnitItem(),
                    item.getPriceItem()
            });
        }
    }

    private void phieudathangList(java.util.List<PhieuDatHangModel> updatedPhieuDatHangList) {
        DefaultTableModel model1 = (DefaultTableModel) ((JTable) orderScrollPane.getViewport().getView()).getModel();
        model1.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        // Thêm dữ liệu mới từ danh sách updatedItemList vào bảng
        for (PhieuDatHangModel phieudathang : updatedPhieuDatHangList) {
            model1.addRow(new Object[] {
                    phieudathang.getMaDV(),
                    phieudathang.getTenDV(),
                    phieudathang.getDonGia(),
                    phieudathang.getSoLuong(),
                    phieudathang.getThanhTien(), });
        }
    }

    private void refreshTable() {
        DatSanController.displayData(); // Gọi lại phương thức hiển thị dữ liệu để làm mới bảng
    }
}
