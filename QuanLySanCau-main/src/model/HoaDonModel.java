package model;

public class HoaDonModel {
    private String MaDS;
    private String MaKH;
    private String MaDV;
    private String TongTienSan;
    private String TongTienDV;
    private String TongTien;

    // Các cột mới cho chi tiết hóa đơn
    private String NgayLap;
    private String TenSan;
    private String TenDV;
    private String SoLuong;
    private String MaSan;
    private String SDT;
    private String TrangThai;
    private String TenKH;
    private String SoGioThue;
    private String GiaSan;
    private String Gia;

    public String getTenKH() {
        return TenKH;
    }

    public String getSoGioThue() {
        return SoGioThue;
    }

    public String getGiaSan() {
        return GiaSan;
    }

    public String getGia() {
        return Gia;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setSoGioThue(String SoGioThue) {
        this.SoGioThue = SoGioThue;
    }

    public void setGiaSan(String GiaSan) {
        this.GiaSan = GiaSan;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }
    

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getTongTienDV() {
        return TongTienDV;
    }

    public void setTongTienDV(String TongTienDV) {
        this.TongTienDV = TongTienDV;
    }
    



    public void setMaSan(String MaSan) {
        this.MaSan = MaSan;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    


    public String getMaSan() {
        return MaSan;
    }

    public String getSDT() {
        return SDT;
    }
    
    
    public String getMaDS() {
        return MaDS;
    }

    public void setMaDS(String MaDS) {
        this.MaDS = MaDS;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTongTienSan() {
        return TongTienSan;
    }

    public void setTongTienSan(String TongTienSan) {
        this.TongTienSan = TongTienSan;
    }


    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String NgayLap) {
        this.NgayLap = NgayLap;
    }

    // Getter và setter cho các cột mới
    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String TenSan) {
        this.TenSan = TenSan;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }


    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }
}
