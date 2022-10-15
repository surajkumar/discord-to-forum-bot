package dtfb.persistance.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordChannelThread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column
    private String channelId;

    @Column
    private String threadId;

    @Column
    private String name;
}