package com.codeneeti.technexushub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity
public class Role {
    @Id
    String roleId;
    String name;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList=new ArrayList<>();
}
