package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.RowFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaoCaoView extends JFrame {
    private JScrollPane jScrollPaneChiTietHoaDonTable;
    private JTable chiTietHoaDonTable;
    private JComboBox<String> statusFilterCbb;

    public BaoCaoView(DefaultTableModel model) {
        initComponents(model);
        setSize(1550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents(DefaultTableModel model) {
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JLabel titleLabel = new JLabel("Báo cáo thống kê");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);
        
        chiTietHoaDonTable = new JTable(model);
        jScrollPaneChiTietHoaDonTable = new JScrollPane(chiTietHoaDonTable);
        jScrollPaneChiTietHoaDonTable.setPreferredSize(new Dimension(1500, 300));
        panel.add(jScrollPaneChiTietHoaDonTable);

        statusFilterCbb = new JComboBox<>(new String[]{"Tất cả", "chưa thanh toán", "Đã thanh toán"});
        panel.add(statusFilterCbb);

        JButton exportExcelBtn = new JButton("Xuất Excel");
        panel.add(exportExcelBtn);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneChiTietHoaDonTable, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneChiTietHoaDonTable, 40, SpringLayout.NORTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, statusFilterCbb, 1380, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, statusFilterCbb, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, exportExcelBtn, 1250, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, exportExcelBtn, 10, SpringLayout.NORTH, panel);

        add(panel);
        setTitle("Báo cáo thống kê");

        statusFilterCbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = (String) statusFilterCbb.getSelectedItem();
                filterByStatus(selectedStatus);
            }
        });

        exportExcelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo workbook và sheet mới
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh sách Hóa đơn");
                XSSFRow row = null;
                Cell cell = null;

                // Tạo hàng đầu tiên (header) trong sheet
                row = sheet.createRow(0);
                String[] headers = {"Mã đặt sân", "Mã sân", "Mã dịch vụ", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Tên sân", "Số giờ thuê", "Giá sân", "Tên dịch vụ", "Số lượng", "Giá", "Tổng tiền sân", "Tổng tiền dịch vụ", "Tổng tiền", "Ngày lập", "Trạng thái"};
                for (int i = 0; i < headers.length; i++) {
                    cell = row.createCell(i, CellType.STRING);
                    cell.setCellValue(headers[i]);
                }

                // Lấy model từ table
                DefaultTableModel tableModel = (DefaultTableModel) chiTietHoaDonTable.getModel();

                // Số dòng trong table
                int rowCount = tableModel.getRowCount();
                int columnCount = tableModel.getColumnCount();

                // Lặp qua từng dòng và ghi vào sheet
                for (int i = 0; i < rowCount; i++) {
                    row = sheet.createRow(i + 1);
                    for (int j = 0; j < columnCount; j++) {
                        Object value = tableModel.getValueAt(i, j); // Lấy giá trị của ô tại hàng i, cột j
                        if (value != null) {
                            cell = row.createCell(j, CellType.STRING);
                            cell.setCellValue(value.toString());
                        }
                    }
                }

                try {
                    // Ghi workbook vào file
                    File file = new File("D:\\danhsachhoadon.xlsx");
                    FileOutputStream fos = new FileOutputStream(file);
                    workbook.write(fos);
                    fos.close();
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công! File được lưu tại: " + file.getAbsolutePath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Xuất Excel thất bại!");
                }
            }
        });
    }

    private void filterByStatus(String status) {
        DefaultTableModel model = (DefaultTableModel) chiTietHoaDonTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        chiTietHoaDonTable.setRowSorter(sorter);

        if (status.equals("Tất cả")) {
            sorter.setRowFilter(null);
        } else {
            String regex = (status.equals("chưa thanh toán")) ? "chưa thanh toán" : "Đã thanh toán";
            sorter.setRowFilter(RowFilter.regexFilter(regex, model.getColumnCount() - 1));
        }
    }
}
