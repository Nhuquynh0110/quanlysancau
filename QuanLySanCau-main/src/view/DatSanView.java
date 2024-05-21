package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

public class DatSanView extends MainMenuView {
    private String URL = "jdbc:mysql://localhost:3306/sancau";
    private String USER = "root";
    private String PASS = "";

    public JTextField txtMaDS, txtMaKH, txtMaSan, txtSoGioThue, txtTongTienSan;
    public JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    public SpinnerDateModel dateModelBatDau, dateModelKetThuc, timeModelBatDau, timeModelKetThuc;
    public JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    public JComboBox<String> cmbLoaiSan;
    public JButton btnAdd, btnEdit, btnDelete, btnCancel, btnSave, btnExcel, btnSearch, btnPhieuDonHang;
    public JTable dataTable;

    public DatSanView() {
        setTitle("Quản lý đặt sân");
        setSize(1200, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel chứa các thành phần nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel header = new JLabel("QUẢN LÝ");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel header2 = new JLabel("ĐẶT SÂN");
        header2.setHorizontalAlignment(JLabel.CENTER);
        header2.setFont(new Font("Arial", Font.BOLD, 24));
        // header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(header, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(header2, gbc);

        // Label và TextField cho Mã DS
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Mã DS:"), gbc);
        txtMaDS = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(txtMaDS, gbc);

        // Label và TextField cho Mã KH
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Mã KH:"), gbc);
        txtMaKH = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(txtMaKH, gbc);

        // Label và TextField cho Mã Sân
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Mã Sân:"), gbc);
        txtMaSan = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(txtMaSan, gbc);

        // Label và TextField cho Loại Sân
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Loại Sân:"), gbc);
        cmbLoaiSan = new JComboBox<>();
        loadLoaiSanData();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(cmbLoaiSan, gbc);

        // Label và TextField cho Ngày Bắt Đầu
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Ngày Bắt Đầu:"), gbc);
        dateModelBatDau = new SpinnerDateModel();
        dateModelBatDau.setCalendarField(Calendar.DAY_OF_MONTH);
        spNgayBatDau = new JSpinner(dateModelBatDau);
        spNgayBatDau.setEditor(new JSpinner.DateEditor(spNgayBatDau, "dd/MM/yyyy"));
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(spNgayBatDau, gbc);

        // Label và TextField cho Ngày Kết Thúc
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Ngày Kết Thúc:"), gbc);
        dateModelKetThuc = new SpinnerDateModel();
        dateModelKetThuc.setCalendarField(Calendar.DAY_OF_MONTH);
        spNgayKetThuc = new JSpinner(dateModelKetThuc);
        spNgayKetThuc.setEditor(new JSpinner.DateEditor(spNgayKetThuc, "dd/MM/yyyy"));
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(spNgayKetThuc, gbc);

        // Label và TextField cho Giờ Bắt Đầu
        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Giờ Bắt Đầu:"), gbc);
        timeModelBatDau = new SpinnerDateModel();
        timeModelBatDau.setCalendarField(Calendar.MINUTE); // Set the calendar field to MINUTE
        spGioBatDau = new JSpinner(timeModelBatDau);
        JSpinner.DateEditor gioBatDau = new JSpinner.DateEditor(spGioBatDau, "HH:mm");
        spGioBatDau.setEditor(gioBatDau);
        gbc.gridx = 1;
        gbc.gridy = 7;
        inputPanel.add(spGioBatDau, gbc);

        // Label và TextField cho Giờ Kết Thúc
        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("Giờ Kết Thúc:"), gbc);
        timeModelKetThuc = new SpinnerDateModel();
        timeModelKetThuc.setCalendarField(Calendar.MINUTE); // Set the calendar field to MINUTE
        spGioKetThuc = new JSpinner(timeModelKetThuc);
        JSpinner.DateEditor gioKetThuc = new JSpinner.DateEditor(spGioKetThuc, "HH:mm");
        spGioKetThuc.setEditor(gioKetThuc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        inputPanel.add(spGioKetThuc, gbc);

        // Label và TextField cho Số Giờ Thuê
        gbc.gridx = 0;
        gbc.gridy = 9;
        inputPanel.add(new JLabel("Số Giờ Thuê:"), gbc);
        txtSoGioThue = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 9;
        inputPanel.add(txtSoGioThue, gbc);

        // Label và TextField cho Tổng tiền sân
        gbc.gridx = 0;
        gbc.gridy = 10;
        inputPanel.add(new JLabel("Tổng tiền sân:"), gbc);
        txtTongTienSan = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 10;
        inputPanel.add(txtTongTienSan, gbc);

        // Thêm checkbox
        gbc.gridx = 0;
        gbc.gridy = 11;
        cbThu2 = new JCheckBox("Thứ 2");
        inputPanel.add(cbThu2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        cbThu3 = new JCheckBox("Thứ 3");
        inputPanel.add(cbThu3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 12;
        cbThu4 = new JCheckBox("Thứ 4");
        inputPanel.add(cbThu4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        cbThu5 = new JCheckBox("Thứ 5");
        inputPanel.add(cbThu5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 13;
        cbThu6 = new JCheckBox("Thứ 6");
        inputPanel.add(cbThu6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 13;
        cbThu7 = new JCheckBox("Thứ 7");
        inputPanel.add(cbThu7, gbc);
        gbc.gridx = 0;
        gbc.gridy = 14;
        cbChuNhat = new JCheckBox("Chủ Nhật");
        inputPanel.add(cbChuNhat, gbc);

        // Các button
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnAdd = new JButton("Thêm");
        inputPanel.add(btnAdd, gbc);
        gbc.gridx = 1;
        gbc.gridy = 15;
        btnDelete = new JButton("Xóa");
        inputPanel.add(btnDelete, gbc);
        gbc.gridx = 0;
        gbc.gridy = 16;
        btnEdit = new JButton("Sửa");
        inputPanel.add(btnEdit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 16;
        btnExcel = new JButton("Excel");
        inputPanel.add(btnExcel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 17;
        btnSave = new JButton("Lưu");
        inputPanel.add(btnSave, gbc);
        gbc.gridx = 1;
        gbc.gridy = 17;
        btnCancel = new JButton("Hủy");
        inputPanel.add(btnCancel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        btnPhieuDonHang = new JButton("Thêm đơn hàng");
        inputPanel.add(btnPhieuDonHang, gbc);

        // Panel chứa bảng hiển thị dữ liệu
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Tạo bảng để hiển thị dữ liệu
        String[] columnNames = { "Mã DS", "Mã KH", "Mã Sân", "Loại Sân",
                "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giờ Bắt Đầu", "Giờ Kết Thúc",
                "Số Giờ Thuê", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7",
                "Chủ nhật", "Tổng tiền sân", "Tổng tiền dịch vụ" };
        dataTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Gộp các panel vào frame chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        // mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.EAST);
        mainPanel.add(dataPanel, BorderLayout.CENTER);

        JPanel menu = super.menu();
        add(menu, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Khóa các JTextField
        txtMaDS.setEditable(false);
        txtMaKH.setEditable(false);
        txtMaSan.setEditable(false);
        cmbLoaiSan.setEnabled(false);
        spNgayBatDau.setEnabled(false);
        spNgayKetThuc.setEnabled(false);
        spGioBatDau.setEnabled(false);
        spGioKetThuc.setEnabled(false);
        txtSoGioThue.setEditable(false);
        txtTongTienSan.setEditable(false);
        cbThu2.setEnabled(false);
        cbThu3.setEnabled(false);
        cbThu4.setEnabled(false);
        cbThu5.setEnabled(false);
        cbThu6.setEnabled(false);
        cbThu7.setEnabled(false);
        cbChuNhat.setEnabled(false);

        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        dataTable.setDefaultEditor(Object.class, null);
        dataTable.getTableHeader().setReorderingAllowed(false);

        addListeners();
        add(mainPanel);
        setVisible(true);

        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {
                        txtMaDS.setText((String) dataTable.getValueAt(selectedRow, 0));
                        txtMaKH.setText((String) dataTable.getValueAt(selectedRow, 1));
                        txtMaSan.setText((String) dataTable.getValueAt(selectedRow, 2));
                        cmbLoaiSan.setSelectedItem((String) dataTable.getValueAt(selectedRow, 3));
                        spNgayBatDau.setValue(convertStringToDate((String) dataTable.getValueAt(selectedRow, 4)));
                        spNgayKetThuc.setValue(convertStringToDate((String) dataTable.getValueAt(selectedRow, 5)));
                        spGioBatDau.setValue(convertStringToTime((String) dataTable.getValueAt(selectedRow, 6)));
                        spGioKetThuc.setValue(convertStringToTime((String) dataTable.getValueAt(selectedRow, 7)));

                        txtSoGioThue.setText(calculateSoGioThue(selectedRow));
                        txtTongTienSan.setText((String) dataTable.getValueAt(selectedRow, 16));

                        cbThu2.setSelected(dataTable.getValueAt(selectedRow, 9).equals("1"));
                        cbThu3.setSelected(dataTable.getValueAt(selectedRow, 10).equals("1"));
                        cbThu4.setSelected(dataTable.getValueAt(selectedRow, 11).equals("1"));
                        cbThu5.setSelected(dataTable.getValueAt(selectedRow, 12).equals("1"));
                        cbThu6.setSelected(dataTable.getValueAt(selectedRow, 13).equals("1"));
                        cbThu7.setSelected(dataTable.getValueAt(selectedRow, 14).equals("1"));
                        cbChuNhat.setSelected(dataTable.getValueAt(selectedRow, 15).equals("1"));
                    }
                }
            }
        });

    }

    private void addListeners() {
        cmbLoaiSan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedLoaiSan = (String) cmbLoaiSan.getSelectedItem();
                    String maSan = getMaSanByLoaiSan(selectedLoaiSan);
                    txtMaSan.setText(maSan);
                }
            }
        });
    }

    private String getMaSanByLoaiSan(String loaiSan) {
        String maSan = "";
        String query = "SELECT MaSan FROM san WHERE LoaiSan = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, loaiSan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    maSan = rs.getString("MaSan");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
        return maSan;
    }

    private void loadLoaiSanData() {
        String querry = "SELECT DISTINCT LoaiSan FROM san";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(querry)) {

            // Xóa các mục hiện tại trong cmbLoaiSan
            cmbLoaiSan.removeAllItems();

            // Lặp qua kết quả truy vấn và thêm dữ liệu vào cmbLoaiSan
            while (rs.next()) {
                String loaiSan = rs.getString("LoaiSan");
                cmbLoaiSan.addItem(loaiSan);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }

    private String calculateSoGioThue(int selectedRow) {
        String gioBatDauStr = (String) dataTable.getValueAt(selectedRow, 6);
        String gioKetThucStr = (String) dataTable.getValueAt(selectedRow, 7);

        // Phân tách chuỗi thời gian thành giờ, phút và giây
        String[] gioBatDauParts = gioBatDauStr.split(":");
        String[] gioKetThucParts = gioKetThucStr.split(":");

        // Chuyển đổi các phần giờ và phút thành số nguyên
        int gioBatDau = Integer.parseInt(gioBatDauParts[0]);
        int phutBatDau = Integer.parseInt(gioBatDauParts[1]);
        int gioKetThuc = Integer.parseInt(gioKetThucParts[0]);
        int phutKetThuc = Integer.parseInt(gioKetThucParts[1]);

        // Tính toán số giờ thuê
        int soGioThue = gioKetThuc - gioBatDau;
        if (phutKetThuc < phutBatDau) {
            soGioThue--; // Giảm số giờ thuê nếu phút kết thúc nhỏ hơn phút bắt đầu
        }

        // Hiển thị số giờ thuê kèm đơn vị
        return soGioThue + " giờ";
    }

    // Phương thức này để lấy thông tin từ các trường nhập liệu
    public String[] getDatsanInfo() {
        String[] info = new String[18];
        info[0] = txtMaDS.getText();
        info[1] = txtMaKH.getText();
        info[2] = txtMaSan.getText();
        info[3] = (String) cmbLoaiSan.getSelectedItem();
        info[4] = spNgayBatDau.getValue().toString();
        info[5] = spNgayKetThuc.getValue().toString();
        info[6] = spGioBatDau.getValue().toString();
        info[7] = spGioKetThuc.getValue().toString();
        info[8] = txtSoGioThue.getText();
        info[9] = String.valueOf(cbThu2.isSelected());
        info[10] = String.valueOf(cbThu3.isSelected());
        info[11] = String.valueOf(cbThu4.isSelected());
        info[12] = String.valueOf(cbThu5.isSelected());
        info[13] = String.valueOf(cbThu6.isSelected());
        info[14] = String.valueOf(cbThu7.isSelected());
        info[15] = String.valueOf(cbChuNhat.isSelected());
        info[16] = txtTongTienSan.getText();
        return info;
    }

    // Phương thức này để thêm một trình nghe cho nút Add
    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Edit
    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Save
    public void addSaveListener(ActionListener listener) {
        btnSave.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Delete
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Cancel
    public void addCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Cancel
    public void addExcelListener(ActionListener listener) {
        btnExcel.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Cancel
    public void addPhieuDonHangListener(ActionListener listener) {
        btnPhieuDonHang.addActionListener(listener);
    }

    // Phương thức này để cập nhật dữ liệu trong bảng từ cơ sở dữ liệu
    public void updateTableData(String[][] data) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (String[] row : data) {
            model.addRow(row); // Thêm dữ liệu mới vào bảng
        }
    }

    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date convertStringToTime(String timeString) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            return timeFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatSanView());
    }
}
