package com.example.estrougedemospringboot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "work")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "work_name")
    private String workName;

    @Basic
    @Column(name = "status")
    private StatusEnum status;

    @Basic
    @Column(name = "starting_date")
    @Temporal(TemporalType.DATE)
    private Date startingDate;

    @Basic
    @Column(name = "ending_date")
    @Temporal(TemporalType.DATE)
    private Date endingDate;
}
