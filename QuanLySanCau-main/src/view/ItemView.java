package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controller.ItemController;
import model.ItemModel;

public class ItemView extends MainMenuView {

    private JButton AddBtn, EditBtn, SaveBtn, DeleteBtn, ClearBtn, SearchBtn, ExportBtn;
    private JScrollPane JScrollPaneTable;
    private JTable Table;
    private JComboBox Sortcbb, TypeItemcbb;
    private JTextField SearchItemtf, IdItemtf, NameItemtf, UnitItemstf, PriceItemstf;
    private JLabel IdItemlbl, NameItemlbl, TypeItemlbl, UnitItemlbl, PriceItemlbl, rowCountlbl;
    private DefaultTableModel tblModel;// lưu tru dl để hiển thị trong Jtable
    private final String[] columnNames = new String[] {
            "Id sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn vị tính", "Giá" };
    private final Object data = new Object[][] {};
    private List<ItemModel> listItem = new ArrayList<>();

    int itemId;

    public ItemView() {
        form();
        updateRowCount();// hiện số dòng ngay khi chạy form
    }

    private void form() {
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        IdItemlbl = new JLabel("Mã sản phẩm");
        NameItemlbl = new JLabel("Tên sản phẩm");
        TypeItemlbl = new JLabel("Loại sản phẩm");
        UnitItemlbl = new JLabel("Đơn vị tính");
        PriceItemlbl = new JLabel("Giá");
        rowCountlbl = new JLabel("Số dòng:");
        JLabel label = new JLabel("QUẢN LÝ DỊCH VỤ");
        Font font = new Font(label.getFont().getName(), Font.BOLD, 25); // Đặt font chữ
        label.setFont(font); // Áp dụng font cho JLabel

        IdItemtf = new JTextField(15);
        IdItemtf.setEditable(false);// không cho sửa
        NameItemtf = new JTextField(15);
        TypeItemcbb = new JComboBox<>(new String[] { " ", "Đồ ăn", "Đồ uống", "Dụng cụ", "Khác" });
        TypeItemcbb.setPreferredSize(new Dimension(155, 20));
        PriceItemstf = new JTextField(15);
        UnitItemstf = new JTextField(15);
        SearchItemtf = new JTextField();
        SearchItemtf.setPreferredSize(new Dimension(200, 25));

        Sortcbb = new JComboBox<>(new String[] { "Sắp xếp", "Tăng dần theo giá", "Giảm dần theo giá" });
        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        SaveBtn = new JButton("Lưu");
        DeleteBtn = new JButton("Xoá");
        ClearBtn = new JButton("Nhập lại");
        SearchBtn = new JButton("Tìm theo tên/loại");
        ExportBtn = new JButton("Xuất Excel");

        // JScrollPaneTable = new JScrollPane();
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

        panel.add(IdItemlbl);
        panel.add(NameItemlbl);
        panel.add(TypeItemlbl);
        panel.add(UnitItemlbl);
        panel.add(PriceItemlbl);
        panel.add(label);
        panel.add(rowCountlbl);

        panel.add(IdItemtf);
        panel.add(NameItemtf);
        panel.add(TypeItemcbb);
        panel.add(UnitItemstf);
        panel.add(PriceItemstf);

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

        panel.add(SearchItemtf);
        panel.add(SearchBtn);
        panel.add(Sortcbb);

        // Bang item
        layout.putConstraint(SpringLayout.WEST, label, 115, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label, 15, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, rowCountlbl, 65, SpringLayout.EAST, UnitItemstf);
        layout.putConstraint(SpringLayout.SOUTH, rowCountlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, Sortcbb, 375, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Sortcbb, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchItemtf, 155, SpringLayout.WEST, Sortcbb);
        layout.putConstraint(SpringLayout.NORTH, SearchItemtf, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 210, SpringLayout.WEST, SearchItemtf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
        // cot 1
        layout.putConstraint(SpringLayout.WEST, IdItemlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IdItemlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, IdItemtf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IdItemtf, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, NameItemlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, NameItemlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, NameItemtf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, NameItemtf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, TypeItemlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TypeItemlbl, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, TypeItemcbb, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TypeItemcbb, 100, SpringLayout.SOUTH, JScrollPaneTable);
        // cot2
        layout.putConstraint(SpringLayout.WEST, UnitItemlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, UnitItemlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, UnitItemstf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, UnitItemstf, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, PriceItemlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PriceItemlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, PriceItemstf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PriceItemstf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        // nut
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -80, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới

        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        ItemController controller = new ItemController();
        listItem = controller.getItemList();
        this.itemList(listItem);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel menu = super.menu();
        mainPanel.add(menu, BorderLayout.WEST);
        mainPanel.add(panel, BorderLayout.CENTER);

        SaveBtn.setEnabled(false);
        ClearBtn.setEnabled(false);

        add(mainPanel);
        setTitle("Quản lý dịch vụ");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        controller.setButtonListener(AddBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("add thanh cong");
                String Nameitem = NameItemtf.getText();
                String Typeitem = (String) TypeItemcbb.getSelectedItem().toString();
                String Unititem = UnitItemstf.getText();

                // Biến cờ để kiểm tra giá trị nhập vào
                boolean validPrice = false;
                float Priceitem = 0;

                // Kiểm tra và yêu cầu người dùng nhập lại nếu giá không phải là số
                while (!validPrice) {
                    String priceInput = PriceItemstf.getText();
                    try {
                        Priceitem = Float.parseFloat(priceInput);
                        validPrice = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập giá là một số.", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        PriceItemstf.setText(""); // Xóa nội dung không hợp lệ
                        PriceItemstf.requestFocus(); // Đưa con trỏ vào ô giá để người dùng nhập lại
                        return; // Thoát khỏi phương thức nếu giá trị không hợp lệ
                    }
                }
                // Tiếp tục thêm mục vào danh sách nếu giá trị hợp lệ
                ItemModel item = new ItemModel(Nameitem, Typeitem, Unititem, Priceitem);
                System.out.println("add thanh cong: " + NameItemtf.getText());
                controller.addItem(item);
                JOptionPane.showMessageDialog(null, "Item đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                listItem = controller.getItemList();
                itemList(listItem);
                clearFields();
                updateRowCount();
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
                    int iditem = (int) Table.getValueAt(selectedRow, 0);
                    String nameItem = (String) Table.getValueAt(selectedRow, 1);
                    String typeItem = (String) Table.getValueAt(selectedRow, 2);
                    String unitItem = (String) Table.getValueAt(selectedRow, 3);
                    Float priceItem = (Float) Table.getValueAt(selectedRow, 4);

                    IdItemtf.setText(String.valueOf(iditem)); // Chuyển đổi số nguyên thành chuỗi
                    NameItemtf.setText(nameItem);
                    TypeItemcbb.setSelectedItem(typeItem);
                    UnitItemstf.setText(unitItem);
                    PriceItemstf.setText(String.valueOf(priceItem));
                    itemId = (int) Table.getValueAt(selectedRow, 0);

                    AddBtn.setEnabled(false);
                    DeleteBtn.setEnabled(false);
                    ExportBtn.setEnabled(false);
                    SaveBtn.setEnabled(true);
                }
            }
        });

        controller.setButtonListener(SaveBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
                String nameitem = NameItemtf.getText();
                String typeitem = (String) TypeItemcbb.getSelectedItem().toString();
                String unititem = UnitItemstf.getText();
                String priceitem = PriceItemstf.getText();

                if (nameitem.isEmpty() || typeitem.isEmpty() || unititem.isEmpty() || priceitem.isEmpty()) {
                    // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực
                    // hiện lưu
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                try {
                    // Chuyển đổi dữ liệu về số nguyên
                    float price = Float.parseFloat(priceitem);

                    // Kiểm tra xem có item nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một item để chỉnh sửa.");
                        return;
                    }

                    // Lấy ID của item được chọn
                    int itemId = (int) Table.getValueAt(selectedRow, 0);

                    // Gọi phương thức trong controller để cập nhật thông tin của mục đã chọn
                    ItemModel item = new ItemModel(itemId, nameitem, typeitem, unititem, price);
                    controller.updateItem(item);

                    // Cập nhật lại dòng trong bảng sau khi lưu thành công
                    listItem = controller.getItemList();
                    itemList(listItem);
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
                updateRowCount();
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
                int Id = (int) Table.getValueAt(selectedRow, 0);
                String name = (String) Table.getValueAt(selectedRow, 1);
                String type = (String) Table.getValueAt(selectedRow, 2);
                String unit = (String) Table.getValueAt(selectedRow, 3);
                float price = (float) Table.getValueAt(selectedRow, 4);

                // Hiển thị dữ liệu lên các ô text
                IdItemtf.setText(String.valueOf(Id)); // Hiển thị ID
                NameItemtf.setText(name);
                TypeItemcbb.setSelectedItem(type);
                UnitItemstf.setText(unit);
                PriceItemstf.setText(String.valueOf(price));

                int itemId = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không ?", "Thông báo",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    controller.deleteItem(itemId);
                    listItem = controller.getItemList();
                    itemList(listItem);
                    clearFields();
                }
                updateRowCount();
            }
        });

        controller.setButtonListener(ClearBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                updateRowCount();
            }
        });

        controller.setButtonListener(SearchBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = SearchItemtf.getText().trim().toLowerCase(); // Lấy từ khóa tìm kiếm từ JTextField và
                                                                              // loại bỏ dấu
                // cách thừa

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<ItemModel> searchResult = new ArrayList<>();

                // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong
                // sách hay không
                for (ItemModel item : listItem) {
                    if (item.getNameItem().toLowerCase().contains(keyword)
                            || item.getTypeItem().toLowerCase().contains(keyword)) {
                        searchResult.add(item);
                    }
                }
                itemList(searchResult);
                updateRowCount();
            }

        });

        Sortcbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSortOption = (String) Sortcbb.getSelectedItem();
                switch (selectedSortOption) {
                    case "Tăng dần theo giá":
                        Collections.sort(listItem, Comparator.comparingDouble(ItemModel::getPriceItem));
                        break;
                    case "Giảm dần theo giá":
                        Collections.sort(listItem, Comparator.comparingDouble(ItemModel::getPriceItem).reversed());
                        break;
                    case "Sắp xếp":
                        listItem = controller.getItemList();
                        itemList(listItem);
                        clearFields();
                        break;
                }
                // Cập nhật hiển thị của bảng
                itemList(listItem);
                updateRowCount();
            }
        });

        controller.setButtonListener(ExportBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm ");
                XSSFRow row = null;
                Cell cell = null;

                row = sheet.createRow(0);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("ID sản phẩm");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Tên sản phẩm");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Loại sản phẩm");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Đơn vị tính");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Giá");

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

                try (FileOutputStream fos = new FileOutputStream("D://danhsachdichvu.xlsx")) {
                    workbook.write(fos);
                    JOptionPane.showMessageDialog(rootPane, "Excel Success!.File path: D://danhsachsanpham.xlsx");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, "An error occurred while exporting to Excel.");
                }
            }
        });

    }

    private void itemList(List<ItemModel> updatedItemList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        // Thêm dữ liệu mới từ danh sách updatedItemList vào bảng
        for (ItemModel item : updatedItemList) {
            model.addRow(new Object[] {
                    item.getIdItem(),
                    item.getNameItem(),
                    item.getTypeItem(),
                    item.getUnitItem(),
                    item.getPriceItem()
            });
        }
    }

    public void clearFields() {
        IdItemtf.setText("");
        NameItemtf.setText("");
        TypeItemcbb.setSelectedItem(" ");
        UnitItemstf.setText("");
        PriceItemstf.setText("");
    }

    public void updateRowCount() {
        int rowCount = Table.getRowCount();
        rowCountlbl.setText("Số dòng: " + rowCount);
    }

}
// Lớp ItemView chịu trách nhiệm về hiển thị giao diện người
// dùng và xử lý các sự kiện từ người dùng như nhấn nút, nhập liệu, v.v.