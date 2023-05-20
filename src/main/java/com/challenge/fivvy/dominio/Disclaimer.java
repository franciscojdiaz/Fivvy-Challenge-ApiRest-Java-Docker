package com.challenge.fivvy.dominio;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fiv_disclaimer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Disclaimer extends AuditableBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "dis_name")
    private String name;

    @Column(name = "dis_text")
    private String text;

    @Column(name = "dis_version")
    private String version;

    @OneToOne(mappedBy = "disclaimer", cascade = CascadeType.ALL)
    private Acceptance acceptance;


}
