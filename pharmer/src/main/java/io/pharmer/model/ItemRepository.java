package io.pharmer.model;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

	public List<Item> findByName(String name){
		return list("name", name);
	}

	public List<Item> findActiveItems(){
		return list("status", Item.Status.ACTIVE);
	}
}
