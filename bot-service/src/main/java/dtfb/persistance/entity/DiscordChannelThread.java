package dtfb.persistance.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordChannelThread {

    @Column(nullable = false)
    private long channelId;

    @Id
    @Column(nullable = false)
    private long threadId;

    @Column(nullable = false)
    private String name;
}