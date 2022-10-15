package dtfb.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordUser {

    @Id
    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String discriminator;

    @Column
    private String profilePictureUrl;
}