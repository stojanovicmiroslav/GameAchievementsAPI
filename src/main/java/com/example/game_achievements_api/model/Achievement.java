package com.example.game_achievements_api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;



// san andreas
// ac1, ac2, ac3
// 1, 3, 2

// ac2, ac1, ac3
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /// @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    @Column(nullable = false,  name = "name_achievement")
    @Size(min = 2, max=100, message = "Achievement name must be between 2 and 100 characters")
    private String name;

    @Column(nullable = false,  name = "description_achievement")
    @Size(min = 5, max=100, message = "Achievement description must be between 5 and 500 characters")
    private String description;

    @Column(name = "icon_achievement")
    private String icon;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date achievementCreated;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date achievementUpdated;

    @Column( name = "diplay_order")
    private Integer displayOrder;


    @ManyToOne
    private Game game;

}
