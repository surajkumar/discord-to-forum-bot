package dtfb.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class DiscordCategory {
    @Id
    @Column
    private long categoryId;

    @Column
    private String name;
}