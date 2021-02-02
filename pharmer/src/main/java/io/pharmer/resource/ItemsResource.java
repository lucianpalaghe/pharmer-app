package io.pharmer.resource;

import io.pharmer.model.Item;
import io.pharmer.service.ItemService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.*;

@Path("items")
public class ItemsResource {
    @Inject
    ItemService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> findAllItems(){
        return service.findAllItems().stream().sorted(Comparator.comparing(Item::getExpirationDate)).map(ItemDTO::new).collect(toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemDTO addItem(@Valid Item item) {
        service.addItem(item);
        return new ItemDTO(item);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemDTO editItem(@PathParam Long id, ItemDTO item) {
        Item editedItem = service.editItem(id, item);
        return new ItemDTO(editedItem);
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam Long id){
        service.deleteItem(id);
        return Response.ok().build();
    }
}