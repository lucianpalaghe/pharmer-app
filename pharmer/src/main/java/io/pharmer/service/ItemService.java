package io.pharmer.service;

import io.pharmer.model.Item;
import io.pharmer.model.ItemRepository;
import io.pharmer.resource.ItemDTO;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.*;

@ApplicationScoped
@Slf4j
public class ItemService {
	@Inject
	ItemRepository repo;
	@Inject
	WhatsAppService whatsapp;

	public List<Item> findAllItems() {
		return repo.findActiveItems();
	}

	@Transactional
	public Item addItem(Item item) {
		repo.persist(item);
		return item;
	}

	@Transactional
	public Item editItem(Long id, ItemDTO dto) {
		Item item = repo.findById(id);
		item.setName(dto.getName());
//		repo.persist(item);
		return item;
	}

	@Transactional
	public void deleteItem(Long id) {
		repo.findById(id).setStatus(Item.Status.DELETED);
	}

//	@Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(every="10s")
	public void sendExpiryNotifications() {
		List<ItemDTO> expiringItems = findAllItems().stream().map(ItemDTO::new).filter(i -> i.isSoonToExpire()).collect(toList());
		log.info("Got expiring items: " + expiringItems.toString() + ". Sending notifications...");
		expiringItems.forEach(i -> whatsapp.sendWhatsAppMessage(String.format("Alooo! Expira %s in %s", i.getName(), i.getExpirationDate())));
	}
}
