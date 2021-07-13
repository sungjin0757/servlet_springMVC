package study.servlettospringmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String username;
    private Integer age;

    public void saveMember(String username,Integer age){
        this.username=username;
        this.age=age;
    }
}
