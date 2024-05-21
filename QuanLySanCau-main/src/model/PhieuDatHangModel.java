package model;

public class PhieuDatHangModel {

    private int maDH;
    private int maDV;
    private String tenDV;
    private int soLuong;
    private float donGia;
    private float thanhTien;

    public PhieuDatHangModel() {

    }

    public PhieuDatHangModel(int maDH,int maDV,String tenDV, float donGia,int soLuong, float thanhTien) {
        this.maDH = maDH;
        this.maDV = maDV;
        this.tenDV=tenDV;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }
    
    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

}
