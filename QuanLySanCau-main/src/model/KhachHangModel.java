package model;

public class KhachHangModel {

    private int MaKH, SoLanDat;
    private String TenKH, SDT;

    public KhachHangModel(String tenkh, String sdt) {

        this.TenKH = tenkh;
        this.SDT = sdt;
       
    }

    public KhachHangModel(int MaKH, String TenKH, String SDT, int SoLanDat) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.SoLanDat = SoLanDat;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getSoLanDat() {
        return SoLanDat;
    }

    public void setSoLanDat(int SoLanDat) {
        this.SoLanDat = SoLanDat;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
