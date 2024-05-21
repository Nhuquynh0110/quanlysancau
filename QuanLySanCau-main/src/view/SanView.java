package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controller.SanController;
import model.SanModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class SanView extends MainMenuView {

    private JButton AddBtn, EditBtn, SaveBtn, DeleteBtn, ClearBtn, SearchBtn, ExportBtn;
    private JScrollPane JScrollPaneTable;
    private JTable Table;
    private JTextField Searchtf, MaSantf, TenSantf, LoaiSantf, GiaSantf;
    private JLabel MaSanlbl, TenSanlbl, LoaiSanlbl, GiaSanlbl;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[] {
            "Mã Sân", "Tên Sân", "Loại Sân", "Giá Sân" };
    private final Object data = new Object[][] {};
    private List<SanModel> listSan = new ArrayList<>();

    int sanMa;
    private boolean isSearching = false;

    public SanView() {
        initComponents();
    }

    private void initComponents() {
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MaSanlbl = new JLabel("Mã Sân");
        TenSanlbl = new JLabel("Tên Sân");
        LoaiSanlbl = new JLabel("Loại Sân");
        GiaSanlbl = new JLabel("Giá Sân");
        JLabel label = new JLabel("BẢNG GIÁ SÂN");
        Font font = new Font(label.getFont().getName(), Font.BOLD, 25); // Đặt font chữ
        label.setFont(font); // Áp dụng font cho JLabel

        MaSantf = new JTextField(15);
        MaSantf.setEditable(false);// không cho sửa
        TenSantf = new JTextField(15);
        LoaiSantf = new JTextField(15);
        GiaSantf = new JTextField(15);
        Searchtf = new JTextField();
        Searchtf.setPreferredSize(new Dimension(200, 25));

        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        SaveBtn = new JButton("Lưu");
        DeleteBtn = new JButton("Xoá");
        ClearBtn = new JButton("Nhập lại");
        SearchBtn = new JButton("Tìm kiếm");
        ExportBtn = new JButton("Xuất Excel");

        JScrollPaneTable = new JScrollPane();
        Table = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        JScrollPaneTable.setViewportView(Table);
        Table.getTableHeader().setReorderingAllowed(false);// không cho sửa các tiêu đề cột
        JScrollPaneTable.setPreferredSize(new Dimension(1247, 300));
        Table.setDefaultEditor(Object.class, null);// không cho sửa dữ liệu trong bảng

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(JScrollPaneTable);

        panel.add(label);
        panel.add(MaSanlbl);
        panel.add(TenSanlbl);
        panel.add(LoaiSanlbl);
        panel.add(GiaSanlbl);
        // panel.add(label);

        panel.add(MaSantf);
        panel.add(TenSantf);
        panel.add(LoaiSantf);
        panel.add(GiaSantf);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout để các nút được sắp
                                                                             // xếp theo chiều ngang

        // Thêm các nút vào panel
        buttonPanel.add(AddBtn);
        buttonPanel.add(Box.createHorizontalStrut(30)); // Tạo một khoảng cách ngang 30 pixel giữa các nút
        buttonPanel.add(EditBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(SaveBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(DeleteBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ClearBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ExportBtn);

        // Thêm panel chứa các nút vào phần dưới cùng của panel chính
        panel.add(buttonPanel);

        panel.add(Searchtf);
        panel.add(SearchBtn);

        // Bang item
        layout.putConstraint(SpringLayout.WEST, label, 280, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label, 15, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, Searchtf, 225, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Searchtf, 65, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 225, SpringLayout.WEST, Searchtf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 65, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
        // cot 1
        layout.putConstraint(SpringLayout.WEST, MaSanlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaSanlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, MaSantf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaSantf, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, TenSanlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TenSanlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, TenSantf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TenSantf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, LoaiSanlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, LoaiSanlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, LoaiSantf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, LoaiSantf, 20, SpringLayout.SOUTH, JScrollPaneTable);
        // cot2

        layout.putConstraint(SpringLayout.WEST, GiaSanlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, GiaSanlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, GiaSantf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, GiaSantf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        // nut
        // layout.putConstraint(SpringLayout.EAST, AddBtn, -32, SpringLayout.WEST,
        // EditBtn);
        // layout.putConstraint(SpringLayout.NORTH, AddBtn, 25, SpringLayout.SOUTH,
        // JScrollPaneTable);
        //
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -100, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới
                                                                                               // cùng của panel chính

        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        SanController controller = new SanController();
        listSan = controller.getSanList();
        this.sanList(listSan);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel menu = super.menu();
        mainPanel.add(menu, BorderLayout.WEST);
        mainPanel.add(panel, BorderLayout.CENTER);
        SaveBtn.setEnabled(false);
        ClearBtn.setEnabled(false);

        add(mainPanel);
        setTitle("Quản lý sân");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        controller.setButtonListener(AddBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String TenSan = TenSantf.getText();
                String LoaiSan = LoaiSantf.getText();
                // float Giasan=Float.parseFloat(GiaSantf.getText());
                String GiaSanText = GiaSantf.getText();

                // Kiểm tra nếu SDT hoặc SoLanDat không phải là số
                if (!isNumeric(GiaSanText)) {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập số cho giá sân", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng việc thêm khách hàng và hiển thị thông báo lỗi
                }
                if (TenSan.isEmpty() || LoaiSan.isEmpty() || GiaSanText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng việc thêm khách hàng và hiển thị thông báo lỗi
                }
                Float GiaSan = Float.parseFloat(GiaSanText);
                SanModel s = new SanModel(TenSan, LoaiSan, GiaSan);
                controller.addsan(s);
                JOptionPane.showMessageDialog(null, "Sân đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                listSan = controller.getSanList();
                sanList(listSan);
                clearFields();

            }
        });

        controller.setButtonListener(ClearBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }

        });

        controller.setButtonListener(DeleteBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow == -1) {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa", "Lỗi Xóa",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy dữ liệu của hàng được chọn và hiển thị lên các ô text
                int MaSan = (int) Table.getValueAt(selectedRow, 0);
                String TenSan = (String) Table.getValueAt(selectedRow, 1);
                String LoaiSan = (String) Table.getValueAt(selectedRow, 2);
                float GiaSan = (float) Table.getValueAt(selectedRow, 3);

                // Hiển thị dữ liệu lên các ô text
                MaSantf.setText(String.valueOf(MaSan)); // Hiển thị ID
                TenSantf.setText(TenSan);
                LoaiSantf.setText(LoaiSan);
                GiaSantf.setText(String.valueOf(GiaSan));

                int sanMa = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không ?", "Thông báo",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    controller.deletesan(sanMa);
                    listSan = controller.getSanList();
                    sanList(listSan);
                    clearFields();
                }
            }
        });

        controller.setButtonListener(EditBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem có hàng nào được chọn không
                if (Table.getSelectedRow() != -1) {
                    // Lấy hàng đã chọn
                    int selectedRow = Table.getSelectedRow();

                    // Lấy dữ liệu từ bảng và đặt vào các JLabel
                    int MaSan = (int) Table.getValueAt(selectedRow, 0);
                    String TenSan = (String) Table.getValueAt(selectedRow, 1);
                    String LoaiSan = (String) Table.getValueAt(selectedRow, 2);
                    Float GiaSan = (Float) Table.getValueAt(selectedRow, 3);

                    MaSantf.setText(String.valueOf(MaSan)); // Chuyển đổi số nguyên thành chuỗi
                    TenSantf.setText(TenSan);
                    LoaiSantf.setText(LoaiSan); //
                    GiaSantf.setText(String.valueOf(GiaSan));

                    AddBtn.setEnabled(false);
                    DeleteBtn.setEnabled(false);
                    ExportBtn.setEnabled(false);
                    SaveBtn.setEnabled(true);

                    // sanMa= (int) Table.getValueAt(selectedRow, 0);
                }
            }
        });

        controller.setButtonListener(SaveBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
                String TenSan = TenSantf.getText();
                String LoaiSan = LoaiSantf.getText();
                String GiaSanstr = GiaSantf.getText();
                
             if (TenSan.isEmpty() || LoaiSan.isEmpty() || GiaSanstr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng việc thêm khách hàng và hiển thị thông báo lỗi
        }

                try {
                    // Chuyển đổi dữ liệu về số nguyên
                    Float GiaSan = Float.parseFloat(GiaSanstr);

                    // Kiểm tra xem có dòng nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.");
                        return;
                    }

                    // Lấy ID của item được chọn
                    int MaSan = (int) Table.getValueAt(selectedRow, 0);

                    // Gọi phương thức trong controller để cập nhật thông tin của mục đã chọn
                    SanModel s = new SanModel(MaSan, TenSan, LoaiSan, GiaSan);
                    controller.updatesan(s);

                    // Cập nhật lại dòng trong bảng sau khi lưu thành công
                    listSan = controller.getSanList();
                    sanList(listSan);
                    clearFields();
                    
                    AddBtn.setEnabled(true);
                    DeleteBtn.setEnabled(true);
                    ExportBtn.setEnabled(true);
                    SaveBtn.setEnabled(false);
                    ClearBtn.setEnabled(false);
                } catch (NumberFormatException ex) {
                    // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho ô giá ");
                }
            }
        });

        controller.setButtonListener(SearchBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = Searchtf.getText().trim(); // Lấy từ khóa tìm kiếm từ JTextField và loại bỏ dấu cách
                                                            // thừa

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<SanModel> searchResult = new ArrayList<>();

                // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong
                // sách hay không
                for (SanModel s : listSan) {
                    if (s.getTenSan().toLowerCase().contains(keyword.toLowerCase())
                            || s.getLoaiSan().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(s.getGiaSan()).contains(keyword)) {
                        // Nếu tìm thấy từ khóa trong tên sách, thêm sách vào danh sách kết quả
                        searchResult.add(s);
                    }
                }
                // Gán isSearching thành true khi đang thực hiện tìm kiếm
                isSearching = true;
                // Hiển thị kết quả tìm kiếm trên bảng
                sanList(searchResult);
            }

        });

        controller.setButtonListener(ExportBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Bảng giá sân ");
                XSSFRow row = null;
                Cell cell = null;
        
                row = sheet.createRow(0);
        
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Mã sân");
        
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Tên sân");
        
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Loại sân");
        
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Giá sân");
        
                int rowCount = Table.getRowCount(); // Lấy số lượng hàng trên bảng
                int columnCount = Table.getColumnCount(); // Lấy số lượng cột trên bảng
        
                // Lặp qua từng hàng của bảng
                for (int i = 0; i < rowCount; i++) {
                    row = sheet.createRow(i + 1); // Bắt đầu từ hàng 1, vì hàng 0 đã được sử dụng cho tiêu đề
        
                    // Lặp qua từng cột của hàng và thêm dữ liệu vào file Excel
                    for (int j = 0; j < columnCount; j++) {
                        Object value = Table.getValueAt(i, j); // Lấy giá trị của ô tại hàng i, cột j
                        if (value != null) {
                            cell = row.createCell(j, CellType.STRING);
                            cell.setCellValue(value.toString());
                        }
                    }
                }
        
                try (FileOutputStream fos = new FileOutputStream("D:\\banggiasan.xlsx")) {
                    workbook.write(fos);
                    JOptionPane.showMessageDialog(rootPane, "Excel Success!.File path: F:\\danhsachsan.xlsx");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, "An error occurred while exporting to Excel.");
                }
            }
        });

    }

    private void sanList(List<SanModel> updatedSanList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        // Thêm dữ liệu mới từ danh sách updatedItemList vào bảng
        for (SanModel s : updatedSanList) {
            model.addRow(new Object[] {
                    s.getMaSan(),
                    s.getTenSan(),
                    s.getLoaiSan(),
                    s.getGiaSan()
            });
        }
    }

    public void clearFields() {
        MaSantf.setText("");
        TenSantf.setText("");
        LoaiSantf.setText("");
        GiaSantf.setText("");
    }

    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            // dòng mã char c = str.charAt(i);trong đoạn mã trước đó đang lấy ký tự ở vị
            // trí i trong chuỗi str và gán vào biến c.
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}