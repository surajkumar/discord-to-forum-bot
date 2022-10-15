package dtfb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ServerStructure {
    private long serverId;
    private String serverName;
    private List<CategoryStructure> categories;
}