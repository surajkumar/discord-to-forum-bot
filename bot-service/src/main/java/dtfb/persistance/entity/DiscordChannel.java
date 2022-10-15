package dtfb.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiscordChannel {
    @Id
    @Column(nullable = false)
    private long channelId;

    @Column(nullable = false)
    private long categoryId;

    @Column(nullable = false)
    private String name;
}