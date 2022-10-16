package dtfb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmMessage {
    private long messageId;
    private String message;
    private long userId;
    private String userName;
    private String userImage;
    private String timestamp;
}