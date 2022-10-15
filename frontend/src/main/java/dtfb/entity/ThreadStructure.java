package dtfb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThreadStructure {
    private long threadId;
    private String threadName;
}