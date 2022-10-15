package dtfb.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column
    private String channelId;

    @Column
    private String message;

    @Column
    private String timestamp;

    @Column
    private String userId;
}