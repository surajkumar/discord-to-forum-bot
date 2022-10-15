package dtfb.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordMessage {

    @Id
    @Column(nullable = false)
    private long messageId;

    @Column(nullable = false)
    private long channelId;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String timestamp;

    @Column(nullable = false)
    private long userId;
}