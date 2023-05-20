package com.challenge.fivvy.dominio;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fiv_acceptance")
@Data
@NoArgsConstructor
public class Acceptance extends AuditableBase2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_Id")
    private String userId;

    //disclaimer_id
    @OneToOne
    @JoinColumn
    @JsonIgnore
    private Disclaimer disclaimer;


    // this constructor allow insert disclaimer_id value in fiv_acceptance table automatically
    public Acceptance(String userId, Disclaimer disclaimer) {
        this.userId = userId;
        this.disclaimer = disclaimer;
        disclaimer.setAcceptance(this);
    }
}
