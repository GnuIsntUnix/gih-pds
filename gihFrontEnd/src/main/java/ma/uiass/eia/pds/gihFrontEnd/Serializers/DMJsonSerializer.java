package ma.uiass.eia.pds.gihFrontEnd.Serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.DMwithExemplaire;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;

import java.io.IOException;

public class DMJsonSerializer extends StdSerializer<DM> {


    public DMJsonSerializer(Class<DM> t) {
        super(t);
    }

    public DMJsonSerializer() {
        this(null);
    }

    @Override
    public void serialize(DM value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("code", value.getCode());
        gen.writeStringField("nom", value.getNom());
        gen.writeArrayFieldStart("exemplaireDmList");
        for (ExemplaireDm exemplaireDm : ((DMwithExemplaire) value).getExemplaireDmList()) {
            gen.writeStartObject();
            gen.writeObjectField("stock", exemplaireDm.getStock());
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeObjectField("typeDM", value.getTypeDM());


        gen.writeEndObject();
    }
}
