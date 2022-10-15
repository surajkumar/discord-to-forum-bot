package dtfb.controllers.structure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryStructure {
    private long categoryId;
    private String categoryName;
    private List<ChannelStructure> channels;
}