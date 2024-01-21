package cm.aites.dev.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;

@Getter
@Setter
@ToString
public class FieldEntityDto {
    private String entityFieldName;
    private String dbFieldName;
    private String dtoFieldName;
    private TypeData typeFieldData = TypeData.STRING;
    private Integer maxLength;
    private boolean primaryKey = false;
    private boolean nullable = true;
    private boolean unique = false;
    private boolean generatedValue = false;
    private GenerationType generationType = GenerationType.IDENTITY;
    private String entityColumnDefinition;
    private boolean useInDtoCreateView;
    private boolean useInDtoUpdateView;
    private boolean useInDtoImportView;
    private boolean useInDtoExportView;
    private boolean transientField;
    private ForeignFieldEntityDto referenceFK;

    public boolean isStringData(){
        return this.typeFieldData==TypeData.STRING || this.typeFieldData==TypeData.STRING_LOB;
    }

    public boolean isTypeData_StringLob(){
        return this.typeFieldData==TypeData.STRING_LOB;
    }

    /*public FieldEntityDto(String dbFieldName, String entityFieldName, String dtoFieldName, TypeData typeFieldData) {
        this.dbFieldName = dbFieldName;
        this.entityFieldName = entityFieldName;
        this.dtoFieldName = dtoFieldName;
        this.typeFieldData = typeFieldData;
    }

    public FieldEntityDto(String dbFieldName) {
        this(dbFieldName,dbFieldName,dbFieldName,TypeData.STRING);
    }*/
}
