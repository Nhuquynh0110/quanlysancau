package model;

public class SanModel {
    private int MaSan;
    private String TenSan, LoaiSan;
    private float GiaSan;

    public SanModel(String tensan, String loaisan, float giasan) {

        this.TenSan = tensan;
        this.LoaiSan = loaisan;
        this.GiaSan = giasan;
    }

    public SanModel(int MaSan, String TenSan, String LoaiSan, float GiaSan) {
        this.MaSan = MaSan;
        this.TenSan = TenSan;
        this.LoaiSan = LoaiSan;
        this.GiaSan = GiaSan;
    }

    public int getMaSan() {
        return MaSan;
    }

    public void setMaSan(int MaSan) {
        this.MaSan = MaSan;
    }

    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String TenSan) {
        this.TenSan = TenSan;
    }

    public String getLoaiSan() {
        return LoaiSan;
    }

    public void setLoaiSan(String LoaiSan) {
        this.LoaiSan = LoaiSan;
    }

    public float getGiaSan() {
        return GiaSan;
    }

    public void setGiaSan(float GiaSan) {
        this.GiaSan = GiaSan;
    }
    
}
