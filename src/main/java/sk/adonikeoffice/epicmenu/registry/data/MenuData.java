package sk.adonikeoffice.epicmenu.registry.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.remain.CompSound;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuData implements ConfigSerializable {

	private String name; // NAME OF THE MENU, SO WE CAN GET IT
	private List<String> commands; // 👍
	private String title; // 👍
	private CompSound openSound;
	private int rows; // 👍
	private List<ButtonData> button; // 👍
	private InfoData info; // 👍
	private FillData fill; // 👍

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				"Name", this.name,
				"Commands", this.commands,
				"Title", this.title,
				"Open_Sound", this.openSound,
				"Rows", this.rows,
				"Content", this.button,
				"Info", this.info,
				"Fill", this.fill
		);
	}

	public static MenuData deserialize(final SerializedMap map) {
		final String name = map.getString("Name");
		final List<String> commands = map.getStringList("Commands");
		final String title = map.getString("Title");
		final CompSound openSound = map.get("Open_Sound", CompSound.class);
		final int rows = map.getInteger("Rows");
		final List<ButtonData> content = map.getList("Content", ButtonData.class);
		final InfoData info = map.get("Info", InfoData.class);
		final FillData fill = map.get("Fill", FillData.class);

		return new MenuData(name, commands, title, openSound, rows, content, info, fill);
	}

}
