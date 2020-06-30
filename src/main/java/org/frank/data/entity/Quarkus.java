package org.frank.data.entity;

import javax.persistence.*;

@Entity
public class Quarkus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_quarkus")
    @SequenceGenerator(name="seq_quarkus", sequenceName = "seq_quarkus", allocationSize = 1)
    private Long id;
    private String name;
    private String address;

    public Quarkus() {
    }

    public Quarkus(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
