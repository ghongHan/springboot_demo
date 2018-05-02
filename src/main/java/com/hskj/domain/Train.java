package com.hskj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xvitcoder
 * Date: 12/21/12
 * Time: 12:19 AM
 */
@Entity
@Table(name = "train")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //此注解表明json数据中中包含非null属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "railwayStation"})
public class Train implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer speed;

    private Boolean diesel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private RailwayStation railwayStation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//  @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    //@NoArgsConstructor代替
    /*
    public Train() {
    }
    */

    //@AllArgsConstructor代替
    /*
    public Train(Long id, String name, Integer speed, Boolean diesel) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.diesel = diesel;
    }*/

    //此处用@Builder代替
    /*public static class Builder{
        private Long id;

        private String name;

        private Integer speed;

        private Boolean diesel;

        private RailwayStation railwayStation;

        private Date createTime;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder diesel(Boolean diesel){
            this.diesel = diesel;
            return this;
        }

        public Builder speed(Integer speed){
            this.speed = speed;
            return this;
        }

        public Builder railwayStation(RailwayStation railwayStation){
            this.railwayStation = railwayStation;
            return this;
        }

        public Builder createTime(Date createTime){
            this.createTime = createTime;
            return this;
        }

        public Train build(){
            return new Train(id, name, speed, diesel, railwayStation, createTime);
        }
    }

    public static Builder builder(){
        return new Builder();
    }*/

}
