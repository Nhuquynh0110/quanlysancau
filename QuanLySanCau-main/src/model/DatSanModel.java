package model;

import java.sql.*;

public class DatSanModel {
    private int MaDS, MaKH, MaSan, MaDH;
    private String LoaiSan;
    private Date NgayBatDau, NgayKetThuc, GioBatDau, GioKetThuc, SoGioThue;
    private boolean Thu_2, Thu_3, Thu_4, Thu_5, Thu_6, Thu_7, ChuNhat, TrangThai;

    public DatSanModel() {
        super();
    }
    
    // Constructor
    public DatSanModel(int MaDS, int MaKH, int MaSan, int MaDH, String LoaiSan,
            Date NgayBatDau, Date NgayKetThuc, Date GioBatDau, Date GioKetThuc,
            Date SoGioThue, boolean Thu_2, boolean Thu_3, boolean Thu_4,
            boolean Thu_5, boolean Thu_6, boolean Thu_7, boolean ChuNhat,
            boolean TrangThai) {
        this.MaDS = MaDS;
        this.MaKH = MaKH;
        this.MaSan = MaSan;
        this.MaDH = MaDH;
        this.LoaiSan = LoaiSan;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GioBatDau = GioBatDau;
        this.GioKetThuc = GioKetThuc;
        this.SoGioThue = SoGioThue;
        this.Thu_2 = Thu_2;
        this.Thu_3 = Thu_3;
        this.Thu_4 = Thu_4;
        this.Thu_5 = Thu_5;
        this.Thu_6 = Thu_6;
        this.Thu_7 = Thu_7;
        this.ChuNhat = ChuNhat;
        this.TrangThai = TrangThai;
    }

    // Getters và Setters
    public int getMaDS() {
        return MaDS;
    }

    public void setMaDS(int MaDS) {
        this.MaDS = MaDS;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getMaSan() {
        return MaSan;
    }

    public void setMaSan(int MaSan) {
        this.MaSan = MaSan;
    }

    public int getMaDH() {
        return MaDH;
    }

    public void setMaDH(int MaDH) {
        this.MaDH = MaDH;
    }

    public String getLoaiSan() {
        return LoaiSan;
    }

    public void setLoaiSan(String LoaiSan) {
        this.LoaiSan = LoaiSan;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public Date getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(Date GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public Date getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(Date GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    public Date getSoGioThue() {
        return SoGioThue;
    }

    public void setSoGioThue(Date SoGioThue) {
        this.SoGioThue = SoGioThue;
    }

    public boolean isThu_2() {
        return Thu_2;
    }

    public void setThu_2(boolean Thu_2) {
        this.Thu_2 = Thu_2;
    }

    public boolean isThu_3() {
        return Thu_3;
    }

    public void setThu_3(boolean Thu_3) {
        this.Thu_3 = Thu_3;
    }

    public boolean isThu_4() {
        return Thu_4;
    }

    public void setThu_4(boolean Thu_4) {
        this.Thu_4 = Thu_4;
    }

    public boolean isThu_5() {
        return Thu_5;
    }

    public void setThu_5(boolean Thu_5) {
        this.Thu_5 = Thu_5;
    }

    public boolean isThu_6() {
        return Thu_6;
    }

    public void setThu_6(boolean Thu_6) {
        this.Thu_6 = Thu_6;
    }

    public boolean isThu_7() {
        return Thu_7;
    }

    public void setThu_7(boolean Thu_7) {
        this.Thu_7 = Thu_7;
    }

    public boolean isChuNhat() {
        return ChuNhat;
    }

    public void setChuNhat(boolean ChuNhat) {
        this.ChuNhat = ChuNhat;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
}
