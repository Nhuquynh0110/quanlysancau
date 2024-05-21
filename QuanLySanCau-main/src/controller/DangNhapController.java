package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.DangNhapModel;
import model.DatSanModel;
import view.DangNhapView;
import view.DatSanView;

public class DangNhapController {
    private DangNhapView view;
    private DangNhapModel model;
    
    public DangNhapController(DangNhapView view, DangNhapModel model) {
        this.view = view;
        this.model = model;
        this.view.addDangNhapListener(new dangNhapListioner());
        this.view.addThoatListener(new thoatsListener());
    }
    
    private void openIndexFrm() {
        // Gọi form mới từ package khác
        DatSanView view = new DatSanView();
        DatSanModel model = new DatSanModel();
        DatSanController controller = new DatSanController(model, view);
        view.setVisible(true);
    }

    class dangNhapListioner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = view.getUser();
            String pass = view.getPass();
            
            if (model.kiemTra(user, pass)) {
                JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");
                view.dispose();
                openIndexFrm();
            } else {
                view.displaySai("Sai tài khoản hoặc mật khẩu!");
            }
        }         
    }
    
    class thoatsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }    
    }

    public static void main(String[] args) {
        DangNhapView view = new DangNhapView();
        DangNhapModel model = new DangNhapModel();
        DangNhapController controller = new DangNhapController(view, model);
        view.setVisible(true);
    }
}
