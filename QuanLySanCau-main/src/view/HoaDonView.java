package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

import controller.BaoCaoController;
import controller.HoaDonController;
import model.HoaDonModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class HoaDonView extends JFrame {
    private JScrollPane jScrollPaneHoaDonTable;
    private JScrollPane jScrollPaneChiTietHoaDonTable;

    private JTable hoaDonTable;
    private JTable chiTietHoaDonTable;

    private JLabel maDSLabel;
    private JTextField maDSField;
    private JLabel maKHLabel;
    private JTextField maKHField;
    private JLabel tongTienSanLabel;
    private JTextField tongTienSanField;
    private JLabel tongTienDVLabel;
    private JTextField tongTienDVField;
    private JLabel tongTienLabel;
    private JTextField tongTienField;
    private JButton lapHoaDonBtn;
    private JButton thanhToanBtn; // Thêm nút "Thanh toán"
    private JButton inHoaDonBtn;
    private JButton thongKeBtn;
    private JButton capNhatBtn;
    private JLabel timKiemLabel;
    private JTextField timKiemField;
    private JButton timKiemBtn;

    private String[] hoaDonColumnNames = new String[] {
            "Mã đặt sân", "Mã khách hàng", "Tổng tiền sân",
            "Tổng tiền dịch vụ", "Tổng tiền"
    };

    private String[] chiTietHoaDonColumnNames = new String[] {
            "Mã đặt sân", "Mã sân", "Mã dịch vụ", "Mã khách hàng",
            "Tên khách hàng", "Số điện thoại", "Tên sân", "Số giờ thuê", "Giá sân",
            "Tên dịch vụ", "Số lượng", "Giá",
            "Tổng tiền sân", "Tổng tiền dịch vụ", "Tổng tiền", "Ngày lập", "Trạng thái"
    };

    private ArrayList<HoaDonModel> danhSachChiTietHoaDon = new ArrayList<>();

    public HoaDonView(ArrayList<HoaDonModel> hoaDons) {
        initComponents();
        customizeTableFont();
        setSize(1550, 800);
        jScrollPaneHoaDonTable.setPreferredSize(new Dimension(1000, 300));
        displayDataInTable(hoaDons);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void displayDataInTable(ArrayList<HoaDonModel> hoaDons) {
        DefaultTableModel model = (DefaultTableModel) hoaDonTable.getModel();
        for (HoaDonModel hoaDon : hoaDons) {
            model.addRow(new Object[] {
                    hoaDon.getMaDS(), hoaDon.getMaKH(),
                    hoaDon.getTongTienSan(), hoaDon.getTongTienDV(), hoaDon.getTongTien(),
            });
        }
    }

    private void customizeTableFont() {
        Font font = new Font("Arial", Font.PLAIN, 14);
        JTableHeader header = hoaDonTable.getTableHeader();
        header.setFont(font);
        hoaDonTable.setFont(font);
    }

    private void initComponents() {

        JPanel panel = new JPanel();

        hoaDonTable = new JTable();
        hoaDonTable.setModel(new DefaultTableModel(new Object[][] {}, hoaDonColumnNames));
        jScrollPaneHoaDonTable = new JScrollPane(hoaDonTable);
        jScrollPaneHoaDonTable.setPreferredSize(new Dimension(1000, 300));

        chiTietHoaDonTable = new JTable();
        chiTietHoaDonTable.setModel(new DefaultTableModel(new Object[][] {}, chiTietHoaDonColumnNames));
        jScrollPaneChiTietHoaDonTable = new JScrollPane(chiTietHoaDonTable);
        jScrollPaneChiTietHoaDonTable.setPreferredSize(new Dimension(1500, 300));

        JLabel titleLabel = new JLabel("Quản lý hóa đơn ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(titleLabel);

        maDSLabel = new JLabel("Mã đặt sân");
        maDSField = new JTextField(6);
        maDSField.setEditable(false); // Vô hiệu hóa sửa đổi
        maKHLabel = new JLabel("Mã khách hàng");
        maKHField = new JTextField(6);
        maKHField.setEditable(false); // Vô hiệu hóa sửa đổi
        tongTienSanLabel = new JLabel("Tổng tiền sân");
        tongTienSanField = new JTextField(6);
        tongTienSanField.setEditable(false); // Vô hiệu hóa sửa đổi
        tongTienDVLabel = new JLabel("Tiền dịch vụ");
        tongTienDVField = new JTextField(6);
        tongTienDVField.setEditable(false); // Vô hiệu hóa sửa đổi
        tongTienLabel = new JLabel("Tổng tiền");
        tongTienField = new JTextField(6);
        tongTienField.setEditable(false); // Vô hiệu hóa sửa đổi
        lapHoaDonBtn = new JButton("Xem hóa đơn");
        thanhToanBtn = new JButton("Thanh toán"); // Thêm nút "Thanh toán"
        inHoaDonBtn = new JButton("In hóa đơn"); // Thêm nút "Thanh toán"
        thongKeBtn = new JButton("Thống kê"); // Thêm nút "Thanh toán"
        capNhatBtn = new JButton("Cập nhật"); // Thêm nút "Thanh toán"
        timKiemLabel = new JLabel("Tìm kiếm theo mã đặt sân:");
        timKiemField = new JTextField(10);
        timKiemBtn = new JButton("Tìm kiếm");

        hoaDonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = hoaDonTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    maDSField.setText(hoaDonTable.getValueAt(row, 0).toString());
                    maKHField.setText(hoaDonTable.getValueAt(row, 1).toString());
                    tongTienSanField.setText(hoaDonTable.getValueAt(row, 2).toString());
                    tongTienDVField.setText(hoaDonTable.getValueAt(row, 3).toString());
                    tongTienField.setText(hoaDonTable.getValueAt(row, 4).toString());
                }
            }
        });

        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        panel.add(jScrollPaneHoaDonTable);
        panel.add(jScrollPaneChiTietHoaDonTable);
        panel.add(maDSLabel);
        panel.add(maDSField);
        panel.add(maKHLabel);
        panel.add(maKHField);
        panel.add(tongTienSanLabel);
        panel.add(tongTienSanField);
        panel.add(tongTienDVLabel);
        panel.add(tongTienDVField);
        panel.add(tongTienLabel);
        panel.add(tongTienField);
        panel.add(lapHoaDonBtn);
        panel.add(thanhToanBtn); // Thêm nút "Thanh toán"
        panel.add(inHoaDonBtn);
        panel.add(thongKeBtn);
        panel.add(timKiemLabel);
        panel.add(timKiemField);
        panel.add(timKiemBtn);
        panel.add(capNhatBtn);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneHoaDonTable, 500, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneHoaDonTable, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneChiTietHoaDonTable, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneChiTietHoaDonTable, 400, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, maDSLabel, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maDSLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maDSField, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maDSField, 130, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, maKHLabel, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maKHLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maKHField, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maKHField, 160, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, tongTienSanLabel, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienSanLabel, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tongTienSanField, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienSanField, 190, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, tongTienDVLabel, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienDVLabel, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tongTienDVField, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienDVField, 220, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, tongTienLabel, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienLabel, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tongTienField, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tongTienField, 250, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, lapHoaDonBtn, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lapHoaDonBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, thanhToanBtn, 570, SpringLayout.WEST, panel); // Thêm vị trí của nút
                                                                                              // "Thanh toán"
        layout.putConstraint(SpringLayout.NORTH, thanhToanBtn, 720, SpringLayout.NORTH, panel); // Thêm vị trí của nút
                                                                                                // "Thanh toán"
        layout.putConstraint(SpringLayout.WEST, inHoaDonBtn, 700, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, inHoaDonBtn, 720, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, thongKeBtn, 830, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, thongKeBtn, 720, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, timKiemLabel, 1130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, timKiemLabel, 13, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, timKiemField, 1290, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, timKiemField, 13, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, timKiemBtn, 1410, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, timKiemBtn, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, capNhatBtn, 280, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, capNhatBtn, 330, SpringLayout.NORTH, panel);

        add(panel);
        setTitle("Quản lý hóa đơn");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        lapHoaDonBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hienThiChiTietHoaDon();
            }
        });

        capNhatBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức cập nhật hóa đơn từ bảng danhsachdatsan
                HoaDonController hoaDonController = new HoaDonController();
                hoaDonController.insertDataFromDanhsachdatsanToHoadon();
                // Cập nhật hiển thị bảng hóa đơn
                DefaultTableModel model = (DefaultTableModel) hoaDonTable.getModel();
                model.setRowCount(0); // Xóa hết dữ liệu hiện tại

                // Lấy dữ liệu hóa đơn từ cơ sở dữ liệu và hiển thị lên bảng hóa đơn
                ArrayList<HoaDonModel> hoadons = hoaDonController.getAllHoadons();
                for (HoaDonModel hoadon : hoadons) {
                    model.addRow(new Object[] {
                            hoadon.getMaDS(), hoadon.getMaKH(), hoadon.getTongTienSan(), hoadon.getTongTienDV(),
                            hoadon.getTongTien()
                    });
                }
            }
        });

        // Bắt sự kiện khi nhấn vào nút "Thanh toán"
        thanhToanBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = hoaDonTable.getSelectedRow();
                if (selectedRow != -1) {
                    String maDS = hoaDonTable.getValueAt(selectedRow, 0).toString();
                    HoaDonController controller = new HoaDonController();
                    controller.updateTrangThaiHoaDon(maDS, "Đã thanh toán");
                    JOptionPane.showMessageDialog(null, "Đã thanh toán hóa đơn có mã đặt sân: " + maDS);
                    hienThiChiTietHoaDon();
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán.");
                }
            }
        });

        inHoaDonBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ví dụ: Lấy mã hóa đơn từ bảng chi tiết hóa đơn được chọn
                    int selectedRow = chiTietHoaDonTable.getSelectedRow();
                    String MaDS = chiTietHoaDonTable.getValueAt(selectedRow, 0).toString(); // Giả sử mã hóa đơn ở cột
                                                                                            // đầu tiên
                    Hashtable map = new Hashtable();
                    JasperReport rpt = JasperCompileManager.compileReport("src/hoadon.jrxml");
                    map.put("sMaDS", MaDS);
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sancau", "root", "");
                    JasperPrint p = JasperFillManager.fillReport(rpt, map, conn);
                    JasperViewer.viewReport(p, false);
                } catch (JRException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HoaDonView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Thêm xử lý sự kiện cho nút tìm kiếm
        // Thêm xử lý sự kiện cho nút tìm kiếm
        timKiemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maDS = timKiemField.getText().trim();
                DefaultTableModel model = (DefaultTableModel) hoaDonTable.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                hoaDonTable.setRowSorter(sorter);

                if (maDS.isEmpty()) {
                    sorter.setRowFilter(null); // Hiển thị lại tất cả hóa đơn nếu trường tìm kiếm trống
                } else {
                    ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
                    filters.add(RowFilter.regexFilter(maDS, 0)); // 0 là chỉ số cột mã đặt sân

                    sorter.setRowFilter(RowFilter.andFilter(filters));
                }
            }
        });

        thongKeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tạo một instance của BaoCaoView khi nhấn vào nút "Thống kê"
                BaoCaoController baoCaoController = new BaoCaoController();
                DefaultTableModel chiTietHoaDons = baoCaoController.getAllChiTietHoaDons();
                BaoCaoView baoCaoView = new BaoCaoView(chiTietHoaDons);
                baoCaoView.setVisible(true); // Hiển thị form BaoCaoView
            }
        });

        // Ví dụ: Sử dụng NonEditableCellEditor cho tất cả các cột trong hoaDonTable
        for (int i = 0; i < hoaDonTable.getColumnCount(); i++) {
            hoaDonTable.setDefaultEditor(hoaDonTable.getColumnClass(i), new NonEditableCellEditor(new JTextField()));
        }

        // Tương tự cho chiTietHoaDonTable
        for (int i = 0; i < chiTietHoaDonTable.getColumnCount(); i++) {
            chiTietHoaDonTable.setDefaultEditor(chiTietHoaDonTable.getColumnClass(i),
                    new NonEditableCellEditor(new JTextField()));
        }

    }

    // Hàm hiển thị chi tiết hóa đơn khi nhấn nút "Xem hóa đơn"
    private void hienThiChiTietHoaDon() {
        int selectedRow = hoaDonTable.getSelectedRow();
        if (selectedRow != -1) {
            String maDSString = hoaDonTable.getValueAt(selectedRow, 0).toString();
            try {
                int maDS = Integer.parseInt(maDSString);
                HoaDonController controller = new HoaDonController();

                controller.updateChiTietHoaDonFromPhieuDatHang(maDS);

                ArrayList<HoaDonModel> danhSachChiTietHoaDon = controller.getChiTietHoaDonByMaDS(maDS);
                displayDataInChiTietHoaDonTable(danhSachChiTietHoaDon);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Mã đặt sân không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để xem chi tiết.", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Hàm hiển thị chi tiết hóa đơn trong bảng
    private void displayDataInChiTietHoaDonTable(ArrayList<HoaDonModel> danhSachChiTietHoaDon) {
        DefaultTableModel model = (DefaultTableModel) chiTietHoaDonTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng
        for (HoaDonModel hoaDon : danhSachChiTietHoaDon) {
            model.addRow(new Object[] {
                    hoaDon.getMaDS(), hoaDon.getMaSan(), hoaDon.getMaDV(), hoaDon.getMaKH(),
                    hoaDon.getTenKH(), hoaDon.getSDT(), hoaDon.getTenSan(),
                    hoaDon.getSoGioThue(), hoaDon.getGiaSan(), hoaDon.getTenDV(),
                    hoaDon.getSoLuong(), hoaDon.getGia(),
                    hoaDon.getTongTienSan(), hoaDon.getTongTienDV(), hoaDon.getTongTien(),
                    hoaDon.getNgayLap(), hoaDon.getTrangThai()
            });
        }
    }

    public class NonEditableCellEditor extends DefaultCellEditor {
        public NonEditableCellEditor(JTextField textField) {
            super(textField);
        }

        @Override
        public boolean isCellEditable(java.util.EventObject e) {
            return false; // Không cho phép chỉnh sửa ô
        }
    }

    public static void main(String args[]) {
        HoaDonController controller = new HoaDonController();
        ArrayList<HoaDonModel> hoaDons = controller.getAllHoadons();
        SwingUtilities.invokeLater(() -> new HoaDonView(hoaDons));
    }
}
