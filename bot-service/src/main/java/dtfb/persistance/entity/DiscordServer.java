package dtfb.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class DiscordServer {
    @Id
    @Column
    private long serverId;

    @Column
    private String serverName;
}