package sk.adonikeoffice.epicmenu.registry.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.List;

@Getter
@AllArgsConstructor
public class InfoData implements ConfigSerializable {

	private int slot;
	private CompMaterial material;
	private String name;
	private List<String> lore;

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				"Slot", this.slot,
				"Material", this.material,
				"Name", this.name,
				"Lore", this.lore
		);
	}

	public static InfoData deserialize(final SerializedMap map) {
		final int slot = map.getInteger("Slot");
		final CompMaterial material = map.getMaterial("Material");
		final String name = map.getString("Name");
		final List<String> lore = map.getList("Lore", String.class);

		return new InfoData(slot, material, name, lore);
	}

}
