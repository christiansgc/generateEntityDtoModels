package cm.aites.dev.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
//@NoArgsConstructor
public class EntityDtoDescription {
    private String entityName;
    private String packageEntityClass;
    private String pathDirEntityClass;
    private String packageDtoClass;
    private String pathDirDtoClass;
    private String dbTableName;
    private TypeEntityDto typeEntityDto;
    private ArrayList<FieldEntityDto> listeFields;

    public int countPrimaryKeys(){
        if(this.listeFields!=null){
            return Long.valueOf(this.listeFields.stream().filter(elt->elt.isPrimaryKey()==true).count()).intValue();
        }else{
            return 0;
        }
    }

    public FieldEntityDto findFirstPrimaryKey(){
        if(this.listeFields!=null){
            return this.listeFields.stream().filter(elt->elt.isPrimaryKey()==true).findFirst().get();
        }else{
            return null;
        }
    }
}
