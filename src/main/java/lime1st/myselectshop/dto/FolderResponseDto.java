package lime1st.myselectshop.dto;

import lime1st.myselectshop.entity.Folder;
import lombok.Getter;

@Getter
public class FolderResponseDto {
    private Long id;
    private String name;

    public FolderResponseDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
    }
}