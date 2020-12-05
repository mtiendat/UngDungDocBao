package com.example.ungdungdocbao;

public class Newspaper {
    private String TieuDe;
    private String DanhMuc;
    private String MoTa;
    private String NoiDung;
    private String NgayDang;
    private String HinhAnh;
    private String TieuDeHinhAnh;
    private String TacGia;
    public Newspaper(String TieuDe,String DanhMuc,String MoTa,String NoiDung,String NgayDang, String HinhAnh,String TieuDeHinhAnh, String TacGia){
        this.TieuDe=TieuDe;
        this.DanhMuc=DanhMuc;
        this.MoTa=MoTa;
        this.NoiDung=NoiDung;
        this.NgayDang=NgayDang;
        this.HinhAnh = HinhAnh;
        this.TieuDeHinhAnh= TieuDeHinhAnh;
        this.TacGia = TacGia;

    }
    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getDanhMuc() {
        return DanhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        DanhMuc = danhMuc;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTieuDeHinhAnh() {
        return TieuDeHinhAnh;
    }

    public void setTieuDeHinhAnh(String tieuDeHinhAnh) {
        TieuDeHinhAnh = tieuDeHinhAnh;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }
}
