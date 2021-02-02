package io.pharmer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="Name may not be blank")
	private String name;
	@NotNull(message="Expiration date is mandatory")
	private LocalDate expirationDate;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	public enum Status{ACTIVE, DELETED}

	public Item(String name, LocalDate expirationDate) {
		this.name = name;
		this.expirationDate = expirationDate;
	}
}
