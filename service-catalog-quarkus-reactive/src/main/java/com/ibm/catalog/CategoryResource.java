package com.ibm.catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.concurrent.CompletionStage;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import io.vertx.mutiny.sqlclient.Row;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;
//import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("{tenant}/CustomerOrderServicesWeb/jaxrs/Category")
@Produces("application/json")
public class CategoryResource {
  
    private static final Logger LOGGER = Logger.getLogger(CategoryResource.class.getName());
    
    @Inject
    EntityManager entityManager;

    //@Inject
    //io.vertx.mutiny.pgclient.PgPool client;
    
    //private static int MAXIMAL_DURATION = 5000;

    /*@Inject
      private InitDatabase initDatabase;

    @PostConstruct
    public void config() {
        initDatabase.config();
    }
    */
    
    @GET
    public CompletionStage<List<Category>> get() {
        System.out.println("{tenant}/CustomerOrderServicesWeb/jaxrs/Category invoked in Quarkus reactive catalog service");
        
        String statement = "SELECT id, name, parent FROM category";
        return client.preparedQuery(statement).execute()
                //.toCompletableFuture()
                //.orTimeout(MAXIMAL_DURATION, TimeUnit.MILLISECONDS)
                //.exceptionally(throwable -> {                    
                //    System.out.println(throwable);
                //    return null;
                //})
                .onItem().transform(rows -> {
                    List<Category> categories = new ArrayList<>(rows.size());
                    rows.forEach(row -> categories.add(fromRow(row)));
                    return addSubCategories(categories);
                })
                .subscribeAsCompletionStage();
    }

    private List<Category> addSubCategories(List<Category> categories) { 
        List<Category> potentialSubCategories = categories;
        return categories.stream()
            .filter(category -> category.parent == Long.valueOf(0))
            .map(category -> {
                List<Category> subCategories = potentialSubCategories.stream()
                    .filter(subCategory -> subCategory.parent == category.id)
                    .collect(Collectors.toList());
                category.setSubCategories(subCategories);
                return category;
            })
            .collect(Collectors.toList());
    }

    private static Category fromRow(Row row) {
        Category category = new Category();
        category.id = row.getLong("id");
        category.name = row.getString("name");
        category.parent = row.getLong("parent");       
        return category;
    }
}
