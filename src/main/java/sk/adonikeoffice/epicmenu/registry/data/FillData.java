package sk.adonikeoffice.epicmenu.registry.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.remain.CompMaterial;

@Getter
@AllArgsConstructor
public class FillData implements ConfigSerializable {

	private boolean fillEnabled;
	private CompMaterial material;

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray("Enabled", this.fillEnabled, "Material", this.material);
	}

	public static FillData deserialize(final SerializedMap map) {
		return new FillData(map.getBoolean("Enabled"), map.getMaterial("Material"));
	}

}
