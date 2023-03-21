package ma.uiass.eia.pds.gihFrontEnd;

import java.lang.reflect.Type;

import com.google.gson.*;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.Espace;
import ma.uiass.eia.pds.gihBackEnd.model.Salle;

public class EspaceDeserializer implements JsonDeserializer<Espace> {

    @Override
    public Espace deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        Gson gson = new Gson();
        switch (type) {
            case "Chambre":
                return gson.fromJson(json, Chambre.class);
            case "Salle":
                return gson.fromJson(json, Salle.class);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}