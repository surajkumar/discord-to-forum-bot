package dtfb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CmChannel {
    private long channelId;
    private String channelName;
    private List<CmMessage> messages;
}