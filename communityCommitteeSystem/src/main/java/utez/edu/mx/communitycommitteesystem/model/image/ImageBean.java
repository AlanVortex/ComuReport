package utez.edu.mx.communitycommitteesystem.model.image;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;

@Entity
@Getter
@Setter
@Table(name = "image")
public class ImageBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReport")
    private ReportBean reportBean;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ReportBean getReportBean() {
        return reportBean;
    }

    public void setReportBean(ReportBean reportBean) {
        this.reportBean = reportBean;
    }
}
