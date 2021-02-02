package io.pharmer.resource;

import io.pharmer.model.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@NoArgsConstructor
public class ItemDTO {
	private Long id;
	private String name;
	private String expirationDate;
	private boolean soonToExpire;
	private boolean expired;

	private static Locale ro = Locale.forLanguageTag("ro");
	private static DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM YYYY", ro);

	public ItemDTO(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.expirationDate = item.getExpirationDate().format(monthYearFormatter);
		if (item.getExpirationDate().isBefore(LocalDate.now())) {
			expired = true;
		} else if (item.getExpirationDate().isBefore(LocalDate.now().plusMonths(3))) {
			soonToExpire = true;
		}
	}
}
