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

    @Column(length = 100)
    private String image;

    @Column(length = 255)
    private String url;


    @ManyToOne
    @JoinColumn(name = "idReport")
    private ReportBean reportBean;


}
