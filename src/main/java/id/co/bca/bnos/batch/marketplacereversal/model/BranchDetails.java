package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "BRANCH_DETAILS")
@Audited
public class BranchDetails extends AuditableEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_details_id_seq")
    @SequenceGenerator(name = "branch_details_id_seq", sequenceName = "branch_details_id_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BRANCH")
    Branch branch;
    
    @Size(max = 6, message = "TOP/NON max 6 digits")
    @Column(name = "TOP_NON", length = 6)
    private String topNon;

    @Size(max = 40, message = "IBU KOTA DATI II max 40 digits")
    @Column(name = "IBU_KOTA_DATI_II", length = 40)
    private String ibukotaDatiII;

    @Size(max = 5, message = "SANDI max 5 digits")
    @Column(name = "SANDI", length = 5)
    private String sandi;

    @Size(max = 35)
    @Column(name = "DATI_II", length = 35)
    private String datiII;

    @Size(max = 2)
    @Column(name = "KANWIL", length = 2)
    private String kanwil;

    @Size(max = 15)
    @Column(name = "STATUS_CABANG", length = 15)
    private String statusCabang;

    @Size(max = 35)
    @Column(name = "CABANG_KOORDINASI", length = 35)
    private String cabangKoordinasi;
    
    @Size(max = 40)
    @Column(name = "NAMA_KCU_CABANG_INDUK", length = 40)
    private String namaKcuCabangInduk;

    @Size(min = 4, max = 4)
    @Column(name = "KODE_CABANG_INDUK", length = 4)
    private String kodeCabangInduk;

    @Size(min = 3, max = 3)
    @Column(name = "INISIAL_CABANG_INDUK", length = 3)
    private String inisialCabangInduk;

    @Size(max = 45)
    @Column(name = "LONGITUDE", length = 45)
    private String longitude;

    @Size(max = 45)
    @Column(name = "LATTITUDE", length = 45)
    private String lattitude;

    @Size(max = 4)
    @Column(name = "TIMEZONE", length = 4)
    private String timezone;

    @Size(max = 150)
    @Column(name = "ALAMAT", length = 150)
    private String alamat;

    @Size(max = 35)
    @Column(name = "NEGARA", length = 35)
    private String negara;

    @Size(max = 35)
    @Column(name = "KOTA", length = 35)
    private String kota;

    @Size(max = 35)
    @Column(name = "PROPINSI", length = 35)
    private String propinsi;

    @Size(max = 5)
    @Column(name = "KODE_POS", length = 5)
    private String kodePos;

    @Size(max = 40)
    @Column(name = "TELEPON", length = 40)
    private String telepon;

    @Size(max = 40)
    @Column(name = "FAX", length = 40)
    private String fax;

    @Size(max = 30)
    @Column(name = "TELEX", length = 30)
    private String telex;

    @Size(max = 20)
    @Column(name = "NO_PERSETUJUAN_OJK", length = 20)
    private String noPersetujuanOjk;

    @Column(name = "TANGGAL_PERSETUJUAN_OJK")
    private LocalDate tanggalPersetujuanOjk;

    @Size(min = 6, max = 6)
    @Column(name = "SANDI_LBU", length = 6)
    private String sandiLbu;

    @Size(min = 7, max = 7)
    @Column(name = "SANDI_KLIRING", length = 7)
    private String sandiKliring;

    @Column(name = "JUMLAH_CRM")
    private Integer jumlahCrm;

    @Column(name = "JUMLAH_MF")
    private Integer jumlahMf;

    @Column(name = "JUMLAH_NT")
    private Integer jumlahNt;

    @Column(name = "JUMLAH_CDM")
    private Integer jumlahCdm;

    @Column(name = "TANGGAL_BUKA_CABANG")
    private LocalDate tanggalBukaCabang;

    @Column(name = "TANGGAL_OPERASIONAL")
    private LocalDate tanggalOperasional;

    @Column(name = "TANGGAL_TUTUP_CABANG")
    private LocalDate tanggalTutupCabang;

    @Size(max = 40)
    @Column(name = "NAMA_KEPALA", length = 40)
    private String namaKepala;

    @Size(max = 10)
    @Column(name = "NIP_KEPALA", length = 10)
    private String nipKepala;

    @Size(max = 15)
    @Column(name = "NO_HP_KEPALA", length = 15)
    private String noHPKepala;

    @Size(max = 40)
    @Column(name = "EMAIL_KEPALA", length = 40)
    private String emailKepala;

    @Size(max = 40)
    @Column(name = "NAMA_KOC", length = 40)
    private String namaKoc;

    @Size(max = 10)
    @Column(name = "NIP_KOC", length = 10)
    private String nipKoc;

    @Size(max = 15)
    @Column(name = "NO_HP_KOC", length = 15)
    private String noHPKoc;

    @Size(max = 40)
    @Column(name = "EMAIL_KOC", length = 40)
    private String emailKoc;

    @Size(max = 40)
    @Column(name = "NAMA_KPBC", length = 40)
    private String namaKpbc;

    @Size(max = 10)
    @Column(name = "NIP_KPBC", length = 10)
    private String nipKpbc;

    @Size(max = 15)
    @Column(name = "NO_HP_KPBC", length = 15)
    private String noHPKpbc;

    @Size(max = 40)
    @Column(name = "EMAIL_KPBC", length = 40)
    private String emailKpbc;

    //REKSADANA_IND 
    @Column(name = "REKSADANA_IND")
    private Boolean reksadanaInd;
    
    //OBLIGASI_IND       
    @Column(name = "OBLIGASI_IND")
    private Boolean obligasiInd;

    //BANCASSURANCE_IND
    @Column(name = "BANCASSURANCE_IND")          
    private Boolean bancassuranceInd;

    //LOCATION_TYPE   
    @Column(name = "LOCATION_TYPE") 
    private String locationType;

    //WEEKEND_BANK_SAT        
    @Column(name = "WEEKEND_BANK_SAT")
    private Boolean weekendBankSat; 

    //WEEKEND_BANK_SUN   
    @Column(name = "WEEKEND_BANK_SUN") 
    private Boolean weekendBankSun;

    //RESERVATION
    @Column(name = "RESERVATION")
    private Boolean reservation;  

    //KIOSK_VENDOR      
    @Column(name = "KIOSK_VENDOR")  
    private String kioskVendor;

    //REGULER_KIOSK       
    @Column(name = "REGULER_KIOSK")  
    private String regulerKiosk;

    //PRIORITAS_KIOSK  
    @Column(name = "PRIORITAS_KIOSK")      
    private String prioritasKiosk;      

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIbukotaDatiII()
    {
        return ibukotaDatiII;
    }

    public void setIbukotaDatiII(String ibukotaDatiII)
    {
        this.ibukotaDatiII = ibukotaDatiII;
    }

    public String getTopNon()
    {
        return topNon;
    }

    public void setTopNon(String topNon)
    {
        this.topNon = topNon;
    }

    public String getSandi()
    {
        return sandi;
    }

    public void setSandi(String sandi)
    {
        this.sandi = sandi;
    }

    public String getDatiII()
    {
        return datiII;
    }

    public void setDatiII(String datiII)
    {
        this.datiII = datiII;
    }

    public String getKanwil()
    {
        return kanwil;
    }

    public void setKanwil(String kanwil)
    {
        this.kanwil = kanwil;
    }

    public String getStatusCabang()
    {
        return statusCabang;
    }

    public void setStatusCabang(String statusCabang)
    {
        this.statusCabang = statusCabang;
    }

    public String getCabangKoordinasi()
    {
        return cabangKoordinasi;
    }

    public void setCabangKoordinasi(String cabangKoordinasi)
    {
        this.cabangKoordinasi = cabangKoordinasi;
    }

    public String getNamaKcuCabangInduk()
    {
        return namaKcuCabangInduk;
    }

    public void setNamaKcuCabangInduk(String namaKcuCabangInduk)
    {
        this.namaKcuCabangInduk = namaKcuCabangInduk;
    }

    public String getKodeCabangInduk()
    {
        return kodeCabangInduk;
    }

    public void setKodeCabangInduk(String kodeCabangInduk)
    {
        this.kodeCabangInduk = kodeCabangInduk;
    }

    public String getInisialCabangInduk()
    {
        return inisialCabangInduk;
    }

    public void setInisialCabangInduk(String inisialCabangInduk)
    {
        this.inisialCabangInduk = inisialCabangInduk;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLattitude()
    {
        return lattitude;
    }

    public void setLattitude(String lattitude)
    {
        this.lattitude = lattitude;
    }

    public String getTimeZone()
    {
        return timezone;
    }

    public void setTimeZone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getNegara()
    {
        return negara;
    }

    public void setNegara(String negara)
    {
        this.negara = negara;
    }

    public String getKota()
    {
        return kota;
    }

    public void setKota(String kota)
    {
        this.kota = kota;
    }

    public String getPropinsi()
    {
        return propinsi;
    }

    public void setPropinsi(String propinsi)
    {
        this.propinsi = propinsi;
    }

    public String getKodePos()
    {
        return kodePos;
    }

    public void setKodePos(String kodePos)
    {
        this.kodePos = kodePos;
    }

    public String getTelepon()
    {
        return telepon;
    }

    public void setTelepon(String telepon)
    {
        this.telepon = telepon;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getTelex()
    {
        return telex;
    }

    public void setTelex(String telex)
    {
        this.telex = telex;
    }

    public String getNoPersetujuanOjk()
    {
        return noPersetujuanOjk;
    }

    public void setNoPersetujuanOjk(String noPersetujuanOjk)
    {
        this.noPersetujuanOjk = noPersetujuanOjk;
    }

    public LocalDate getTanggalPersetujuanOjk()
    {
        return tanggalPersetujuanOjk;
    }

    public void setTanggalPersetujuanOjk(LocalDate tanggalPersetujuanOjk)
    {
        this.tanggalPersetujuanOjk = tanggalPersetujuanOjk;
    }
    
    public String getSandiLbu()
    {
        return sandiLbu;
    }

    public void setSandiLbu(String sandiLbu)
    {
        this.sandiLbu = sandiLbu;
    }

    public String getSandiKliring()
    {
        return sandiKliring;
    }

    public void setSandiKliring(String sandiKliring)
    {
        this.sandiKliring = sandiKliring;
    }

    public Integer getJumlahCrm()
    {
        return jumlahCrm;
    }

    public void setJumlahCrm(Integer jumlahCrm)
    {
        this.jumlahCrm = jumlahCrm;
    }

    public Integer getJumlahMf()
    {
        return jumlahMf;
    }

    public void setJumlahMf(Integer jumlahMf)
    {
        this.jumlahMf = jumlahMf;
    }

    public Integer getJumlahNt()
    {
        return jumlahNt;
    }

    public void setJumlahNt(Integer jumlahNt)
    {
        this.jumlahNt = jumlahNt;
    }

    public Integer getJumlahCdm()
    {
        return jumlahCdm;
    }

    public void setJumlahCdm(Integer jumlahCdm)
    {
        this.jumlahCdm = jumlahCdm;
    }

    public LocalDate getTanggalBukaCabang()
    {
        return tanggalBukaCabang;
    }

    public void setTanggalBukaCabang(LocalDate tanggalBukaCabang)
    {
        this.tanggalBukaCabang = tanggalBukaCabang;
    }

    public LocalDate getTanggalOperasional()
    {
        return tanggalOperasional;
    }

    public void setTanggalOperasional(LocalDate tanggalOperasional)
    {
        this.tanggalOperasional = tanggalOperasional;
    }

    public LocalDate getTanggalTutupCabang()
    {
        return tanggalTutupCabang;
    }

    public void setTanggalTutupCabang(LocalDate tanggalTutupCabang)
    {
        this.tanggalTutupCabang = tanggalTutupCabang;
    }

    public String getNamaKepala()
    {
        return namaKepala;
    }

    public void setNamaKepala(String namaKepala)
    {
        this.namaKepala = namaKepala;
    }

    public String getNipKepala()
    {
        return nipKepala;
    }

    public void setNipKepala(String nipKepala)
    {
        this.nipKepala = nipKepala;
    }

    public String getNoHPKepala()
    {
        return noHPKepala;
    }

    public void setNoHPKepala(String noHPKepala)
    {
        this.noHPKepala = noHPKepala;
    }

    public String getEmailKepala()
    {
        return emailKepala;
    }

    public void setEmailKepala(String emailKepala)
    {
        this.emailKepala = emailKepala;
    }

    public String getNamaKoc()
    {
        return namaKoc;
    }

    public void setNamaKoc(String namaKoc)
    {
        this.namaKoc = namaKoc;
    }

    public String getNipKoc()
    {
        return nipKoc;
    }

    public void setNipKoc(String nipKoc)
    {
        this.nipKoc = nipKoc;
    }

    public String getNoHPKoc()
    {
        return noHPKoc;
    }

    public void setNoHPKoc(String noHPKoc)
    {
        this.noHPKoc = noHPKoc;
    }

    public String getEmailKoc()
    {
        return emailKoc;
    }

    public void setEmailKoc(String emailKoc)
    {
        this.emailKoc = emailKoc;
    }

    public String getNamaKpbc()
    {
        return namaKpbc;
    }

    public void setNamaKpbc(String namaKpbc)
    {
        this.namaKpbc = namaKpbc;
    }

    public String getNipKpbc()
    {
        return nipKpbc;
    }

    public void setNipKpbc(String nipKpbc)
    {
        this.nipKpbc = nipKpbc;
    }

    public String getNoHPKpbc()
    {
        return noHPKpbc;
    }

    public void setNoHPKpbc(String noHPKpbc)
    {
        this.noHPKpbc = noHPKpbc;
    }

    public String getEmailKpbc()
    {
        return emailKpbc;
    }

    public void setEmailKpbc(String emailKpbc)
    {
        this.emailKpbc = emailKpbc;
    }

    public Boolean getReksadanaInd()
    {
        return reksadanaInd;
    }

    public void setReksadanaInd(Boolean reksadanaInd)
    {
        this.reksadanaInd = reksadanaInd;
    }

    public Boolean getObligasiInd()
    {
        return obligasiInd;
    }

    public void setObligasiInd(Boolean obligasiInd)
    {
        this.obligasiInd = obligasiInd;
    }

    public Boolean getBancassuranceInd()
    {
        return bancassuranceInd;
    }

    public void setBancassuranceInd(Boolean bancassuranceInd)
    {
        this.bancassuranceInd = bancassuranceInd;
    }

    public Boolean getWeekendBankSat()
    {
        return weekendBankSat;
    }

    public void setWeekendBankSat(Boolean weekendBankSat)
    {
        this.weekendBankSat = weekendBankSat;
    }

    public Boolean getWeekendBankSun()
    {
        return weekendBankSun;
    }

    public void setWeekendBankSun(Boolean weekendBankSun)
    {
        this.weekendBankSun = weekendBankSun;
    }

    public Boolean getReservation()
    {
        return reservation;
    }

    public void setReservation(Boolean reservation)
    {
        this.reservation = reservation;
    }

    public String getLocationType()
    {
        return locationType;
    }

    public void setLocationType(String locationType)
    {
        this.locationType = locationType;
    }

    public String getKioskVendor()
    {
        return kioskVendor;
    }

    public void setKioskVendor(String kioskVendor)
    {
        this.kioskVendor = kioskVendor;
    }

    public String getRegulerKiosk()
    {
        return regulerKiosk;
    }

    public void setRegulerKiosk(String regulerKiosk)
    {
        this.regulerKiosk = regulerKiosk;
    }

    public String getPrioritasKiosk()
    {
        return prioritasKiosk;
    }

    public void setPrioritasKiosk(String prioritasKiosk)
    {
        this.prioritasKiosk = prioritasKiosk;
    }
}
